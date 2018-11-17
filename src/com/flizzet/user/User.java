package com.flizzet.user;

import com.flizzet.chatwindow.ChatConversationArea;
import com.flizzet.chatwindow.ChatLogger;
import com.flizzet.rules.RuleContainer;

/**
 * Gets user input etc.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class User {

	private static final User INSTANCE = new User();

	/** Private constructor for single used */
	private User() {
	}

	/** Returns instance of User */
	public static User getInstance() {
		return INSTANCE;
	}

	/** Sends message to chat and adds to file */
	public void say(String message) {
		ChatLogger.writeToLog("You: " + message);
		ChatConversationArea.getInstance().refresh();
		RuleContainer.getInstance().compare(message.replaceAll("[\\p{Punct}&&[^<>]]+", ""));
		System.out.println(message.replaceAll("[\\p{Punct}&&[^_-]]+", ""));
	}
}
