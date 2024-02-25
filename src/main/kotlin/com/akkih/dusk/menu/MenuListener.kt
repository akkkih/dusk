package com.akkih.dusk.menu

import com.akkih.dusk.event.Event
import com.akkih.dusk.scheduler.Scheduler
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.event.inventory.InventoryOpenEvent
import org.bukkit.inventory.Inventory

internal class MenuListener {
    init {
        Event.listenTo<InventoryOpenEvent> {
            asMenu(inventory)?.onOpenAction?.invoke(this)
        }

        Event.listenTo<InventoryCloseEvent> {
            asMenu(inventory)?.onCloseAction?.invoke(this)
        }

        Event.listenTo<InventoryClickEvent> {
            if (clickedInventory == null) return@listenTo

            asMenu(view.topInventory)?.let { menu ->
                isCancelled = true
                if (clickedInventory != menu.inventory) return@listenTo

                Scheduler.delayTask(1) {
                    menu.onClickAction.invoke(this@listenTo)
                    menu.buttonMap[slot]?.onClick(this@listenTo)
                }
            }
        }
    }

    private fun asMenu(inventory: Inventory): Menu? {
        return inventory.holder as? Menu
    }
}