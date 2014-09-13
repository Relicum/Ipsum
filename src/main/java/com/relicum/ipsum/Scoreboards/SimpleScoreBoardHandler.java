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

import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

/**
 * Name: SCManager.java Created: 05 September 2014
 *
 * @author Relicum
 * @version 0.0.1
 */
public class SimpleScoreBoardHandler implements IScoreboardManager {

    private Scoreboard board;
    private String name;
    private boolean isSingle = false;
    private String objectiveName;

    /**
     * Instantiates a new SC manager.
     *
     * @param name the name
     */
    public SimpleScoreBoardHandler(String name) {
        Validate.notNull(name);
        this.name = name;
        this.board = Bukkit.getScoreboardManager().getNewScoreboard();
    }

    /**
     * Instantiates a new SC manager.
     *
     * @param name       the name
     * @param singleName the single name of the objective
     */
    public SimpleScoreBoardHandler(String name, String singleName) {
        Validate.notNull(name);
        Validate.notNull(singleName);
        this.name = name;
        this.board = Bukkit.getScoreboardManager().getNewScoreboard();
        this.objectiveName = singleName;
        this.setIsSingleObjective(true);

    }

    /**
     * Is board null
     *
     * @return {@link java.lang.Boolean} true if the board is null, false if the board is not
     */
    public boolean isBoardNull() {
        try {
            if (getScoreboard() != null) {
                return false;
            }
        } catch (Exception ignored) {

        }

        return true;
    }

    /**
     * Gets scoreboard.
     *
     * @return the scoreboard
     */
    @Override
    public Scoreboard getScoreboard() {
        return board;
    }

    /**
     * Gets scoreboard plugin identifiably name.
     *
     * @return the scoreboard name used by a plugin to identify the scoreboard
     */
    @Override
    public String getScoreboardName() {
        return name;
    }

    /**
     * Get if this scoreboard only has a single objective.
     * <p>This is reduces the amount of code needed as the class will automatically refer to the objective without needing
     * to have a name or displayslot past to it.
     */
    @Override
    public boolean isSingleObjective() {
        return isSingle;
    }

    /**
     * Set if this scoreboard only has a single objective.
     * <p>This is reduces the amount of code needed as the class will automatically refer to the objective without needing
     * to have a name or displayslot past to it.
     *
     * @param single the obj
     */
    public void setIsSingleObjective(boolean single) {

        Validate.notNull(single);
        this.isSingle = single;
    }


    /**
     * Gets single objective name.
     *
     * @return the single objective name
     */
    @Override
    public String getObjectiveName() {
        return this.objectiveName;
    }


    /**
     * Gets entry.
     *
     * @return the entry
     */
    @Override
    public Score getEntry() {
        return null;
    }

    /**
     * Remove entry.
     *
     * @param score the score
     */
    @Override
    public void removeEntry(Score score) {
        getObjective().getScoreboard().resetScores(score.getEntry());
    }

    /**
     * Remove all entries.
     *
     * @param objective the objective
     */
    @Override
    public void removeAllEntries(Objective objective) {


    }

    /**
     * Remove all entries.
     */
    @Override
    public void removeAllEntries() {
        getObjective().unregister();

    }

    /**
     * Delete scoreboard.
     */
    @Override
    public void deleteScoreboard() {

    }


}
