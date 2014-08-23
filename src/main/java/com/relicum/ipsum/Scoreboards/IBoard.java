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

package com.relicum.ipsum.Scoreboards;

/**
 * Generic Scoreboard Interface
 *
 * @author Relicum
 * @version 0.0.1
 */
public interface IBoard {

    /**
     * Set a Unique Name for the scoreboard this is for your reference
     *
     * @param boardName {@link java.lang.String} name of the ScoreBoard
     * @return the IBoard used to chain methods together;
     */
    public IBoard setBoardName(String boardName);

    /**
     * The same of the objective to be used on the scoreboard
     *
     * @param objectiveName {@link java.lang.String} the objectives name
     * @return the IBoard used to chain methods together;
     */
    public IBoard setObjectiveName(String objectiveName);

    /**
     * The objectives Criteria to be used when registering
     *
     * @param criteria {@link java.lang.String} as the criteria type
     * @return the IBoard used to chain methods together;
     */
    public IBoard setObjectiveCriteria(String criteria);

    /**
     * Set The objectives DisplayName.
     * Appears as the ScoreBoards Title
     *
     * @param displayName {@link java.lang.String} , the objectives displayName
     * @return the IBoard used to chain methods together;
     */
    public IBoard setDisplayName(String displayName);

    /**
     * The display slot the ScoreBoard will be displayed
     *
     * @param slot the display slot
     * @return the IBoard used to chain methods together;
     */
    public IBoard setDisPlaySlot(String slot);

    public String getBoardName();

    public String getObjectiveName();

    public String getObjectiveCriteria();

    public String getDisplayName();

    public String getDisplaySlot();

}
