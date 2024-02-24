package com.akkih.dusk.util

import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent

/**
 * Retrieves the Player associated with the InventoryClickEvent.
 */
val InventoryClickEvent.player: Player
    get() = whoClicked as Player