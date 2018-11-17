package com.flizzet.launcher;

import com.flizzet.debug.LoggerF;
import javafx.stage.Stage;

/**
 * Creates the window.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class LauncherWindow {

	private final static LauncherWindow INSTANCE = new LauncherWindow();
	private Stage launcherStage;

	/** Private constructor for single use */
	private LauncherWindow() {
		LoggerF.writeToLog("LauncherWindow class created");
	}

	/** Returns an instance of the LauncherWindow */
	public static LauncherWindow getInstance() {
		return INSTANCE;
	}

	/** Creates JavaFX window and makes window visible */
	public void buildWindow(Stage stage) {
		this.launcherStage = stage;

		launcherStage.setTitle("OttoBot Launcher");
		launcherStage.setResizable(false);

		LoggerF.writeToLog("Launcher window created");		// LOG

		launcherStage.show();								// Makes window visible
	}

	public Stage getStage() {
		return launcherStage;
	}
}
