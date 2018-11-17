package com.flizzet.rules;

import com.flizzet.debug.LoggerF;
import com.flizzet.otto.Otto;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

/**
 * Holds possible responses from the response file.
 *
 * @author Pedro Dutra 2017.
 * @version 1.0
 */
public class Response {

	private final File file;
	private final String name;
	private String fileText = "";
	private String[] responses;

	/** Default instantiable constructor */
	public Response(String fileName, File responseFile) {
		this.file = responseFile;
		this.name = fileName.replace(".txt", "");

		setUpResponses();
	}

	/** Reads response file and makes terms */
	private void setUpResponses() {

		try {

			Scanner scanner = new Scanner(file);

			while (scanner.hasNextLine()) {					// Checks if scanner has a line
				fileText = fileText + scanner.nextLine();	// Add line to text
			}

			responses = fileText.split(" : ");

		} catch (FileNotFoundException e) {
			LoggerF.logError(e);
		}

	}

	/** Chooses a response from the possible responses */
	public String getRandomResponse() {

		Random r = new Random();
		int chosenResponse;

		chosenResponse = r.nextInt(responses.length);

		return responses[chosenResponse];

	}

	public String getName() { return name; }

}
