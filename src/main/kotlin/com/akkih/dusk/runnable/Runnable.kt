package com.akkih.dusk.runnable

import com.akkih.dusk.Dusk
import org.bukkit.scheduler.BukkitRunnable

/**
 * Utility object for handling runnables.
 *
 * @author https://github.com/flytegg/twilight
 */
object Runnable {
    /**
     * Schedules a synchronous task to be executed by the Bukkit scheduler.
     *
     * @param runnable The function representing the task to be executed.
     * @return The BukkitTask representing the scheduled task.
     */
    fun sync(runnable: BukkitRunnable.() -> Unit) = createBukkitRunnable(runnable).runTask(Dusk.plugin)

    /**
     * Schedules an asynchronous task to be executed by the Bukkit scheduler.
     *
     * @param runnable The function representing the task to be executed.
     * @return The BukkitTask representing the scheduled task.
     */
    fun async(runnable: BukkitRunnable.() -> Unit) = createBukkitRunnable(runnable).runTaskAsynchronously(Dusk.plugin)

    /**
     * Schedules a delayed task to be executed by the Bukkit scheduler.
     *
     * @param value The duration value for the delay.
     * @param unit The TimeUnit representing the time unit of the delay (default: MILLISECONDS).
     * @param async Whether the task should be executed asynchronously (default: false).
     * @param runnable The function representing the task to be executed.
     * @return The BukkitTask representing the scheduled task.
     */
    fun delay(
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
     * @param ticks The number of ticks for the delay (default: 1).
     * @param runnable The function representing the task to be executed.
     * @return The BukkitTask representing the scheduled task.
     * @see delay
     */
    fun delay(ticks: Int = 1, runnable: BukkitRunnable.() -> Unit) = delay(ticks, TimeUnit.TICKS, false, runnable)

    /**
     * Schedules a delayed task to be executed by the Bukkit scheduler.
     *
     * @param ticks The number of ticks for the delay (default: 1).
     * @param async Whether the task should be executed asynchronously.
     * @param runnable The function representing the task to be executed.
     * @return The BukkitTask representing the scheduled task.
     * @see delay
     */
    fun delay(ticks: Int = 1, async: Boolean, runnable: BukkitRunnable.() -> Unit) =
        delay(ticks, TimeUnit.TICKS, async, runnable)

    /**
     * Schedules a repeating task to be executed by the Bukkit scheduler.
     *
     * @param delay The duration value for the initial delay.
     * @param period The duration value for the period between subsequent executions.
     * @param unit The TimeUnit representing the time unit of the delay and period (default: MILLISECONDS).
     * @param async Whether the task should be executed asynchronously (default: false).
     * @param runnable The function representing the task to be executed.
     * @return The BukkitTask representing the scheduled task.
     */
    fun repeat(
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
     * @param periodTicks The number of ticks for the period between subsequent executions (default: 1).
     * @param runnable The function representing the task to be executed.
     * @return The BukkitTask representing the scheduled task.
     * @see repeat
     */
    fun repeat(periodTicks: Int = 1, runnable: BukkitRunnable.() -> Unit) =
        repeat(periodTicks, periodTicks, TimeUnit.TICKS, false, runnable)

    /**
     * Schedules a repeating task to be executed by the Bukkit scheduler.
     *
     * @param periodTicks The number of ticks for the period between subsequent executions (default: 1).
     * @param async Whether the task should be executed asynchronously.
     * @param runnable The function representing the task to be executed.
     * @return The BukkitTask representing the scheduled task.
     * @see repeat
     */
    fun repeat(periodTicks: Int = 1, async: Boolean, runnable: BukkitRunnable.() -> Unit) =
        repeat(periodTicks, periodTicks, TimeUnit.TICKS, async, runnable)

    /**
     * Schedules a repeating task to be executed by the Bukkit scheduler.
     *
     * @param period The duration value for the period between subsequent executions.
     * @param unit The TimeUnit representing the time unit of the period.
     * @param runnable The function representing the task to be executed.
     * @return The BukkitTask representing the scheduled task.
     * @see repeat
     */
    fun repeat(period: Int, unit: TimeUnit, runnable: BukkitRunnable.() -> Unit) =
        repeat(period, period, unit, false, runnable)

    /**
     * Schedules a repeating task to be executed by the Bukkit scheduler.
     *
     * @param period The duration value for the period between subsequent executions.
     * @param unit The TimeUnit representing the time unit of the period.
     * @param async Whether the task should be executed asynchronously.
     * @param runnable The function representing the task to be executed.
     * @return The BukkitTask representing the scheduled task.
     * @see repeat
     */
    fun repeat(period: Int, unit: TimeUnit, async: Boolean, runnable: BukkitRunnable.() -> Unit) =
        repeat(period, period, unit, async, runnable)

    /**
     * Schedules a repeating task to be executed by the Bukkit scheduler.
     *
     * @param delayTicks The number of ticks for the initial delay.
     * @param periodTicks The number of ticks for the period between subsequent executions.
     * @param async Whether the task should be executed asynchronously.
     * @param runnable The function representing the task to be executed.
     * @return The BukkitTask representing the scheduled task.
     * @see repeat
     */
    fun repeat(delayTicks: Int, periodTicks: Int, async: Boolean, runnable: BukkitRunnable.() -> Unit) =
        repeat(delayTicks, periodTicks, TimeUnit.TICKS, async, runnable)

    /**
     * Schedules a repeating task to be executed by the Bukkit scheduler.
     *
     * @param delay The duration value for the initial delay.
     * @param period The duration value for the period between subsequent executions.
     * @param unit The TimeUnit representing the time unit of the delay and period.
     * @param runnable The function representing the task to be executed.
     * @return The BukkitTask representing the scheduled task.
     * @see repeat
     */
    fun repeat(delay: Int, period: Int, unit: TimeUnit, runnable: BukkitRunnable.() -> Unit) =
        repeat(delay, period, unit, false, runnable)

    private fun createBukkitRunnable(runnable: BukkitRunnable.() -> Unit) =
        object : BukkitRunnable() {
            override fun run() {
                this.runnable()
            }
        }
}