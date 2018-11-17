package com.flizzet.chatwindow;

import com.flizzet.debug.LoggerF;
import javafx.stage.Stage;

/**
 * Builds the chat window.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class ChatWindow {

	private static final ChatWindow INSTANCE = new ChatWindow();
	private Stage chatStage;

	/** Private constructor for single use */
	private ChatWindow() {
		LoggerF.writeToLog("ChatWindow class created");
	}

	/** Returns an instance of the ChatWindow */
	public static ChatWindow getInstance() {
		return INSTANCE;
	}

	/** Builds window */
	public void buildWindow() {

		chatStage = new Stage();

		chatStage.setTitle("OttoBot");
		chatStage.setResizable(false);

		LoggerF.writeToLog("Chat window created");		// LOG

	}

	public Stage getStage() {
		return chatStage;
	}

}
