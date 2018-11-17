package com.flizzet.chatwindow;

import com.flizzet.debug.LoggerF;
import com.flizzet.otto.Otto;
import com.flizzet.user.User;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

/**
 * Builds the GUI elements of the chat window.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class ChatGuiBuilder {

	private static final ChatGuiBuilder INSTANCE = new ChatGuiBuilder();
	private TextField chatInputField;	// Message typing area

	/** Private constructor for single use */
	private ChatGuiBuilder() {
		LoggerF.writeToLog("ChatGuiBuilder class created");
	}

	/** Returns an instance of the ChatGuiBuilder */
	public static ChatGuiBuilder getInstance() {
		return INSTANCE;
	}

	/** Builds gui elements */
	public void buildChatGui() {

		ChatConversationArea chatArea = ChatConversationArea.getInstance();

		chatInputField = new TextField();									 // Chat input box
		chatInputField.setPromptText("Say anything!");

		chatInputField.setOnKeyPressed(e -> {
			if (e.getCode().equals(KeyCode.ENTER))
			{
				sendPressed(chatInputField.getText());
			}
		});

		chatArea.createGridPane();											// Create grid pane
		chatArea.getGridPane().add(chatInputField, 0, 1);					// Add input field
		chatArea.buildChatArea();											// Build chat area

		StackPane rootPane = new StackPane();								// MainPane
		rootPane.getChildren().add(chatArea.getGridPane());

		Scene scene = new Scene(rootPane, 800, 400);			// ONTOP Set up scene

		ChatWindow.getInstance().getStage().setScene(scene);
		ChatWindow.getInstance().getStage().show();							// Make stage visible

		Button sendButton = new Button("Send");						// Send button
		sendButton.setOnAction(event -> sendPressed(chatInputField.getText()));
		sendButton.setTranslateX(365);
		sendButton.setTranslateY(176);
		rootPane.getChildren().add(sendButton);								// Add send button

		LoggerF.writeToLog("ChatGui elements built");						// LOG

	}

	/** Called when the send button is pressed and resets the chatInputField */
	private void sendPressed(String message) {
		User.getInstance().say(message);
		chatInputField.setText("");
	}

}
