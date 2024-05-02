GTCEuStartupEvents.registry("gtceu:material", (event) => {
  event 
    .create("precious_metal")
    .ingot()
    .color(0xedbc2a)
    .iconSet(GTMaterialIconSet.DULL)
    .components("1x gold")
    .flags(GTMaterialFlags.DISABLE_DECOMPOSITION);

  event
      .create('potassium_metabisulfite')
      .dust()
      .components('2x potassium', '2x sulfur', '5x oxygen')
      .color(0xe0d7e0).iconSet(GTMaterialIconSet.DULL);

  event 
      .create('chloroauric_acid')
      .fluid()
      .color(0xd5db5e)
      .flags(GTMaterialFlags.DISABLE_DECOMPOSITION);

  event 
      .create('gold_alloy')
      .ingot()
      .color(0xc6750b)
      .iconSet(GTMaterialIconSet.SHINY)
      .components("3x copper", "1x gold")
      .flags(GTMaterialFlags.DISABLE_DECOMPOSITION);

  event 
      .create('gold_leach')
      .dust()
      .color(0xc6750b)
      .components('3x copper', '1x gold')
      .flags(GTMaterialFlags.DISABLE_DECOMPOSITION);

  event 
    .create("leached_copper")
    .dust()
    .components("3x copper")
    .color(0x7a3f14)
    .flags(GTMaterialFlags.DISABLE_DECOMPOSITION);
});
