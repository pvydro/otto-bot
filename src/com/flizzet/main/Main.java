package com.flizzet.main;

import com.flizzet.debug.LoggerF;
import com.flizzet.launcher.LauncherBuilder;
import com.flizzet.launcher.LauncherThread;
import com.flizzet.launcher.LauncherWindow;
import com.flizzet.searching.GoogleSearcher;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Main class with main method.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class Main extends Application {

	public static void main(String[] args) {

		LoggerF.openLogger(); 						// Write initial log

		LauncherThread.getInstance().startThread();	// Start launcher thread

		LoggerF.writeToLog("Main method success");

		launch(args);								// Start method

	}

	/** Runs on program start */
	@Override
	public void start(Stage primaryStage) {
		LauncherWindow.getInstance().buildWindow(primaryStage);		// Create window
		LauncherBuilder.getInstance().buildLauncher();				// Create GUI elements
	}

	/** Runs on program close */
	@Override
	public void stop() throws Exception
	{

		LoggerF.writeToLog("Closing application");

		super.stop();
		LauncherThread.getInstance().stopThread();	// Stops threads so application stops running
		MainThread.getInstance().stopThread();

		LoggerF.writeToLog("Application closed");	// LOG
	}

}
