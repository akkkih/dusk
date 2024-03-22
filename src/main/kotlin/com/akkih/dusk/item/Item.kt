package com.akkih.dusk.item

import com.akkih.dusk.extension.meta
import org.bukkit.Bukkit
import org.bukkit.Material
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
 * @param texture The texture of the custom texture.
 * @param amount The amount of items in the stack (default is 1).
 * @param meta Customization function for configuring the SkullMeta.
 * @return The created ItemStack representing the player skull with the custom texture.
 */
fun head(texture: String,
         amount: Int = 1,
         meta: SkullMeta.() -> Unit = {}) =
    item(Material.PLAYER_HEAD, amount)
        .meta(meta)
        .meta<SkullMeta> {
            val profile = Bukkit.createProfile(UUID.randomUUID())
            val textures = profile.textures

            textures.skin = URL("http://textures.minecraft.net/texture/$texture")

            profile.setTextures(textures)
            playerProfile = profile
        }