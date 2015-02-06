/*
 * Ipsum is a rapid development API for Minecraft, developer by Relicum
 * Copyright (C) 2015.  Chris Lutte
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

package com.relicum.ipsum.Events;

/**
 * CancelableBaseEvent
 *
 * @author Relicum
 * @version 0.0.1
 */
abstract class CancelableBaseEvent extends BaseEvent {

    protected boolean canceled;

    public CancelableBaseEvent() {
        this.canceled = false;
    }

    public boolean isCancelled() {
        return this.canceled;
    }

    public void setCancelled(boolean cancel) {
        this.canceled = cancel;
    }
}
