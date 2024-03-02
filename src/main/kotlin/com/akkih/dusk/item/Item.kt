package com.akkih.dusk.item

import com.akkih.dusk.extension.toMiniMessageComponent
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta

/**
 * Creates an ItemStack with the specified material, amount, and meta customization.
 *
 * @param material The material of the ItemStack.
 * @param amount The amount of items in the ItemStack (default is 1).
 * @param meta Customization function for configuring the ItemMeta.
 * @return The created ItemStack.
 */
fun item(
    material: Material,
    amount: Int = 1,
    meta: ItemMeta.() -> Unit = {},
) = ItemStack(material, amount).meta(meta)

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
fun ItemStack.displayName(displayName: String) = meta<ItemMeta> { this.displayName(displayName.toMiniMessageComponent()) }

/**
 * Sets the lore of an ItemStack using a list of Strings.
 *
 * @param lore The lore to set.
 * @return The modified ItemStack.
 */
fun ItemStack.lore(lore: List<String>) = meta<ItemMeta> { this.lore(lore.toMiniMessageComponent()) }

/**
 * Creates an ItemStack from a Material with a specified amount and custom meta.
 *
 * @param amount The amount of items in the ItemStack (default is 1).
 * @param meta Customization function for configuring the ItemMeta.
 * @return The created ItemStack.
 */
fun Material.asItemStack(
    amount: Int = 1,
    meta: ItemMeta.() -> Unit = {},
) = item(this, amount, meta)