package com.akkih.dusk.menu

import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.ItemStack

/**
 * Represents an abstract button that can be placed in a menu.
 *
 * @property slot The slot index where the button is located in the menu.
 * @property item The ItemStack representing the appearance of the button.
 */
abstract class Button private constructor(val slot: Int, val item: ItemStack) {
    /**
     * Called when the button is clicked in a menu.
     *
     * @param event The InventoryClickEvent associated with the button click.
     */
    abstract fun onClick(event: InventoryClickEvent)

    companion object {
        /**
         * Creates a Button instance with the specified slot, item, and click callback.
         *
         * @param slot The slot index where the button will be located in the menu.
         * @param item The ItemStack representing the appearance of the button.
         * @param callback The callback function to be executed when the button is clicked.
         * @return A Button instance.
         */
        fun create(slot: Int, item: ItemStack, callback: InventoryClickEvent.() -> Unit): Button {
            return object : Button(slot, item) {
                override fun onClick(event: InventoryClickEvent) {
                    callback(event)
                }
            }
        }
    }
}