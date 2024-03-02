package com.akkih.dusk.extension

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextDecoration
import net.kyori.adventure.text.minimessage.MiniMessage

/**
 * Converts a String to a Component with italics disabled.
 *
 * @return The converted Component.
 */
fun String.toComponent() = Component.text(this).decoration(TextDecoration.ITALIC, false)

/**
 * Converts a list of Strings into a list of Components with italics disabled.
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
    .decoration(TextDecoration.ITALIC, false)

/**
 * Converts a list of Strings containing MiniMessage format into a list of MiniMessage Components.
 *
 * @return The converted list of Components.
 */
fun List<String>.toMiniMessageComponent() = this.map { it.toMiniMessageComponent() }