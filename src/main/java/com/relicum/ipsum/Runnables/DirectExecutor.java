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

import javax.annotation.Nonnull;
import java.util.concurrent.Executor;

/**
 * DirectExecutor is a single, same thread Task {@link java.lang.Runnable} executor.
 * <p>This class creates an always on executor that running {@link java.lang.Runnable} tasks from the same thread it is created in.
 * Any class implementing {@link java.lang.Runnable} can be passed to it and will be run immediately or as soon as its possible.
 * This is great for performance as it not required to start a new thread to run the task it just reuses the current on.
 * <p>Be careful not to overload this as it does not have a large queue and it is not thread safe in any way. All Synchronization
 * will need to be done by the tasks themselves.
 * <p>More detail on the Executor itself can be found here {@link java.util.concurrent.Executor}
 *
 * @author Relicum
 * @version 0.0.1
 */
public class DirectExecutor implements Executor {

    /**
     * Instantiates a new Direct executor.
     */
    public DirectExecutor() {

    }

    /**
     * Executes the given command at some time in the future.  The command
     * will execute in the calling thread, at the discretion of the {@code Executor} implementation.
     * Ideal to quick runnables that need to run on the main mc thread, without having to start a new thread each time.
     * Also includes a small queue. THIS IS NOT THREAD SAFE.
     *
     * @param command the runnable task
     * @throws java.util.concurrent.RejectedExecutionException if this task cannot be
     *                                                         accepted for execution
     * @throws NullPointerException                            if command is null
     */
    @Override
    public void execute(@Nonnull Runnable command) {
        command.run();
    }
}
