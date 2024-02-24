package com.akkih.dusk.extension

import org.bukkit.Location
import org.bukkit.entity.EntityType
import org.bukkit.inventory.ItemStack

/**
 * Drops the specified ItemStack at the given Location.
 *
 * @param itemStack The ItemStack to drop.
 * @return The dropped item entity.
 */
fun Location.dropItem(itemStack: ItemStack) = world?.dropItem(this, itemStack)

/**
 * Drops the specified ItemStack at the given Location with natural item behavior.
 *
 * @param itemStack The ItemStack to drop naturally.
 * @return The dropped item entity.
 */
fun Location.dropItemNaturally(itemStack: ItemStack) = world?.dropItemNaturally(this, itemStack)

/**
 * Spawns an entity of the specified EntityType at the given Location.
 *
 * @param type The EntityType of the entity to spawn.
 * @return The spawned entity.
 */
fun Location.spawnEntity(type: EntityType) = world?.spawnEntity(this, type)

/**
 * Checks if the chunk at the Location is loaded.
 */
val Location.isLoaded: Boolean
    get() = world?.isChunkLoaded(blockX shr 4, blockZ shr 4) ?: false