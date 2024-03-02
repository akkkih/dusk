package com.akkih.dusk.extension

import com.akkih.dusk.menu.Menu
import net.kyori.adventure.text.Component
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.event.inventory.InventoryOpenEvent
import org.bukkit.event.inventory.InventoryType

/**
 * Open a [Menu] for the player.
 *
 * @param menu The menu to be opened for the player.
 */
fun Player.openMenu(menu: Menu) = menu.open(this)

/**
 * Creates a menu with the specified title, inventory type, and callback function.
 *
 * @param title The title of the menu.
 * @param type The inventory type of the menu.
 * @param callback The callback function to be executed when the menu is created.
 * @return A Menu instance.
 */
fun menu(
    title: String,
    type: InventoryType,
    callback: Menu.() -> Unit
): Menu = menu(title.toComponent(), type, callback)

/**
 * Creates a menu with the specified title, inventory type, and callback function.
 *
 * @param title The title of the menu.
 * @param type The inventory type of the menu.
 * @param callback The callback function to be executed when the menu is created.
 * @return A Menu instance.
 */
fun menu(title: Component, type: InventoryType, callback: Menu.() -> Unit): Menu {
    return object : Menu(title, type) {
        fun onOpen(event: InventoryOpenEvent) {
            onOpenAction(event)
        }

        fun onClose(event: InventoryCloseEvent) {
            onCloseAction(event)
        }

        fun onClick(event: InventoryClickEvent) {
            onClickAction(event)
        }
    }.also(callback)
}

/**
 * Creates a menu with the specified title, number of rows, and callback function.
 *
 * @param title The title of the menu.
 * @param rows The number of rows in the menu.
 * @param callback The callback function to be executed when the menu is created.
 * @return A Menu instance.
 */
fun menu(
    title: String,
    rows: Int,
    callback: Menu.() -> Unit
): Menu = menu(title.toComponent(), rows, callback)

/**
 * Creates a menu with the specified title, number of rows, and callback function.
 *
 * @param title The title of the menu.
 * @param rows The number of rows in the menu.
 * @param callback The callback function to be executed when the menu is created.
 * @return A Menu instance.
 */
fun menu(title: Component, rows: Int, callback: Menu.() -> Unit): Menu {
    return object : Menu(title, rows) {
        fun onOpen(event: InventoryOpenEvent) {
            onOpenAction(event)
        }

        fun onClose(event: InventoryCloseEvent) {
            onCloseAction(event)
        }

        fun onClick(event: InventoryClickEvent) {
            onClickAction(event)
        }
    }.also(callback)
}