package com.akkih.dusk.util

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage

/**
 * Converts a [String] to a [Component] using the default formatting.
 *
 * @return The converted [Component].
 */
fun String.toComponent() = Component.text(this)

/**
 * Converts a [String] containing MiniMessage format to a [Component].
 *
 * @return The converted [Component].
 */
fun String.toMiniMessageComponent() = MiniMessage.miniMessage().deserialize(this)