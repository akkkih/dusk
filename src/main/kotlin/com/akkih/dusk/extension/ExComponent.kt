package com.akkih.dusk.extension

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage

/**
 * Converts a Component into a serialized MiniMessage string.
 */
val Component.asString: String
    get() = MiniMessage.miniMessage().serialize(this)