package com.playx.gtx.fission;

import com.playx.gtx.common.util.NuclearEfficiencyHistory;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.Random;

public class ReactorGridHelper {
    private static final int[] dX = {1, 0, -1, 0};
    private static final int[] dY = {0, 1, 0, -1};

    private static final Random rand = new Random();

    private static final int MAX_SPLIT = 30;

    public static boolean simulate(ReactorGrid grid, NuclearEfficiencyHistory efficiencyHistory) {
        int sizeX = grid.getSizeX();
        int sizeY = grid.getSizeY();

        boolean hasFuel = false;

        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {

                final int x = i;
                final int y = j;

                @Nullable
                INuclearTile tile = grid.getNuclearTile(x, y);
                if (tile == null) {
                    continue;
                }

                Optional<NuclearFuel> maybeFuel = tile.getFuel();

                int neutronNumberPrime = tile.neutronGenerationTick(efficiencyHistory);
                if (neutronNumberPrime == 0) {
                    continue;
                }

                hasFuel = true;
                NuclearFuel fuel = maybeFuel.orElseThrow(() -> new IllegalStateException("Neutron generated without fuel"));

                tile.putHeat(neutronNumberPrime * fuel.getDirectEUbyDesintegration() / fuel.getNeutronMultiplicationFactor());

                int split = Math.min(neutronNumberPrime, MAX_SPLIT);
                int neutronNumberPerSplit = neutronNumberPrime / split;

                for (var k = 0; k < split + 1; k++) {
                    var neutronNumber = (k < split) ? neutronNumberPerSplit : neutronNumberPrime % split;

                    if (neutronNumber > 0) {
                        var typ = NeutronType.FAST;
                        grid.registerNeutronCreation(neutronNumber, typ);

                        int dir = rand.nextInt(4);
                        int posX = x;
                        int posY = y;

                        // Loop until we exit the grid
                        while (true) {
                            @Nullable
                            INuclearTile secondTile = grid.getNuclearTile(posX, posY);

                            if (secondTile == null) {
                                grid.registerNeutronFate(neutronNumber, typ, true);
                                break;
                            }

                            secondTile.addNeutronsToFlux(neutronNumber, typ);

                            @Nullable
                            INuclearComponent<?> component = secondTile.getComponent();

                            if (component != null) {
                                var interactionProba = component.getNeutronBehaviour().interactionTotalProbability(typ);

                                if (rand.nextDouble() < interactionProba) {
                                    var interactionSelector = rand.nextDouble();
                                    var probAbsorption = component.getNeutronBehaviour().interactionRelativeProbability(typ, NeutronInteraction.ABSORPTION);

                                    if (interactionSelector <= probAbsorption) {
                                        secondTile.absorbNeutrons(neutronNumber, typ);

                                        if (typ == NeutronType.FAST) {
                                            secondTile.putHeat(neutronNumber * NuclearConstant.EU_FOR_FAST_NEUTRON);
                                        }

                                        if (secondTile.getFuel().isPresent()) {

                                        }

                                        break;
                                    } else {
                                        dir = rand.nextInt();


                                        if (typ == NeutronType.FAST
                                                && rand.nextDouble() < component.getNeutronBehaviour().neutronSlowingProbability()) {
                                            typ = NeutronType.THERMAL;
                                            secondTile.putHeat(neutronNumber * NuclearConstant.EU_FOR_FAST_NEUTRON);
                                        }
                                    }
                                }
                            }

                            posX += dX[dir];
                            posY += dY[dir];
                        }
                    }
                }
            }
        }

        // HEAT
        // Cache heat transfer coefficients
        double heatTransferCoeff[][] = new double[grid.getSizeX()][grid.getSizeY()];
        for (int i = 0; i < sizeX; ++i) {
            for (int j = 0; j < sizeY; ++j) {
                @Nullable
                INuclearTile tile = grid.getNuclearTile(i, j);
                if (tile != null) {
                    heatTransferCoeff[i][j] = tile.getHeatTransferCoeff();
                }
            }
        }

        final int NUMERICAL_SUBSTEP = 10;

        for (int substep = 0; substep < NUMERICAL_SUBSTEP; substep++) {
            double[][] temperatureOut = new double[sizeX][sizeY];
            double[][] temperatureDelta = new double[sizeX][sizeY];

            for (int step = 0; step < 3; step++) {
                // step 0: compute temperatureOut = dT * coeff
                // step 1: compute temperatureDelta, clamping as necessary
                // step 2: set temperature
                for (int i = 0; i < sizeX; i++) {
                    for (int j = 0; j < sizeY; j++) {
                        @Nullable
                        INuclearTile tile = grid.getNuclearTile(i, j);
                        if (tile == null) {
                            continue;
                        }

                        double temperatureA = tile.getTemperature();
                        if (step == 2) {
                            tile.setTemperature(temperatureA + temperatureDelta[i][j]);
                        } else {
                            double coeffA = heatTransferCoeff[i][j];

                            if (step == 1) {
                                // clamp to avoid reaching < 0 temperatures
                                temperatureDelta[i][j] -= Math.min(temperatureA, temperatureOut[i][j]);
                            }
                            for (int k = 0; k < 4; k++) {
                                int i2 = i + dX[k];
                                int j2 = j + dY[k];

                                @Nullable
                                INuclearTile secondTile = grid.getNuclearTile(i2, j2);

                                if (secondTile != null) {
                                    double temperatureB = secondTile.getTemperature();
                                    double coeffB = heatTransferCoeff[i2][j2];
                                    double coeffTransfer = 0.5 * (coeffA + coeffB) / NUMERICAL_SUBSTEP;
                                    if (temperatureA > temperatureB) {
                                        if (step == 0) {
                                            temperatureOut[i][j] += (temperatureA - temperatureB) * coeffTransfer;
                                        } else {
                                            double frac = Math.min(1, temperatureA / temperatureOut[i][j]);
                                            temperatureDelta[i2][j2] += frac * (temperatureA - temperatureB) * coeffTransfer;
                                        }
                                    }
                                } else {
                                    double temperatureB = 0;
                                    double coeffTransfer = 0.5 * coeffA / NUMERICAL_SUBSTEP;
                                    if (step == 0) {
                                        temperatureOut[i][j] += (temperatureA - temperatureB) * coeffTransfer;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                @Nullable
                INuclearTile maybeTile = grid.getNuclearTile(i, j);

                if (maybeTile != null) {
                    maybeTile.nuclearTick(efficiencyHistory);
                }
            }
        }

        return hasFuel;
    }
}
