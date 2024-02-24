package com.akkih.dusk.event.listener

import com.akkih.dusk.event.Event
import com.destroystokyo.paper.event.player.PlayerJumpEvent

class PlayerListener {
    init {
        // Make sure the player cannot move when they're frozen
        Event.listenTo<PlayerJumpEvent> {
            if (player.hasMetadata("frozen"))
                isCancelled = true
        }
    }
}