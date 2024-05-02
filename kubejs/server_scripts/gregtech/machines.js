ServerEvents.recipes((event) => {
  event.remove("gtceu:shaped/lp_steam_extractor_bronze");
  event.remove("gtceu:shaped/lp_steam_macerator_bronze");
  event.remove("gtceu:shaped/lp_steam_compressor_bronze");

  event.shaped(
    "gtceu:lp_steam_extractor",
    ["PPP", "XCG", "PPP"],
    {
      P: "gtceu:bronze_small_fluid_pipe",
      X: "kubejs:steampiston",
      C: "gtceu:bronze_machine_casing",
      G: "minecraft:glass"
    }
  );

  event.shaped(
    "gtceu:lp_steam_macerator",
    ["PXP", "XCX", "MXM"],
    {
      P: "kubejs:steampiston",
      X: "gtceu:bronze_small_fluid_pipe",
      C: "gtceu:bronze_machine_casing",
      M: "kubejs:steammotor"
    }
  );

  event.shaped(
    "gtceu:lp_steam_compressor",
    ["XXX", "MCP", "XXX"],
    {
      X: "gtceu:bronze_small_fluid_pipe",
      M: "kubejs:steammotor",
      P: "kubejs:steampiston",
      C: "gtceu:bronze_machine_casing"
    }
  )
});
