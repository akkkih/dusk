package com.akkih.dusk.event.listener

import com.akkih.dusk.event.Event
import org.bukkit.event.player.PlayerMoveEvent

class PlayerListener {
    init {
        // Make sure the player cannot move when they're frozen
        Event.listenTo<PlayerMoveEvent> {
            if (player.hasMetadata("frozen"))
                isCancelled = true
        }
    }
}