package com.flizzet.chatwindow;

import com.flizzet.debug.LoggerF;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * TextArea that displays the full conversation.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class ChatConversationArea {

	private static final ChatConversationArea INSTANCE = new ChatConversationArea();
	private GridPane gridPane;
	private TextArea chatArea;

	/** Default instantiable constructor */
	private ChatConversationArea() {
	}

	/** Returns instance of ChatConversationArea */
	public static ChatConversationArea getInstance() {
		return INSTANCE;
	}

	public void buildChatArea() {

		chatArea = new TextArea();
		chatArea.setEditable(false);
		chatArea.setWrapText(true);
		chatArea.setMinWidth(780);		// FIXME Change this for adding a side menu in future updates
		chatArea.setPrefColumnCount(43);
		chatArea.setPrefRowCount(25);

		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setContent(chatArea);

		gridPane.add(chatArea, 0, 0);

	}

	/** Creates GridPane */
	public void createGridPane() {
		gridPane = new GridPane();											// Create gridPane pane
		gridPane.setAlignment(Pos.BOTTOM_RIGHT);
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		gridPane.setPadding(new Insets(10, 10, 10, 10));
	}

	/** Reloads the text file to display any newly added text */
	public void refresh() {

		String newText = "";									// Text used in chat area

		try {
			Scanner scanner = new Scanner(ChatLogger.getCurrentChatFile());
			while (scanner.hasNextLine()) {
				newText = newText + "\n" + scanner.nextLine();	// Check if lines exist, if so add to chat
			}
		} catch (FileNotFoundException e) {
			LoggerF.logError(e);
		}

		chatArea.setText(newText);
		chatArea.appendText("");

	}

	public GridPane getGridPane() {
		return gridPane;
	}

}
