package com.akkih.dusk.extension

/**
 * Executes the given expression if the boolean condition is true.
 *
 * @param param The expression to execute if the boolean condition is true.
 * @return The result of the expression if the condition is true, or null otherwise.
 * @param T The type of the result.
 */
infix fun <T> Boolean.then(param: T): T? = if (this) param else null