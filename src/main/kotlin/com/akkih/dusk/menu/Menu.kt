package com.akkih.dusk.menu

import com.akkih.dusk.extension.toComponent
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
     * Creates a Button instance with the specified slot, item, and click callback.
     *
     * @param slot The slot index where the button will be located in the menu.
     * @param item The ItemStack representing the appearance of the button.
     * @param callback The callback function to be executed when the button is clicked.
     * @return A Button instance.
     */
    fun button(slot: Int, item: ItemStack, callback: InventoryClickEvent.() -> Unit) =
        object : Button(slot, item) {
            override fun onClick(event: InventoryClickEvent) = callback(event)
        }.apply {
            buttonMap[slot] = this
            inventory.setItem(slot, item)
        }

    /**
     * Creates a toggleable button with the specified slot, the item when the state is true,
     * the item when the state is false, the actual state of the button and the click callback.
     *
     * @param slot The slot index where the button will be located in the menu.
     * @param trueItem The ItemStack representing the button when the state is true.
     * @param falseItem The ItemStack representing the button when the state is false.
     * @param state The current state of the button. Default is `true`.
     * @param callback The callback function to be executed when the button is clicked.
     * @return A [ToggleableButton] instance.
     */
    fun toggleableButton(slot: Int,
                         trueItem: ItemStack,
                         falseItem: ItemStack,
                         state: Boolean = true,
                         callback: InventoryClickEvent.() -> Unit) =
        object : ToggleableButton(slot, trueItem, falseItem, state) {
            override fun onStateChange(event: InventoryClickEvent) = callback(event)
        }.apply {
            buttonMap[slot] = this
            inventory.setItem(slot, if (state) trueItem else falseItem)
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
    fun fillEmpty(item: ItemStack) = (0 until inventory.size)
        .filter { inventory.getItem(it)?.type?.isAir != false }
        .forEach { inventory.setItem(it, item) }

    /**
     * Fills a specified range of slots in the menu with the specified item.
     *
     * @param start The starting slot index (inclusive).
     * @param end The ending slot index (exclusive).
     * @param item The item to fill the range with.
     */
    fun fillRange(start: Int, end: Int, item: ItemStack) = (start until end)
        .forEach { inventory.setItem(it, item) }

    /**
     * Gets the item at the specified slot in the menu.
     *
     * @param slot The slot index.
     * @return The item at the specified slot, or null if no item is present.
     */
    fun getItem(slot: Int) = inventory.getItem(slot)

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
    fun removeButton(slot: Int) = buttonMap.remove(slot)
        .also { remove(slot) }

    /**
     * Gets all buttons in the menu.
     *
     * @return A map of slot index to Button.
     */
    val buttons: Map<Int, Button>
        get() = buttonMap.toMap()

    override fun getInventory() = inventory
}