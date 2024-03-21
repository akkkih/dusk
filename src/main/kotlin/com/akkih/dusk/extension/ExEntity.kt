package com.akkih.dusk.extension

import com.akkih.dusk.Dusk
import org.bukkit.entity.Entity
import org.bukkit.metadata.FixedMetadataValue

/**
 * Set metadata for the given entity.
 *
 * @param key The metadata key.
 * @param value The value associated with the key.
 */
fun Entity.setMetadata(key: String, value: Any?) = this.setMetadata(key, FixedMetadataValue(Dusk.plugin, value))

/**
 * Remove metadata for the given entity.
 *
 * @param key The metadata key to be removed.
 */
fun Entity.removeMetadata(key: String) = this.removeMetadata(key, Dusk.plugin)

/**
 * Disables movement for the entity.
 *
 * @see [enableMovement]
 * @see [isMovementDisabled]
 */
fun Entity.disableMovement() = this.setMetadata("movement_disabled", true)

/**
 * Enables movement for the entity.
 *
 * @see [disableMovement]
 * @see [isMovementDisabled]
 */
fun Entity.enableMovement() = this.removeMetadata("movement_disabled")

/**
 * Checks if movement is disabled for the entity.
 *
 * @see [disableMovement]
 * @see [enableMovement]
 *
 * @return `true` if movement is disabled, `false` otherwise.
 */
val Entity.isMovementDisabled: Boolean
    get() = this.hasMetadata("movement_disabled")