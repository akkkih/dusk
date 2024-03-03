package com.akkih.dusk.menu

import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.ItemStack

/**
 * Represents a toggleable button that can be placed in a menu.
 * The toggle functionality is handled by the class itself.
 *
 * @property slot The slot index where the button is located in the menu.
 * @property trueItem The ItemStack representing the `true` state of the button.
 * @property falseItem The ItemStack representing the `false` state of the button.
 * @property state The boolean state representing how the button should start. Default is `true`.
 */
abstract class ToggleableButton(slot: Int,
                                val trueItem: ItemStack,
                                val falseItem: ItemStack,
                                var state: Boolean = true): Button(slot, trueItem) {
    override fun onClick(event: InventoryClickEvent) {
        state = !state
        onStateChange(event)
    }

    /**
     * Called when the button is clicked in a menu.
     *
     * @param event The InventoryClickEvent associated with the button click.
     */
    abstract fun onStateChange(event: InventoryClickEvent)
}