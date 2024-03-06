package com.akkih.dusk.event

import com.akkih.dusk.Dusk
import org.bukkit.Bukkit
import org.bukkit.event.Event
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener

/**
 * Registers a listener for a specific type of event with the given priority and callback.
 *
 * @param T The type of the event to listen for.
 * @param priority The priority of the event listener.
 * @param ignoreCancelled Whether to ignore cancelled events.
 * @param callback The callback function to be executed when the event occurs.
 */
inline fun <reified T : Event> event(priority: EventPriority = EventPriority.NORMAL,
                                     ignoreCancelled: Boolean = false,
                                     crossinline callback: T.() -> Unit) =
    Bukkit.getServer().pluginManager.registerEvent(
        T::class.java,
        object : Listener {},
        priority,
        { _, event ->
            if (event is T) callback(event)
        },
        Dusk.plugin,
        ignoreCancelled
    )