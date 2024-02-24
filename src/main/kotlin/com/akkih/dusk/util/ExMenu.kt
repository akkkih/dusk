package com.akkih.dusk.util

import com.akkih.dusk.menu.Menu
import org.bukkit.entity.Player

/**
 * Open a [Menu] for the player.
 *
 * @param menu The menu to be opened for the player.
 */
fun Player.openMenu(menu: Menu) = menu.open(this)