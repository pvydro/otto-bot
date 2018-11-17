package com.flizzet.debug;

import com.flizzet.utils.DateUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * Logs the application run process to an output file,
 * </br> also logs errors to files and console.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class LoggerF {

	private static File logFile; // File with all basic logs
	private static long currentTime = System.currentTimeMillis();

	/** Suppress default constructor for noninstantiability */
	private LoggerF() {
		throw new AssertionError();
	}

	/** Opens the logger for writing */
	public static void openLogger() {

		PrintWriter logWriter = null;

		try {
			File logDir = new File("output/logs/");
			logFile = new File("output/logs/logFile.txt");

			boolean result = true;

			if(!logDir.exists()) {
				result = logDir.mkdirs();				// If logDir doesn't exist, try and create one
			}
			if(!logFile.exists()) {
				result = logFile.createNewFile();		// If logFile doesn't exist, try and create one
			}

			if(!result) {
				LoggerF.logError(new IOException());	// If log dir or log file creation fails, throw IOException
			}

			logWriter = new PrintWriter(logFile);
			logWriter.write("New OttoBot run at " + DateUtils.getDateAndTime(true) + ".\n");
			logWriter.write("Made by Pedro \"Flizzet\" Dutra\n");
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
		long timeTaken = System.currentTimeMillis() - currentTime;	// Calculate time taken

		try {
			tempWriter = new FileWriter(logFile, true); 	// New writer for log file, second parameter is "append"
			tempWriter.write("\n" + DateUtils.getTime(true) + " | ");
			tempWriter.write(message);
			tempWriter.write(" | " + timeTaken + "ms | ");

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

		currentTime = System.currentTimeMillis();					// Set current time for next ms calculation
	}

	/** Writes an error to a file and prints on console */
	public static void logError(Exception e) {

		File errorFile;

		try {
			e.printStackTrace();

			errorFile = new File("errors/error" + DateUtils.getTime(true) + ".txt");

			e.printStackTrace(new PrintStream(errorFile));
			System.err.println("New error logged to error file");

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

}