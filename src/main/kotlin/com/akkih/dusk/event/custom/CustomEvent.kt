package com.akkih.dusk.event.custom

import org.bukkit.event.Cancellable
import org.bukkit.event.Event
import org.bukkit.event.HandlerList
import java.time.Instant

/**
 * Base class for custom events.
 *
 * @param async Specifies if the event should be handled asynchronously. Default value is `false`.
 */
open class CustomEvent(async: Boolean = false) : Event(async), Cancellable {
    /**
     * The timestamp when the event occurred.
     */
    val timestamp: Instant = Instant.now()

    private var _isCancelled = false

    /**
     * Checks if the event is cancelled.
     *
     * @return True if the event is cancelled, otherwise false.
     */
    override fun isCancelled() = _isCancelled

    /**
     * Sets the cancellation status of the event.
     *
     * @param cancel True to cancel the event, false to allow it to proceed.
     */
    override fun setCancelled(cancel: Boolean) { _isCancelled = cancel }

    companion object {
        private val HANDLERS = HandlerList()

        /**
         * Gets the HandlerList for the event.
         *
         * @return The HandlerList for the event.
         */
        @JvmStatic
        fun getHandlerList() = HANDLERS
    }

    /**
     * Gets the HandlerList for the event.
     *
     * @return The HandlerList for the event.
     */
    override fun getHandlers() = HANDLERS
}