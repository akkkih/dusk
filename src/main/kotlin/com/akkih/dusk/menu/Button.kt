package com.akkih.dusk.menu

import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.ItemStack

/**
 * Represents an abstract button that can be placed in a menu.
 *
 * @property slot The slot index where the button is located in the menu.
 * @property item The ItemStack representing the appearance of the button.
 */
abstract class Button(val slot: Int, val item: ItemStack) {
    /**
     * Called when the button is clicked in a menu.
     *
     * @param event The InventoryClickEvent associated with the button click.
     */
    abstract fun onClick(event: InventoryClickEvent)
}