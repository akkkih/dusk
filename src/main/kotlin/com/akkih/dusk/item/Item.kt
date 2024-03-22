package com.akkih.dusk.item

import com.akkih.dusk.extension.meta
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.OfflinePlayer
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import org.bukkit.inventory.meta.SkullMeta
import java.net.URL
import java.util.*

/**
 * Creates an ItemStack with the specified material, amount, and meta customization.
 *
 * @param material The material of the ItemStack.
 * @param amount The amount of items in the ItemStack (default is 1).
 * @param meta Customization function for configuring the ItemMeta.
 * @return The created ItemStack.
 */
fun item(material: Material,
         amount: Int = 1,
         meta: ItemMeta.() -> Unit = {}) =
    ItemStack(material, amount).meta(meta)

/**
 * Creates a player skull item with a custom texture. An example of texture is
 * `259b42265bb22d3cd0084e182b1392a5a88b728ab51e0359a6c07e4b8f9e675`.
 *
 * @param texture The string value of the custom texture.
 * @param amount The amount of items in the stack (default is 1).
 * @param meta Customization function for configuring the SkullMeta.
 * @return The created ItemStack representing the player skull with the custom texture.
 */
fun head(texture: String,
         amount: Int = 1,
         meta: SkullMeta.() -> Unit = {}) =
    head(URL("http://textures.minecraft.net/texture/$texture"), amount, meta)

/**
 * Creates a player skull item with a custom texture. An example of texture is
 * `259b42265bb22d3cd0084e182b1392a5a88b728ab51e0359a6c07e4b8f9e675`.
 *
 * @param texture The URL value of the custom texture.
 * @param amount The amount of items in the stack (default is 1).
 * @param meta Customization function for configuring the SkullMeta.
 * @return The created ItemStack representing the player skull with the custom texture.
 */
fun head(texture: URL,
         amount: Int = 1,
         meta: SkullMeta.() -> Unit = {}) =
    item(Material.PLAYER_HEAD, amount)
        .meta(meta)
        .meta<SkullMeta> {
            val profile = Bukkit.createProfile(UUID.randomUUID())
            val textures = profile.textures

            textures.skin = texture

            profile.setTextures(textures)
            playerProfile = profile
        }

/**
 * Creates a player skull item with the player's skin.
 *
 * @param owner The owner of the skull.
 * @param amount The amount of items in the stack (default is 1).
 * @param meta Customization function for configuring the SkullMeta.
 * @return The created ItemStack representing the player skull with the custom texture.
 */
fun head(owner: OfflinePlayer,
         amount: Int = 1,
         meta: SkullMeta.() -> Unit = {}) =
    item(Material.PLAYER_HEAD, amount)
        .meta(meta)
        .meta<SkullMeta> { owningPlayer = owner }