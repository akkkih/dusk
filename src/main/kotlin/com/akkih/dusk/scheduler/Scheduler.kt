package com.akkih.dusk.scheduler

import com.akkih.dusk.Dusk
import org.bukkit.scheduler.BukkitRunnable

/**
 * Schedules a synchronous task to be executed by the Bukkit scheduler.
 *
 * @param runnable The function representing the task to be executed.
 * @return The BukkitTask representing the scheduled task.
 */
fun syncTask(runnable: BukkitRunnable.() -> Unit) = createBukkitRunnable(runnable).runTask(Dusk.plugin)

/**
 * Schedules an asynchronous task to be executed by the Bukkit scheduler.
 *
 * @param runnable The function representing the task to be executed.
 * @return The BukkitTask representing the scheduled task.
 */
fun asyncTask(runnable: BukkitRunnable.() -> Unit) = createBukkitRunnable(runnable).runTaskAsynchronously(Dusk.plugin)

/**
 * Schedules a delayed task to be executed by the Bukkit scheduler.
 *
 * @param value The duration value for the delay.
 * @param unit The TimeUnit representing the time unit of the delay. Default is milliseconds.
 * @param async Whether the task should be executed asynchronously. Default is `false`.
 * @param runnable The function representing the task to be executed.
 * @return The BukkitTask representing the scheduled task.
 */
fun delayTask(
    value: Int,
    unit: TimeUnit = TimeUnit.TICKS,
    async: Boolean = false,
    runnable: BukkitRunnable.() -> Unit
) = if (async) {
    createBukkitRunnable(runnable).runTaskLaterAsynchronously(Dusk.plugin, unit.toTicks(value.toLong()))
} else {
    createBukkitRunnable(runnable).runTaskLater(Dusk.plugin, unit.toTicks(value.toLong()))
}

/**
 * Schedules a delayed task to be executed by the Bukkit scheduler.
 *
 * @param ticks The number of ticks for the delay. Default is 1.
 * @param runnable The function representing the task to be executed.
 * @return The BukkitTask representing the scheduled task.
 * @see delayTask
 */
fun delayTask(ticks: Int = 1, runnable: BukkitRunnable.() -> Unit) = delayTask(ticks, TimeUnit.TICKS, false, runnable)

/**
 * Schedules a delayed task to be executed by the Bukkit scheduler.
 *
 * @param ticks The number of ticks for the delay. Default is 1.
 * @param async Whether the task should be executed asynchronously.
 * @param runnable The function representing the task to be executed.
 * @return The BukkitTask representing the scheduled task.
 * @see delayTask
 */
fun delayTask(ticks: Int = 1, async: Boolean, runnable: BukkitRunnable.() -> Unit) =
    delayTask(ticks, TimeUnit.TICKS, async, runnable)

/**
 * Schedules a repeating task to be executed by the Bukkit scheduler.
 *
 * @param delay The duration value for the initial delay.
 * @param period The duration value for the period between subsequent executions.
 * @param unit The TimeUnit representing the time unit of the delay and period. Default is milliseconds.
 * @param async Whether the task should be executed asynchronously. Default is `false`.
 * @param runnable The function representing the task to be executed.
 * @return The BukkitTask representing the scheduled task.
 */
fun repeatTask(
    delay: Int,
    period: Int,
    unit: TimeUnit = TimeUnit.TICKS,
    async: Boolean = false,
    runnable: BukkitRunnable.() -> Unit
) = if (async) {
    createBukkitRunnable(runnable).runTaskTimerAsynchronously(
        Dusk.plugin,
        unit.toTicks(delay.toLong()),
        unit.toTicks(period.toLong())
    )
} else {
    createBukkitRunnable(runnable).runTaskTimer(
        Dusk.plugin,
        unit.toTicks(delay.toLong()),
        unit.toTicks(period.toLong())
    )
}

/**
 * Schedules a repeating task to be executed by the Bukkit scheduler.
 *
 * @param periodTicks The number of ticks for the period between subsequent executions. Default is 1.
 * @param runnable The function representing the task to be executed.
 * @return The BukkitTask representing the scheduled task.
 * @see repeatTask
 */
fun repeatTask(periodTicks: Int = 1, runnable: BukkitRunnable.() -> Unit) =
    repeatTask(periodTicks, periodTicks, TimeUnit.TICKS, false, runnable)

/**
 * Schedules a repeating task to be executed by the Bukkit scheduler.
 *
 * @param periodTicks The number of ticks for the period between subsequent executions. Default is 1.
 * @param async Whether the task should be executed asynchronously.
 * @param runnable The function representing the task to be executed.
 * @return The BukkitTask representing the scheduled task.
 * @see repeatTask
 */
fun repeatTask(periodTicks: Int = 1, async: Boolean, runnable: BukkitRunnable.() -> Unit) =
    repeatTask(periodTicks, periodTicks, TimeUnit.TICKS, async, runnable)

/**
 * Schedules a repeating task to be executed by the Bukkit scheduler.
 *
 * @param period The duration value for the period between subsequent executions.
 * @param unit The TimeUnit representing the time unit of the period.
 * @param runnable The function representing the task to be executed.
 * @return The BukkitTask representing the scheduled task.
 * @see repeatTask
 */
fun repeatTask(period: Int, unit: TimeUnit, runnable: BukkitRunnable.() -> Unit) =
    repeatTask(period, period, unit, false, runnable)

/**
 * Schedules a repeating task to be executed by the Bukkit scheduler.
 *
 * @param period The duration value for the period between subsequent executions.
 * @param unit The TimeUnit representing the time unit of the period.
 * @param async Whether the task should be executed asynchronously.
 * @param runnable The function representing the task to be executed.
 * @return The BukkitTask representing the scheduled task.
 * @see repeatTask
 */
fun repeatTask(period: Int, unit: TimeUnit, async: Boolean, runnable: BukkitRunnable.() -> Unit) =
    repeatTask(period, period, unit, async, runnable)

/**
 * Schedules a repeating task to be executed by the Bukkit scheduler.
 *
 * @param delayTicks The number of ticks for the initial delay.
 * @param periodTicks The number of ticks for the period between subsequent executions.
 * @param async Whether the task should be executed asynchronously.
 * @param runnable The function representing the task to be executed.
 * @return The BukkitTask representing the scheduled task.
 * @see repeatTask
 */
fun repeatTask(delayTicks: Int, periodTicks: Int, async: Boolean, runnable: BukkitRunnable.() -> Unit) =
    repeatTask(delayTicks, periodTicks, TimeUnit.TICKS, async, runnable)

/**
 * Schedules a repeating task to be executed by the Bukkit scheduler.
 *
 * @param delay The duration value for the initial delay.
 * @param period The duration value for the period between subsequent executions.
 * @param unit The TimeUnit representing the time unit of the delay and period.
 * @param runnable The function representing the task to be executed.
 * @return The BukkitTask representing the scheduled task.
 * @see repeatTask
 */
fun repeatTask(delay: Int, period: Int, unit: TimeUnit, runnable: BukkitRunnable.() -> Unit) =
    repeatTask(delay, period, unit, false, runnable)

private fun createBukkitRunnable(runnable: BukkitRunnable.() -> Unit) =
    object : BukkitRunnable() {
        override fun run() {
            this.runnable()
        }
    }