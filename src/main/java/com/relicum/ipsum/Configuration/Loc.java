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

package com.relicum.ipsum.Configuration;

import lombok.*;
import net.cubespace.Yamler.Config.Config;
import org.bukkit.Bukkit;
import org.bukkit.Location;

/**
 * Name: Loc.java Created: 06 August 2014
 *
 * @author Relicum
 * @version 0.0.1
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Loc extends Config {

    private int X;
    private int Y;
    private int Z;
    private float yaw;
    private float pitch;
    private String world;

    @Override
    public String toString() {

        return world + "," + X + "," + Y + "," + Z + "," + yaw + "," + pitch;
    }

    public Location getLocation() {
        return new Location(Bukkit.getWorld(world), X, Y, Z, yaw, pitch);
    }
}
