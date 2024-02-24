package com.akkih.dusk.extension

import org.bukkit.Material

val Material.isPickaxe: Boolean get() = name.endsWith("PICKAXE")
val Material.isSword: Boolean get() = name.endsWith("SWORD")
val Material.isAxe: Boolean get() = name.endsWith("_AXE")
val Material.isSpade: Boolean get() = name.endsWith("SPADE")
val Material.isHoe: Boolean get() = name.endsWith("HOE")
val Material.isOre: Boolean get() = name.endsWith("ORE")
val Material.isIngot: Boolean get() = name.endsWith("INGOT")
val Material.isDoor: Boolean get() = name.endsWith("DOOR")
val Material.isMinecart: Boolean get() = name.endsWith("MINECART")
val Material.isWater: Boolean get() = this == Material.WATER
val Material.isLava: Boolean get() = this == Material.LAVA