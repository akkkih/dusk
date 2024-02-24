package com.akkih.dusk.util

import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent

/**
 * Extension property to get the Player associated with an InventoryClickEvent.
 */
val InventoryClickEvent.player: Player
    get() = whoClicked as Player