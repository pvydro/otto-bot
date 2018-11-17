package com.flizzet.launcher;

import com.flizzet.chatwindow.ChatConversationArea;
import com.flizzet.chatwindow.ChatGuiBuilder;
import com.flizzet.chatwindow.ChatLogger;
import com.flizzet.chatwindow.ChatWindow;
import com.flizzet.debug.LoggerF;
import com.flizzet.main.MainThread;
import com.flizzet.otto.Otto;
import com.flizzet.rules.ResponseLoader;
import com.flizzet.rules.RuleLoader;
import com.flizzet.settings.CurrentSettings;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * Builds all gui elements in the launcher.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class LauncherBuilder {

	private static final LauncherBuilder INSTANCE = new LauncherBuilder();

	private GridPane grid;

	/** Private constructor for single use */
	private LauncherBuilder() {
		LoggerF.writeToLog("LauncherBuilder class created");
	}

	/** Returns an instance of the launcher builder */
	public static LauncherBuilder getInstance() {
		return INSTANCE;
	}

	/** Builds all gui elements */
	public void buildLauncher() {
		createGrid();																// ONTOP Set up GridPane
		Scene scene = new Scene(grid, 200, 375);						// ONTOP Set up scene
		Image headImage = null;
		ImageView headImageView;
		try {
			headImage = new Image("file:res/launcher/ottoHead.png");			// Create head Image
		} catch (Exception e) {
			LoggerF.logError(e);
		}
		headImageView = new ImageView();											// Create head ImageView
		headImageView.setImage(headImage);
		Text nameTitle = new Text("Name:");									// Name header
		nameTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
		TextField nameTextField = new TextField();									// Name input box
		Text genderTitle = new Text("Gender:");								// Gender header
		nameTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
		ChoiceBox genderChoice = new ChoiceBox<>(FXCollections.observableArrayList(	// Gender choice
				"Male", "Female")
		);
		Button launchBtn = new Button();											// Launch button
		launchBtn.setText("Launch");
		launchBtn.setOnAction(event -> {											// Lambda ActionListener
			if (!nameTextField.getText().trim().equals("") && !genderChoice.getSelectionModel().isEmpty()) {
				LauncherWindow.getInstance().getStage().close();					// Disabling launcher
				LauncherThread.getInstance().stopThread();
				LoggerF.writeToLog("** Launching OttoBot **");						// LOG
				MainThread.getInstance().startThread();								// Creating main thread
				CurrentSettings.NAME = nameTextField.getText();						// Set name
				CurrentSettings.GENDER = genderChoice.getSelectionModel().getSelectedItem().toString();
				RuleLoader.loadRules();												// Load rules
				ResponseLoader.loadResponses();										// Load responses
				ChatWindow.getInstance().buildWindow();								// Creating chat application
				ChatGuiBuilder.getInstance().buildChatGui();
				ChatLogger.openLog();												// Open chat log
				ChatConversationArea.getInstance().refresh();
				Otto.getInstance().respond("Hi " + CurrentSettings.NAME + "! I'm Otto, ask me anything about "
						+ CurrentSettings.SUBJECT.toLowerCase());					// Otto introduction
				LoggerF.writeToLog("New chat started at " +
						ChatLogger.getCurrentChatFile().getAbsolutePath());			// LOG
			}
		});
		grid.add(headImageView, 0, 0);							// Adding all elements
		grid.add(nameTitle, 0, 1);
		grid.add(nameTextField, 0, 2);
		grid.add(genderTitle, 0, 3);
		grid.add(genderChoice, 0, 4);
		grid.add(launchBtn, 0, 5);
		LauncherWindow.getInstance().getStage().setScene(scene);
		LoggerF.writeToLog("Launcher elements built");								// LOG

	}

	private void createGrid() {
		grid = new GridPane();														// Create grid pane
		grid.setAlignment(Pos.TOP_CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));
	}

}