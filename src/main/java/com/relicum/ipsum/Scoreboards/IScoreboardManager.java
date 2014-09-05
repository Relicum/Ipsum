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
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

/**
 * Interface that provides a generic set of methods for managing a scoreboard in a standardized way.
 *
 * @author Relicum
 * @version 0.0.1
 */
public interface IScoreboardManager {

    /**
     * Gets scoreboard.
     *
     * @return the scoreboard
     */
    Scoreboard getScoreboard();

    /**
     * Gets scoreboard plugin identifiably name.
     *
     * @return the scoreboard name used by a plugin to identify the scoreboard
     */
    String getScoreboardName();

    /**
     * Get if this scoreboard only has a single objective.
     * <p>This is reduces the amount of code needed as the class will automatically refer to the objective without needing
     * to have a name or displayslot past to it.
     *
     * @return the {@link java.lang.Boolean} true if this scoreboard is set to have only a single objective. False and multiple objectives are allowed.
     */
    boolean isSingleObjective();

    /**
     * Gets objective.
     *
     * @param name the name
     * @return the objective
     */
    default Objective getObjective(String name) {

        Validate.notNull(name);

        return getScoreboard().getObjective(name);
    }

    /**
     * Gets objective.
     *
     * @param slot the slot
     * @return the objective
     */
    default Objective getObjective(DisplaySlot slot) {

        Validate.notNull(slot);

        return getScoreboard().getObjective(slot);
    }

    /**
     * Register new objective.
     *
     * @param name     the name
     * @param criteria the criteria
     * @return the objective
     */
    default Objective registerNewObjective(String name, String criteria) {

        Validate.notNull(name);
        Validate.notNull(criteria);

        return getScoreboard().registerNewObjective(name, criteria);
    }


    /**
     * Register new dummy objective.
     *
     * @param name the name
     * @return the objective
     */
    default Objective registerNewDummyObjective(String name) {

        Validate.notNull(name);

        return getScoreboard().registerNewObjective(name, "dummy");
    }

    /**
     * Register new dummy objective and set the displayName and DisplaySlot all at once.
     *
     * @param name        the objective name
     * @param displayName the objective display name
     * @param slot        the {@link org.bukkit.scoreboard.DisplaySlot}
     * @return the {@link org.bukkit.scoreboard.Objective}
     */
    default Objective registerNewDummyObjective(String name, String displayName, DisplaySlot slot) {

        Validate.notNull(name);

        getScoreboard().registerNewObjective(name, "dummy");
        getObjective(name).setDisplayName(displayName);
        getObjective(name).setDisplaySlot(slot);

        return getObjective(name);
    }

    /**
     * Register new dummy objective and set the displayName and DisplaySlot all at once.
     *
     * @param displayName the objective display name
     * @param slot        the {@link org.bukkit.scoreboard.DisplaySlot}
     * @return the {@link org.bukkit.scoreboard.Objective}
     */
    default Objective registerNewDummyObjective(String displayName, DisplaySlot slot) {

        Validate.notNull(displayName);
        Validate.notNull(slot);

        getScoreboard().registerNewObjective(getObjectiveName(), "dummy");
        getObjective().setDisplayName(displayName);
        getObjective().setDisplaySlot(slot);

        return getObjective();
    }

    /**
     * Clear all {@link org.bukkit.scoreboard.DisplaySlot} display slots from the scoreboard.
     */
    default void clearAllSlots() {

        try {
            getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
            getScoreboard().clearSlot(DisplaySlot.BELOW_NAME);
            getScoreboard().clearSlot(DisplaySlot.PLAYER_LIST);
        } catch (Exception ignored) {
            //Ignore any slots that are empty
        }
    }


    /**
     * Gets single objective name.
     *
     * @return the single objective name
     */
    String getObjectiveName();


    /**
     * Gets single objective registered to the scoreboard
     *
     * @return the objective
     */
    default Objective getObjective() {

        return getObjective(getObjectiveName());
    }


    /**
     * Add new score to a single objective scoreboard.
     *
     * @param entry the entry
     * @return the {@link org.bukkit.scoreboard.Score}
     * @throws java.lang.IllegalStateException if the manager is not in single objective mode.
     */
    default Score addNewScore(String entry) throws IllegalStateException {
        Validate.notNull(entry);

        if (!isSingleObjective()) {
            throw new IllegalStateException("You can not add a new score using this method as the scoreboard is marked as only having one objective");
        }

        return getObjective().getScore(entry);
    }

    /**
     * Add new score specifying the name of the {@link org.bukkit.scoreboard.Objective} to add it to.
     *
     * @param ObjName the {@link org.bukkit.scoreboard.Objective} name
     * @param entry   the entry
     * @return the {@link org.bukkit.scoreboard.Score}
     */
    default Score addNewScore(String ObjName, String entry) {
        Validate.notNull(entry);
        Validate.notNull(ObjName);

        return getObjective(ObjName).getScore(entry);
    }

    /**
     * Gets entry.
     *
     * @return the entry
     */
    Score getEntry();

    /**
     * Remove entry.
     *
     * @param score the score
     */
    void removeEntry(Score score);

    /**
     * Remove all entries.
     *
     * @param objective the objective
     */
    void removeAllEntries(Objective objective);

    /**
     * Remove all entries.
     */
    void removeAllEntries();

    /**
     * Delete scoreboard.
     */
    void deleteScoreboard();


}
