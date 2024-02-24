package com.akkih.dusk.menu

import com.akkih.dusk.util.toComponent
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.event.inventory.InventoryOpenEvent
import org.bukkit.event.inventory.InventoryType
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryHolder
import org.bukkit.inventory.ItemStack

/**
 * Represents an abstract menu with common functionality for handling GUI menus.
 */
abstract class Menu : InventoryHolder {
    private val inventory: Inventory
    internal val buttonMap = hashMapOf<Int, Button>()

    /**
     * Constructor for creating a menu with a string title and number of rows.
     *
     * @param title The title of the menu.
     * @param rows The number of rows in the menu.
     * @throws IllegalArgumentException If the number of rows is not between 1 and 6.
     */
    constructor(title: String, rows: Int) : this(title.toComponent(), rows)

    /**
     * Constructor for creating a menu with a component title and number of rows.
     *
     * @param title The title of the menu.
     * @param rows The number of rows in the menu.
     * @throws IllegalArgumentException If the number of rows is not between 1 and 6.
     */
    constructor(title: Component, rows: Int) {
        if (rows !in 1 until 6) {
            throw IllegalArgumentException("Number of rows must be between 1 and 6.")
        }
        inventory = Bukkit.createInventory(this, rows * 9, title)
    }

    /**
     * Constructor for creating a menu with a string title and inventory type.
     *
     * @param title The title of the menu.
     * @param type The inventory type of the menu.
     */
    constructor(title: String, type: InventoryType) : this(title.toComponent(), type)

    /**
     * Constructor for creating a menu with a component title and inventory type.
     *
     * @param title The title of the menu.
     * @param type The inventory type of the menu.
     */
    constructor(title: Component, type: InventoryType) {
        inventory = Bukkit.createInventory(this, type, title)
    }

    internal var onOpenAction: (InventoryOpenEvent.() -> Unit) = {}
    internal var onCloseAction: (InventoryCloseEvent.() -> Unit) = {}
    internal var onClickAction: (InventoryClickEvent.() -> Unit) = {}

    /**
     * Registers the action to be executed once the menu is opened.
     *
     * @param action The action to be executed.
     */
    fun onOpen(action: InventoryOpenEvent.() -> Unit) {
        onOpenAction = action
    }

    /**
     * Registers the action to be executed once the menu is closed.
     *
     * @param action The action to be executed.
     */
    fun onClose(action: InventoryCloseEvent.() -> Unit) {
        onCloseAction = action
    }

    /**
     * Registers the action to be executed once something is clicked on the menu.
     *
     * @param action The action to be executed.
     */
    fun onClick(action: InventoryClickEvent.() -> Unit) {
        onClickAction = action
    }

    /**
     * Opens the menu for the specified player.
     *
     * @param player The player to open the menu for.
     */
    fun open(player: Player) = player.openInventory(inventory)

    /**
     * Sets an item in the menu at the specified slot.
     *
     * @param slot The slot index.
     * @param item The item to set.
     */
    fun setItem(slot: Int, item: ItemStack) = inventory.setItem(slot, item)

    /**
     * Adds one or more items to the menu.
     *
     * @param items The items to add.
     */
    fun addItem(vararg items: ItemStack) = inventory.addItem(*items)

    /**
     * Adds a button to the menu.
     *
     * @param button The button to add.
     */
    fun addButton(button: Button) {
        buttonMap[button.slot] = button
        inventory.setItem(button.slot, button.item)
    }

    /**
     * Sets a border around the menu with the specified item.
     *
     * @param item The item to use for the border.
     */
    fun setBorder(item: ItemStack) {
        val rows = inventory.size / 9
        val lastRow = rows - 1

        for (column in 0 until 9) {
            inventory.setItem(column, item)
            inventory.setItem(lastRow * 9 + column, item)
        }

        for (row in 0 until rows) {
            inventory.setItem(row * 9, item)
            inventory.setItem(row * 9 + 8, item)
        }
    }

    /**
     * Fills empty slots in the menu's inventory with the specified item.
     *
     * @param item The item to fill empty slots with.
     */
    fun fillEmpty(item: ItemStack) =
        (0 until inventory.size)
            .filter { inventory.getItem(it)?.type?.isAir != false }
            .forEach { inventory.setItem(it, item) }

    /**
     * Fills a specified range of slots in the menu with the specified item.
     *
     * @param start The starting slot index (inclusive).
     * @param end The ending slot index (exclusive).
     * @param item The item to fill the range with.
     */
    fun fillRange(start: Int, end: Int, item: ItemStack) =
        (start until end)
            .forEach { inventory.setItem(it, item) }

    /**
     * Gets the item at the specified slot in the menu.
     *
     * @param slot The slot index.
     * @return The item at the specified slot, or null if no item is present.
     */
    fun getItem(slot: Int) = inventory.getItem(slot)

    /**
     * Gets the button at the specified slot in the menu.
     *
     * @param slot The slot index.
     * @return The button at the specified slot, or null if no button is present.
     */
    fun getButton(slot: Int) = buttonMap[slot]

    /**
     * Removes the item at the specified slot in the menu.
     *
     * **NOTE:** To remove a [Button], use [removeButton] instead.
     *
     * @param slot The slot index.
     */
    fun remove(slot: Int) = inventory.setItem(slot, null)

    /**
     * Removes a button from the menu at the specified slot.
     *
     * @param slot The slot index.
     */
    fun removeButton(slot: Int) =
        buttonMap.remove(slot)
            .also { remove(slot) }

    /**
     * Gets all buttons in the menu.
     *
     * @return A map of slot index to Button.
     */
    val buttons: Map<Int, Button>
        get() = buttonMap.toMap()

    override fun getInventory() = inventory

    companion object {
        /**
         * Creates a menu with the specified title, inventory type, and callback function.
         *
         * @param title The title of the menu.
         * @param type The inventory type of the menu.
         * @param callback The callback function to be executed when the menu is created.
         * @return A Menu instance.
         */
        fun create(
            title: String,
            type: InventoryType,
            callback: Menu.() -> Unit
        ): Menu = this.create(title.toComponent(), type, callback)

        /**
         * Creates a menu with the specified title, inventory type, and callback function.
         *
         * @param title The title of the menu.
         * @param type The inventory type of the menu.
         * @param callback The callback function to be executed when the menu is created.
         * @return A Menu instance.
         */
        fun create(title: Component, type: InventoryType, callback: Menu.() -> Unit): Menu {
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
        fun create(
            title: String,
            rows: Int,
            callback: Menu.() -> Unit
        ): Menu = this.create(title.toComponent(), rows, callback)

        /**
         * Creates a menu with the specified title, number of rows, and callback function.
         *
         * @param title The title of the menu.
         * @param rows The number of rows in the menu.
         * @param callback The callback function to be executed when the menu is created.
         * @return A Menu instance.
         */
        fun create(title: Component, rows: Int, callback: Menu.() -> Unit): Menu {
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
    }
}