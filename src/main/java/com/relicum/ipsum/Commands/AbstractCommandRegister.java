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

package com.relicum.ipsum.Commands;

/**
 * Abstract command register.
 * <p>Extend this class to create a new command register.
 */
public abstract class AbstractCommandRegister {

/*    public Map<String, SimpleCommand> commandMap = new HashMap<>();

    public T plugin;

    public AbstractCommandRegister(T plug) {

        this.plugin = plug;


    }

    public T getPlugin() {
        return plugin;
    }

    public abstract PermissionManager permissionManager();

    public void registerCommand(SimpleCommand command) {

        if (permissionManager().registerPermission(command.getPermission(), command.getDescription(), command.getParentPermission(), PermissionDefault.OP)) {
            System.out.println("Successfully created new permission " + command.getPermission() + " with parent permission " + command.getParentPermission());
        } else {
            System.out.println("Error creating new Permission");
        }

        command.registerCommand();


        commandMap.put(command.getCmdName(), command);

    }

    public void clearAllCommands() {

        this.commandMap.clear();
    }*/

}
