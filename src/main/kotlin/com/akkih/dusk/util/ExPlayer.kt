package com.akkih.dusk.util

import com.akkih.dusk.Dusk
import net.kyori.adventure.text.Component.text
import org.bukkit.Bukkit
import org.bukkit.entity.Player

/**
 * Clears the action bar for the player.
 */
fun Player.clearActionBar() = this.sendActionBar(text())

/**
 * Restores the player's food level to maximum.
 */
fun Player.feed() { this.foodLevel = 20 }

/**
 * Resets the player's walk speed to the default value.
 */
fun Player.resetWalkSpeed() { this.walkSpeed = 0.2F }

/**
 * Resets the player's fly speed to the default value.
 */
fun Player.resetFlySpeed() { this.flySpeed = 0.1F }

/**
 * Hides the player from all online players.
 */
fun Player.hidePlayer() = Bukkit.getOnlinePlayers().forEach { it.hidePlayer(Dusk.plugin, this) }

/**
 * Shows the player to all online players.
 */
fun Player.showPlayer() = Bukkit.getOnlinePlayers().forEach { it.showPlayer(Dusk.plugin, this) }