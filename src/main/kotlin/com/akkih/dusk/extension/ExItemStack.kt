package com.akkih.dusk.extension

import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta

/**
 * Adds metadata to an ItemStack using a reified type parameter and a customization callback.
 *
 * @param T The type of ItemMeta.
 * @param callback The customization callback for the ItemMeta.
 * @return The modified ItemStack.
 */
inline fun <reified T : ItemMeta> ItemStack.meta(callback: T.() -> Unit) =
    apply { itemMeta = (itemMeta as? T)?.apply(callback) ?: itemMeta }

/**
 * Sets the display name of an ItemStack.
 *
 * @param displayName The display name to set.
 * @return The modified ItemStack.
 */
fun ItemStack.displayName(displayName: String) =
    meta<ItemMeta> { this.displayName(displayName.toMiniMessageComponent()) }

/**
 * Sets the lore of an ItemStack using a list of Strings.
 *
 * @param lore The lore to set.
 * @return The modified ItemStack.
 */
fun ItemStack.lore(lore: List<String>) = meta<ItemMeta> { this.lore(lore.toMiniMessageComponent()) }

/**
 * Sets the lore of an ItemStack using vararg strings.
 *
 * @param lore The lore to set.
 * @return The modified ItemStack.
 */
fun ItemStack.lore(vararg lore: String) = this.lore(lore.toList())