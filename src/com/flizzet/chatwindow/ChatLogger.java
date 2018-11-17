package com.flizzet.chatwindow;

import com.flizzet.debug.LoggerF;
import com.flizzet.settings.CurrentSettings;
import com.flizzet.utils.DateUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Saves the chat to a file and allows retrieval.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class ChatLogger {

	private static File chatFile;

	/** Suppress default constructor for noninstantiability */
	private ChatLogger() {
		throw new AssertionError();
	}

	/** Opens the chat log to be written to */
	public static void openLog() {

		PrintWriter logWriter = null;

		try {
			File chatDir = new File("output/chatLogs/");
			boolean result = true;
			if (!chatDir.exists()) {
				result = chatDir.mkdirs();                // If directory doesn't exist, try and create one
			}

			long fileCount = Files.list(Paths.get("output/chatLogs")).count();

			/* ------ */

			chatFile = new File("output/chatLogs/chat" + fileCount + ".txt");

			if (!chatFile.exists()) {
				result = chatFile.createNewFile();        // If chatFile doesn't exist, try and create one
			}

			if (!result) {
				LoggerF.logError(new IOException());    // If directory or chatFile creation fails, throw IOException
			}

			logWriter = new PrintWriter(chatFile);
			logWriter.write("New OttoBot chat at " + DateUtils.getTime(false) + ".\n");
			logWriter.write("Under the " + CurrentSettings.GENDER.toLowerCase() + " name: " + CurrentSettings.NAME + ".\n");
			logWriter.write("With the subject: " + CurrentSettings.SUBJECT + ".\n");
			logWriter.write("----\n");

		} catch (IOException e) {
			LoggerF.logError(e);
		} finally {
			if (logWriter != null) {
				logWriter.close();
			}
		}

	}

	/** Writes information to the logger */
	public static void writeToLog(String message) {

		FileWriter tempWriter = null;

		try {
			tempWriter = new FileWriter(chatFile, true); 	// New writer for log file, second parameter is "append"
			tempWriter.write("\n" + DateUtils.getTime(false) + " | ");
			tempWriter.write(message);
		} catch (IOException e) {
			LoggerF.logError(e);
		} finally {
			if (tempWriter != null) {
				try {
					tempWriter.close();
				} catch (IOException e) {
					LoggerF.logError(e);
				}
			}
		}
	}

	/** Returns the current chat log */
	public static File getCurrentChatFile() {
		return chatFile;
	}

}