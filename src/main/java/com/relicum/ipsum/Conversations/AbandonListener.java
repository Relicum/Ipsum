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

package com.relicum.ipsum.Conversations;

import org.bukkit.ChatColor;
import org.bukkit.conversations.ConversationAbandonedEvent;
import org.bukkit.conversations.ConversationAbandonedListener;
import org.bukkit.conversations.ExactMatchConversationCanceller;
import org.bukkit.conversations.InactivityConversationCanceller;

/**
 * Name: AbandonListener.java Created: 30 January 2015
 *
 * @author Relicum
 * @version 0.0.1
 */
public class AbandonListener implements ConversationAbandonedListener {

    private final String gracefulMessage;
    private final String inactiveMessage;
    private final String exactMessage;

    public AbandonListener(String exactMessage, String gracefulMessage, String inactiveMessage) {
        this.exactMessage = exactMessage;
        this.gracefulMessage = gracefulMessage;
        this.inactiveMessage = inactiveMessage;
    }

    @Override
    public void conversationAbandoned(ConversationAbandonedEvent event) {

        if (event.gracefulExit()) {

            event.getContext().getForWhom().sendRawMessage(gracefulMessage);

        } else {

            if (event.getCanceller() instanceof InactivityConversationCanceller) {
                event.getContext().getForWhom().sendRawMessage(inactiveMessage);

                return;
            }

            if (event.getCanceller() instanceof ExactMatchConversationCanceller) {
                event.getContext().getForWhom().sendRawMessage(exactMessage);
                return;
            }

            event.getContext().getForWhom().sendRawMessage(ChatColor.RED + event.getCanceller().toString());

        }

    }
}
