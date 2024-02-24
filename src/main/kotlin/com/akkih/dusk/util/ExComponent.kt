package com.akkih.dusk.util

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage

/**
 * Extension property to convert a Component to a serialized MiniMessage string.
 */
val Component.asString: String
    get() = MiniMessage.miniMessage().serialize(this)