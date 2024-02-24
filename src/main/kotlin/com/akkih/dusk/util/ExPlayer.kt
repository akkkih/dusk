package com.akkih.dusk.util

import com.akkih.dusk.Dusk
import net.kyori.adventure.text.Component.text
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.metadata.FixedMetadataValue

/**
 * Extension function to clear the action bar for the player.
 */
fun Player.clearActionBar() = this.sendActionBar(text())

/**
 * Extension function to restore the player's food level to maximum.
 */
fun Player.feed() { this.foodLevel = 20 }

/**
 * Extension function to reset the player's walk speed to the default value.
 */
fun Player.resetWalkSpeed() { this.walkSpeed = 0.2F }

/**
 * Extension function to reset the player's fly speed to the default value.
 */
fun Player.resetFlySpeed() { this.flySpeed = 0.1F }

/**
 * Extension function to hide the player from all online players.
 */
fun Player.hidePlayer() = Bukkit.getOnlinePlayers().forEach { it.hidePlayer(Dusk.plugin, this) }

/**
 * Extension function to show the player to all online players.
 */
fun Player.showPlayer() = Bukkit.getOnlinePlayers().forEach { it.showPlayer(Dusk.plugin, this) }

/**
 * Extension function to set metadata for the player.
 *
 * @param key The metadata key.
 * @param value The value associated with the key.
 */
fun Player.setMetadata(key: String, value: Any?) = this.setMetadata(key, FixedMetadataValue(Dusk.plugin, value))

/**
 * Extension function to remove metadata for the player.
 *
 * @param key The metadata key to be removed.
 */
fun Player.removeMetadata(key: String) = this.removeMetadata(key, Dusk.plugin)

/**
 * Freezes the player.
 */
fun Player.freeze() = this.setMetadata("frozen", true)

/**
 * Unfreezes the player.
 */
fun Player.unfreeze() = this.removeMetadata("frozen")