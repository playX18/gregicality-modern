ServerEvents.recipes((event) => {
    console.log("loading alloy smelter");
    event.recipes.gtceu
        .alloy_smelter("gtceu:gold_alloy_smelt")
        .itemInputs('3x gtceu:copper_dust', '1x gtceu:precious_metal_dust')
        .itemOutputs('4x gtceu:gold_alloy_ingot')
        .duration(100)
        .EUt(30);
        console.log("loading alloy smelter donme");
    
    event.recipes.gtceu
        .chemical_reactor('gtceu:gold_leach_reaction')
        .itemInputs('4x gtceu:gold_alloy_ingot')
        .inputFluids('gtceu:nitric_acid 1000')
        .itemOutputs('4x gtceu:gold_leach_dust')
        .outputFluids("gtceu:nitrogen_dioxide")
        .duration(80)
        .EUt(30);

    event.recipes.gtceu 
        .electrolyzer('gtceu:electrolyze_gold_leach')
        .itemInputs('4x gtceu:gold_leach_dust')
        .inputFluids('gtceu:hydrogen 1000')
        .itemOutputs('3x gtceu:copper_dust', "1x gtceu:gold_dust")
        .outputFluids("water 1000")
        .duration(300)
        .EUt(30);

    event.recipes.gtceu 
        .chemical_reactor('gtceu:leached_copper_reaction')
        .itemInputs('4x gtceu:gold_leach_dust')
        .inputFluids('gtceu:hydrochloric_acid 1000')
        .itemOutputs('4x gtceu:leached_copper_dust')
        .outputFluids('gtceu:chloroauric_acid 1000')
        .EUt(30)
        .duration(80);
    
    event.recipes.gtceu 
        .chemical_reactor('gtceu:chloroauric_reaction')
        .inputFluids('gtceu:chloroauric_acid 1000')
        .notConsumable('gtceu:potassium_metabisulfite_dust')
        .itemOutputs('2x gtceu:gold_dust')
        .outputFluids('water 1000', 'gtceu:chlorine 1000')
        .duration(100)
        .EUt(30);

    event.recipes.gtceu
        .mixer('gtceu:potassium_metabisulfite_mixer')
        .itemInputs('2x gtceu:potassium_dust', '2x gtceu:sulfur_dust')
        .inputFluids('gtceu:oxygen 5000')
        .circuit(1)
        .itemOutputs("9x gtceu:potassium_metabisulfite_dust")
        .duration(100)
        .EUt(30);
});