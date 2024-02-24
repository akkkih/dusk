package com.akkih.dusk.extension

import org.bukkit.Material

/**
 * Checks if the material represents a pickaxe.
 */
val Material.isPickaxe: Boolean
    get() = name.endsWith("PICKAXE")

/**
 * Checks if the material represents a sword.
 */
val Material.isSword: Boolean
    get() = name.endsWith("SWORD")

/**
 * Checks if the material represents an axe.
 */
val Material.isAxe: Boolean
    get() = name.endsWith("_AXE")

/**
 * Checks if the material represents a spade (shovel).
 */
val Material.isSpade: Boolean
    get() = name.endsWith("SPADE")

/**
 * Checks if the material represents a hoe.
 */
val Material.isHoe: Boolean
    get() = name.endsWith("HOE")

/**
 * Checks if the material represents an ore.
 */
val Material.isOre: Boolean
    get() = name.endsWith("ORE")

/**
 * Checks if the material represents an ingot.
 */
val Material.isIngot: Boolean
    get() = name.endsWith("INGOT")

/**
 * Checks if the material represents a door.
 */
val Material.isDoor: Boolean
    get() = name.endsWith("DOOR")

/**
 * Checks if the material represents a minecart.
 */
val Material.isMinecart: Boolean
    get() = name.endsWith("MINECART")

/**
 * Checks if the material represents water.
 */
val Material.isWater: Boolean
    get() = this == Material.WATER

/**
 * Checks if the material represents lava.
 */
val Material.isLava: Boolean
    get() = this == Material.LAVA