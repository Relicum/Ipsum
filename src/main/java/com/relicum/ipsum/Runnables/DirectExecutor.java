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

import java.util.Collections;
import java.util.List;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;

/**
 * DirectExecutor is a single, same thread Task {@link java.lang.Runnable}
 * executor.
 * <p>
 * This class creates an always on executor that running
 * {@link java.lang.Runnable} tasks from the same thread it is created in. Any
 * class implementing {@link java.lang.Runnable} can be passed to it and will be
 * run immediately or as soon as its possible. This is great for performance as
 * it not required to start a new thread to run the task it just reuses the
 * current on.
 * <p>
 * Be careful not to overload this as it does not have a large queue and it is
 * not thread safe in any way. All Synchronization will need to be done by the
 * tasks themselves.
 * <p>
 * More detail on the Executor itself can be found here
 * {@link java.util.concurrent.AbstractExecutorService}
 *
 * @author Relicum
 * @version 0.0.1
 */
public class DirectExecutor extends AbstractExecutorService {

    private boolean terminated;

    /**
     * Instantiates a new Direct executor.
     */
    public DirectExecutor() {

    }

    /**
     * Executes the given command at some time in the future. The command will
     * execute in the calling thread, at the discretion of the {@code Executor}
     * implementation. Ideal to quick runnables that need to run on the main mc
     * thread, without having to start a new thread each time. Also includes a
     * small queue. THIS IS NOT THREAD SAFE.
     *
     * @param command the runnable task
     * @throws java.util.concurrent.RejectedExecutionException if this task
     *         cannot be accepted for execution
     * @throws NullPointerException if command is null
     */
    @Override
    public void execute(@Nonnull Runnable command) {
        command.run();
    }

    /**
     * Initiates an orderly shutdown in which previously submitted tasks are
     * executed, but no new tasks will be accepted. Invocation has no additional
     * effect if already shut down.
     * <p>
     * This method does not wait for previously submitted tasks to complete
     * execution. Use {@link #awaitTermination awaitTermination} to do that.
     *
     * @throws SecurityException if a security manager exists and shutting down
     *         this ExecutorService may manipulate threads that the caller is
     *         not permitted to modify because it does not hold
     *         {@link RuntimePermission}{@code ("modifyThread")}, or the
     *         security manager's {@code checkAccess} method denies access.
     */
    @Override
    public void shutdown() {
        terminated = true;
    }

    /**
     * Attempts to stop all actively executing tasks, halts the processing of
     * waiting tasks, and returns a list of the tasks that were awaiting
     * execution.
     * <p>
     * This method does not wait for actively executing tasks to terminate. Use
     * {@link #awaitTermination awaitTermination} to do that.
     * <p>
     * There are no guarantees beyond best-effort attempts to stop processing
     * actively executing tasks. For example, typical implementations will
     * cancel via {@link Thread#interrupt}, so any task that fails to respond to
     * interrupts may never terminate.
     *
     * @return list of tasks that never commenced execution
     * @throws SecurityException if a security manager exists and shutting down
     *         this ExecutorService may manipulate threads that the caller is
     *         not permitted to modify because it does not hold
     *         {@link RuntimePermission}{@code ("modifyThread")}, or the
     *         security manager's {@code checkAccess} method denies access.
     */
    @Override
    public List<Runnable> shutdownNow() {
        return Collections.emptyList();
    }

    /**
     * Returns {@code true} if this executor has been shut down.
     *
     * @return {@code true} if this executor has been shut down
     */
    @Override
    public boolean isShutdown() {
        return terminated;
    }

    /**
     * Returns {@code true} if all tasks have completed following shut down.
     * Note that {@code isTerminated} is never {@code true} unless either
     * {@code shutdown} or {@code shutdownNow} was called first.
     *
     * @return {@code true} if all tasks have completed following shut down
     */
    @Override
    public boolean isTerminated() {
        return terminated;
    }

    /**
     * Blocks until all tasks have completed execution after a shutdown request,
     * or the timeout occurs, or the current thread is interrupted, whichever
     * happens first.
     *
     * @param timeout the maximum time to wait
     * @param unit the time unit of the timeout argument
     * @return {@code true} if this executor terminated and {@code false} if the
     *         timeout elapsed before termination
     * @throws InterruptedException if interrupted while waiting
     */
    @Override
    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        shutdown(); // TODO ok to call shutdown? what if the client never called
                    // shutdown???
        return terminated;
    }
}
