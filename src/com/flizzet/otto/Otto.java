package com.flizzet.otto;

import com.flizzet.chatwindow.ChatConversationArea;
import com.flizzet.chatwindow.ChatLogger;
import com.flizzet.settings.CurrentSettings;
import com.flizzet.utils.DateUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Chat bot.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class Otto {

	private static final Otto INSTANCE = new Otto();

	/** Private constructor for single use */
	private Otto() {
	}

	/** Returns instance of Otto */
	public static Otto getInstance() {
		return INSTANCE;
	}

	/** Gives response and adds to file */
	public void respond(String message) {
		message = checkAndReplace(message);				// Replace all keywords
		ChatLogger.writeToLog("Otto: " + message);		// Send it to chat log
		ChatConversationArea.getInstance().refresh();	// Refresh chat window
	}

	/** Replaces keywords in text with real words */
	private String checkAndReplace(String message) {

		String[] allKeywords = new String[] {
			"<SUBJECT>", "<NAME>", "<TIME>", "<DATE>"
		};

		for (String keyword : allKeywords) {			// For every keyword
			if (message.contains(keyword)) {			// Check if the message contains it
				switch (keyword) {						// And replace it with the correct String
					case "<SUBJECT>":
						message = message.replaceAll(keyword, CurrentSettings.SUBJECT);
						break;
					case "<NAME>":
						message = message.replaceAll(keyword, CurrentSettings.NAME);
						break;
					case "<TIME>":
						message = message.replaceAll(keyword, DateUtils.getTime(false));
						break;
					case "<DATE>":
						message = message.replaceAll(keyword, DateUtils.getDate());
				}
			}
		}

		return message;

	}
}
