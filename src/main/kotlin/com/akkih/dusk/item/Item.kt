package com.akkih.dusk.item

import com.akkih.dusk.extension.toComponent
import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta

/**
 * Utility object for creating ItemStack instances with fluent builder-style methods.
 */
object Item {
    /**
     * Creates an ItemStack with the specified material, amount, and meta customization.
     *
     * @param material The material of the ItemStack.
     * @param amount The amount of items in the ItemStack (default is 1).
     * @param meta Customization function for configuring the ItemMeta.
     * @return The created ItemStack.
     */
    fun create(
        material: Material,
        amount: Int = 1,
        meta: ItemMeta.() -> Unit = {},
    ) = ItemStack(material, amount).meta(meta)
}

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
fun ItemStack.displayName(displayName: String) = meta<ItemMeta> { this.displayName(displayName.toComponent()) }

/**
 * Sets the display name of an ItemStack using a Component.
 *
 * @param displayName The display name to set.
 * @return The modified ItemStack.
 */
fun ItemStack.displayName(displayName: Component) = meta<ItemMeta> { this.displayName(displayName) }

/**
 * Sets the lore of an ItemStack using a list of Components.
 *
 * @param lore The lore to set.
 * @return The modified ItemStack.
 */
fun ItemStack.lore(lore: List<Component>) = meta<ItemMeta> { this.lore(lore) }

/**
 * Sets the lore of an ItemStack using a list of Strings.
 *
 * @param lore The lore to set.
 * @return The modified ItemStack.
 */
fun ItemStack.lore(lore: List<String>) = meta<ItemMeta> { this.lore(lore.toComponent()) }

/**
 * Enchants an ItemStack with a specific enchantment and level.
 *
 * @param enchantment The enchantment to apply.
 * @param level The level of the enchantment.
 * @return The modified ItemStack.
 */
fun ItemStack.enchant(enchantment: Enchantment, level: Int) =
    apply { this.addUnsafeEnchantment(enchantment, level) }

/**
 * Enchants an ItemStack with multiple enchantments and their respective levels.
 *
 * @param enchantments A map containing the enchantments and their levels.
 * @return The modified ItemStack.
 */
fun ItemStack.enchant(enchantments: Map<Enchantment, Int>) =
    apply { enchantments.forEach { this.addUnsafeEnchantment(it.key, it.value) } }

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
) = Item.create(this, amount, meta)