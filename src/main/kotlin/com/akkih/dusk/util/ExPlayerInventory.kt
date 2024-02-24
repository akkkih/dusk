package com.akkih.dusk.util

import org.bukkit.inventory.PlayerInventory

/**
 * Clears the armor slots in the player's inventory.
 */
fun PlayerInventory.clearArmor() { this.armorContents = arrayOf(null, null, null, null) }

/**
 * Clears all items from the player's inventory, including the armor slots.
 */
fun PlayerInventory.clearAll() = this.clear().also { this.clearArmor() }