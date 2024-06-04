package com.playx.gtx.fission;

import com.google.common.base.Preconditions;

public interface INeutronBehaviour {
    INeutronBehaviour NO_INTERACTION = new INeutronBehaviour() {

        @Override
        public double neutronSlowingProbability() {
            return 0.5;
        }

        @Override
        public double interactionTotalProbability(NeutronType type) {
            return 0;
        }

        @Override
        public double interactionRelativeProbability(NeutronType type, NeutronInteraction interaction) {
            return 0.5;
        }
    };

    static double crossSectionFromProba(double proba) {
        return -Math.log(1 - proba);
    }

    static double probaFromCrossSection(double crossSection) {
        return 1 - Math.exp(-crossSection);
    }

    static INeutronBehaviour of(NuclearConstant.ScatteringType scatteringType, IsotopeParams params, double size) {

        return of(scatteringType, params.thermalAbsorption, params.fastAbsorption, params.thermalScattering, params.fastScattering, size);
    }

    static double reduceCrossProba(double proba, double crossSectionFactor) {
        return probaFromCrossSection(crossSectionFromProba(proba) * crossSectionFactor);
    }

    static INeutronBehaviour of(NuclearConstant.ScatteringType scatteringType, double thermalNeutronAbsorptionBarn, double fastNeutronAbsorptionBarn,
                                double thermalNeutronScatteringBarn, double fastNeutronScatteringBarn, double size) {

        return new INeutronBehaviour() {

            final double thermalProbability = probaFromCrossSection((thermalNeutronAbsorptionBarn + thermalNeutronScatteringBarn) * Math.sqrt(size));
            final double fastProbability = probaFromCrossSection((fastNeutronAbsorptionBarn + fastNeutronScatteringBarn) * Math.sqrt(size));

            @Override
            public double neutronSlowingProbability() {
                return scatteringType.slowFraction;
            }

            @Override
            public double interactionTotalProbability(NeutronType type) {
                Preconditions.checkArgument(type != NeutronType.BOTH);
                if (type == NeutronType.FAST) {
                    return fastProbability;
                } else if (type == NeutronType.THERMAL) {
                    return thermalProbability;
                }
                return 0;
            }

            @Override
            public double interactionRelativeProbability(NeutronType type, NeutronInteraction interaction) {
                Preconditions.checkArgument(type != NeutronType.BOTH);
                if (type == NeutronType.THERMAL) {
                    if (interaction == NeutronInteraction.SCATTERING) {
                        return thermalNeutronScatteringBarn / (thermalNeutronAbsorptionBarn + thermalNeutronScatteringBarn);
                    } else if (interaction == NeutronInteraction.ABSORPTION) {
                        return thermalNeutronAbsorptionBarn / (thermalNeutronAbsorptionBarn + thermalNeutronScatteringBarn);
                    }
                } else if (type == NeutronType.FAST) {
                    if (interaction == NeutronInteraction.SCATTERING) {
                        return fastNeutronScatteringBarn / (fastNeutronAbsorptionBarn + fastNeutronScatteringBarn);
                    } else if (interaction == NeutronInteraction.ABSORPTION) {
                        return fastNeutronAbsorptionBarn / (fastNeutronAbsorptionBarn + fastNeutronScatteringBarn);
                    }
                }
                return 0;
            }
        };
    }

    double neutronSlowingProbability();

    double interactionTotalProbability(NeutronType type);

    double interactionRelativeProbability(NeutronType type, NeutronInteraction interaction);
}
