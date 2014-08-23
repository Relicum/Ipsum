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

import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

/**
 * Name: Board.java Created: 22 August 2014
 *
 * @author Relicum
 * @version 0.0.1
 */
public class Board implements IBoard {

    public String boardName;
    public String objectiveName;
    public String objectiveCriteria;
    public String displaySlot;
    public String displayName;
    public Scoreboard scoreboard;
    public Objective objective;

    /**
     * Set a Unique Name for the scoreboard this is for your reference
     *
     * @param boardName {@link String} name of the ScoreBoard
     * @return the IBoard used to chain methods together;
     */
    @Override
    public IBoard setBoardName(String boardName) {

        this.boardName = boardName;
        return this;
    }

    /**
     * The same of the objective to be used on the scoreboard
     *
     * @param objectiveName {@link String} the objectives name
     * @return the IBoard used to chain methods together;
     */
    @Override
    public IBoard setObjectiveName(String objectiveName) {

        this.objectiveName = objectiveName;
        return this;
    }

    /**
     * The objectives Criteria to be used when registering
     *
     * @param criteria {@link String} as the criteria type
     * @return the IBoard used to chain methods together;
     */
    @Override
    public IBoard setObjectiveCriteria(String criteria) {

        this.objectiveCriteria = criteria;
        return this;
    }

    /**
     * Set The objectives DisplayName.
     * Appears as the ScoreBoards Title
     *
     * @param displayName {@link String} , the objectives displayName
     * @return the IBoard used to chain methods together;
     */
    @Override
    public IBoard setDisplayName(String displayName) {

        this.displayName = displayName;
        return this;
    }

    /**
     * The display slot the ScoreBoard will be displayed
     *
     * @param slot the display slot
     * @return the IBoard used to chain methods together;
     */
    @Override
    public IBoard setDisPlaySlot(String slot) {

        this.displaySlot = (DisplaySlot.valueOf(slot).name());
        return this;
    }

    @Override
    public String getBoardName() {

        return this.boardName;
    }

    @Override
    public String getObjectiveName() {

        return this.objectiveName;
    }

    @Override
    public String getObjectiveCriteria() {

        return this.objectiveCriteria;
    }

    @Override
    public String getDisplayName() {

        return this.displayName;
    }

    @Override
    public String getDisplaySlot() {

        return this.displaySlot;
    }

    public Board(Scoreboard scoreboard) {
        this.scoreboard = scoreboard;
    }

    public Score getScore(String name) {

        return this.objective.getScore(name);
    }

    public Scoreboard build() {

        this.objective = this.scoreboard.registerNewObjective(getObjectiveName(), getObjectiveCriteria());
        this.objective.setDisplayName(getDisplayName());
        this.objective.setDisplaySlot(DisplaySlot.valueOf(getDisplaySlot()));
        return scoreboard;

    }

}
