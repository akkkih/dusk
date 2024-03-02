package com.akkih.dusk.menu

import com.akkih.dusk.event.event
import com.akkih.dusk.scheduler.delayTask
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.event.inventory.InventoryOpenEvent
import org.bukkit.inventory.Inventory

internal class MenuListener {
    init {
        event<InventoryOpenEvent> {
            asMenu(inventory)?.onOpenAction?.invoke(this)
        }

        event<InventoryCloseEvent> {
            asMenu(inventory)?.onCloseAction?.invoke(this)
        }

        event<InventoryClickEvent> {
            if (clickedInventory == null) return@event

            asMenu(view.topInventory)?.let { menu ->
                isCancelled = true
                if (clickedInventory != menu.inventory) return@event

                delayTask(1) {
                    menu.onClickAction.invoke(this@event)
                    menu.buttonMap[slot]?.onClick(this@event)
                }
            }
        }
    }

    private fun asMenu(inventory: Inventory): Menu? {
        return inventory.holder as? Menu
    }
}