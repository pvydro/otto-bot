package com.flizzet.rules;

import com.flizzet.debug.LoggerF;
import com.sun.xml.internal.ws.util.StringUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Rules list for responses.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class Rule {

	private final String name;
	private final File file;
	private String[] terms;
	private String fileText = "";

	/** Default instantiable constructor */
	public Rule(String ruleName, File ruleFile) {
		this.name = ruleName.replace(".txt", "");
		this.file = ruleFile;

		setUpTerms();

		LoggerF.writeToLog(StringUtils.capitalize(ruleName) + " rules set up");	// LOG
	}

	/** Reads rule file and makes terms */
	private void setUpTerms() {

		try {

			Scanner scanner = new Scanner(file);

			while (scanner.hasNextLine()) {					// Checks if scanner has a line
				fileText = fileText + scanner.nextLine();	// Add line to text
			}

			terms = fileText.split(" : ");

		} catch (FileNotFoundException e) {
			LoggerF.logError(e);
		}

	}

	/** Checks if data in message connects to any terms */
	public boolean compare(String message) {
		for (String s : terms) {
			if(message.toUpperCase().contains(" " + s) ||			// If it's a last word
					message.toUpperCase().contains(s + " ") ||		// Or a first word
					message.toUpperCase().contains(" " + s + " ") ||// Or a word in the middle
					message.toUpperCase().equals(s)) {				// Or the only word
				return true;										// Then it's connected
			}
		}
		return false;												// Otherwise it's not connected
	}

	public String getName() { return name; }

}
