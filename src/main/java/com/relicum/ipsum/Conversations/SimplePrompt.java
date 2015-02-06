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

import org.bukkit.conversations.*;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

/**
 * SimplePrompt single question and answer conversation using the
 * {@link org.bukkit.conversations.ConversationFactory}
 *
 * @author Relicum
 * @version 0.0.1
 */
public class SimplePrompt extends ValidatingPrompt {

    private static ConversationFactory factory;
    private final String question;
    private final ValidAnswer listener;

    public static void newPrompt(Player player, String question, ValidAnswer listener, Plugin plugin) {

        if (factory == null)
            factory = new ConversationFactory(plugin);

        if (!player.isConversing()) {

            Conversation conv = factory.withModality(false).withFirstPrompt(new SimplePrompt(listener, question)).withLocalEcho(true).buildConversation(player);

            conv.begin();
        }
    }

    public SimplePrompt(ValidAnswer listener, String question) {
        this.listener = listener;
        this.question = question;
    }

    @Override
    protected boolean isInputValid(ConversationContext conversationContext, String input) {
        return true;
    }

    @Override
    protected Prompt acceptValidatedInput(ConversationContext conversationContext, String input) {
        if ((input.equalsIgnoreCase("quit")) || (input.equalsIgnoreCase("stop")) || (input.equalsIgnoreCase("end"))) {
            return Prompt.END_OF_CONVERSATION;
        }
        if (this.listener.onAnswer(input)) {
            return Prompt.END_OF_CONVERSATION;
        }
        return this;
    }

    @Override
    public String getPromptText(ConversationContext conversationContext) {
        return this.question;
    }
}
