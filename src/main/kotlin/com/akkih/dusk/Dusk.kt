package com.akkih.dusk

import com.akkih.dusk.menu.MenuListener
import org.bukkit.plugin.java.JavaPlugin

class Dusk private constructor(plugin: JavaPlugin) {
    init {
        Companion.plugin = plugin

        MenuListener()
    }

    companion object {
        /**
         * The instance of the registered JavaPlugin.
         */
        lateinit var plugin: JavaPlugin
            private set

        /**
         * Creates an instance of Dusk with the specified JavaPlugin.
         *
         * @param plugin The JavaPlugin instance.
         * @return An instance of Dusk.
         */
        fun create(plugin: JavaPlugin) = Dusk(plugin)
    }
}