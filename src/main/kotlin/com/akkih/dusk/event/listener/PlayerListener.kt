package com.akkih.dusk.event.listener

import com.akkih.dusk.event.event
import org.bukkit.event.player.PlayerMoveEvent

internal class PlayerListener {
    init {
        // Make sure the player cannot move when they're frozen
        event<PlayerMoveEvent> {
            if (player.hasMetadata("frozen"))
                isCancelled = true
        }
    }
}