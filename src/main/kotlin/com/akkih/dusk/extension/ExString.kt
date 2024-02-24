package com.akkih.dusk.extension

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage

/**
 * Converts a String to a Component using the default formatting.
 *
 * @return The converted Component.
 */
fun String.toComponent() = Component.text(this)

/**
 * Converts a list of Strings into a list of Components using the default formatting.
 *
 * @return The converted list of Components.
 */
fun List<String>.toComponent() = this.map { it.toComponent() }

/**
 * Converts a String containing MiniMessage format to a Component.
 *
 * @return The converted Component.
 */
fun String.toMiniMessageComponent() = MiniMessage.miniMessage().deserialize(this)

/**
 * Converts a list of Strings containing MiniMessage format into a list of MiniMessage Components.
 *
 * @return The converted list of Components.
 */
fun List<String>.toMiniMessageComponent() = this.map { it.toMiniMessageComponent() }