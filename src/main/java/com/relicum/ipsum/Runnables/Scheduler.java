/*
 * Ipsum is a rapid development API for Minecraft, developer by Relicum
 * Copyright (C) 2014.  Chris Lutte
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.relicum.ipsum.Runnables;

import java.util.concurrent.*;

/**
 * An {@link java.util.concurrent.ScheduledThreadPoolExecutor} that can schedule commands to run after a given
 * delay, or to execute periodically.
 * <p>The {@code runAfterDelay} method creates tasks with various delays
 * and return a task object that can be used to cancel or check
 * execution. The {@code runRepeatedly} method create and execute tasks
 * that run periodically until cancelled.
 */
public class Scheduler {


    private ScheduledThreadPoolExecutor timer;


    /**
     * Instantiates a new Scheduler.
     *
     * @param cores          the maximum number of core to use.
     * @param removeOnCancel the remove on cancel
     */
    public Scheduler(int cores, boolean removeOnCancel) {

        timer = new ScheduledThreadPoolExecutor(cores, Executors.privilegedThreadFactory());
        timer.setRemoveOnCancelPolicy(removeOnCancel);
    }

    /**
     * Creates and executes a one-shot action that becomes enabled
     * after the given delay.
     *
     * @param command the task to execute
     * @param delay   the time from now to delay execution
     * @param unit    the time unit of the delay parameter
     * @return a ScheduledFuture representing pending completion of
     * the task and whose {@code get()} method will return
     * {@code null} upon completion
     * @throws java.util.concurrent.RejectedExecutionException if the task cannot be
     *                                                         scheduled for execution
     * @throws NullPointerException                            if command is null
     */
    public ScheduledFuture<?> runAfterDelay(Runnable command, long delay, TimeUnit unit) {

        return timer.schedule(command, delay, unit);
    }

    /**
     * Creates and executes a periodic action that becomes enabled first
     * after the given initial delay, and subsequently with the given
     * period; that is executions will commence after
     * {@code initialDelay} then {@code initialDelay+period}, then
     * {@code initialDelay + 2 * period}, and so on.
     * If any execution of the task
     * encounters an exception, subsequent executions are suppressed.
     * Otherwise, the task will only terminate via cancellation or
     * termination of the executor.  If any execution of this task
     * takes longer than its period, then subsequent executions
     * may start late, but will not concurrently execute.
     *
     * @param command      the task to execute
     * @param initialDelay the time to delay first execution
     * @param period       the period between successive executions
     * @param unit         the time unit of the initialDelay and period parameters
     * @return a ScheduledFuture representing pending completion of
     * the task, and whose {@code get()} method will throw an
     * exception upon cancellation
     * @throws java.util.concurrent.RejectedExecutionException if the task cannot be
     *                                                         scheduled for execution
     * @throws NullPointerException                            if command is null
     * @throws IllegalArgumentException                        if period less than or equal to zero
     */
    public ScheduledFuture<?> runRepeatedly(Runnable command, long initialDelay, long period, TimeUnit unit) {

        return timer.scheduleAtFixedRate(command, initialDelay, period, unit);
    }

    /**
     * Executes {@code command} with zero required delay.
     * This has effect equivalent to
     * {@link java.util.concurrent.ScheduledThreadPoolExecutor#schedule(Runnable, long, TimeUnit)} schedule(command, 0, anyUnit)}.
     * Note that inspections of the queue and of the list returned by
     * {@code shutdownNow} will access the zero-delayed
     * {@link ScheduledFuture}, not the {@code command} itself.
     * <p>A consequence of the use of {@code ScheduledFuture} objects is
     * that {@link java.util.concurrent.ThreadPoolExecutor#afterExecute afterExecute} is always
     * called with a null second {@code Throwable} argument, even if the
     * {@code command} terminated abruptly.  Instead, the {@code Throwable}
     * thrown by such a task can be obtained via {@link java.util.concurrent.Future#get}.
     *
     * @param command the task to execute.
     * @throws java.util.concurrent.RejectedExecutionException at discretion of
     *                                                         {@code RejectedExecutionHandler}, if the task
     *                                                         cannot be accepted for execution because the
     *                                                         executor has been shut down
     * @throws NullPointerException                            from command
     */
    public void execute(Runnable command) {

        this.timer.execute(command);
    }

    public <T> Future<T> submit(Callable<T> task) {

        return timer.submit(task);
    }

    public java.util.List<Runnable> shutdownNow() {

        return timer.shutdownNow();
    }

    /**
     * Shutdown the Scheduler
     */
    public void shutdown() {
        timer.shutdown();
    }

    /**
     * Get the internal instance of {@link java.util.concurrent.ScheduledThreadPoolExecutor} object.
     *
     * @return the {@link java.util.concurrent.ScheduledThreadPoolExecutor}
     */
    public ScheduledThreadPoolExecutor getTimer() {
        return timer;
    }


}
