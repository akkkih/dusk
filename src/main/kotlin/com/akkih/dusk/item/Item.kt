package com.akkih.dusk.item

import com.akkih.dusk.extension.meta
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