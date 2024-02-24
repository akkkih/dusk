package com.akkih.dusk.extension

import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack

/**
 * Checks if the inventory has at least one empty slot.
 *
 * @return `true` if there is at least one empty slot, `false` otherwise.
 */
fun Inventory.hasSpace() = this.firstEmpty() != -1

/**
 * Checks if the inventory is full.
 *
 * @return `true` if the inventory is full, `false` otherwise.
 */
val Inventory.isFull: Boolean
    get() = !this.isEmpty

/**
 * Adds multiple ItemStacks to the inventory.
 *
 * @param itemStacks The ItemStacks to add to the inventory.
 */
fun Inventory.addItems(vararg itemStacks: ItemStack) = itemStacks.forEach { this.addItem(it) }