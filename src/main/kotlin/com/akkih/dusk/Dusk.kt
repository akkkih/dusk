package com.akkih.dusk

import com.akkih.dusk.event.listener.PlayerListener
import com.akkih.dusk.menu.MenuListener
import org.bukkit.plugin.java.JavaPlugin

class Dusk(plugin: JavaPlugin) {
    init {
        Companion.plugin = plugin

        MenuListener()
        PlayerListener()
    }

    companion object {
        /**
         * The instance of the registered JavaPlugin.
         */
        lateinit var plugin: JavaPlugin
            private set
    }
}

/**
 * Creates an instance of Dusk with the specified JavaPlugin.
 *
 * @param plugin The JavaPlugin instance.
 * @return An instance of Dusk.
 */
fun dusk(plugin: JavaPlugin) = Dusk(plugin)