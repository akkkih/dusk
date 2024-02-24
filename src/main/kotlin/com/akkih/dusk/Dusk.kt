package com.akkih.dusk

import org.bukkit.plugin.java.JavaPlugin

class Dusk private constructor(plugin: JavaPlugin) {
    init {
        Companion.plugin = plugin
    }

    companion object {
        lateinit var plugin: JavaPlugin
            private set

        fun create(plugin: JavaPlugin) = Dusk(plugin)
    }
}