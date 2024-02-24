package com.akkih.dusk.extension

/**
 * Finds the first key associated with the specified value in the map.
 *
 * @param value The value to search for.
 * @param K The type of keys in the map.
 * @param V The type of values in the map.
 * @return The first key associated with the specified value, or `null` if not found.
 */
fun <K, V> Map<K, V>.findKeyByValue(value: V): K? = entries.find { it.value == value }?.key

/**
 * Finds all keys associated with the specified value in the map.
 *
 * @param value The value to search for.
 * @param K The type of keys in the map.
 * @param V The type of values in the map.
 * @return A list of keys associated with the specified value, or an empty list if not found.
 */
fun <K, V> Map<K, V>.findKeysByValue(value: V): List<K> = entries.filter { it.value == value }.map { it.key }
