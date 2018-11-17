package com.flizzet.rules;

import com.flizzet.debug.LoggerF;

import java.io.File;

/**
 * Loads responses and stores them in the ResponseContainer.
 *
 * @author Pedro Dutra 2017.
 * @version 1.0
 * @see also ResponseContainer
 */
public class ResponseLoader {

	private static final File responsesFolder = new File("res/bot/responses/");
	private static File[] responsesFiles = responsesFolder.listFiles();

	/** Suppress default constructor for noninstantiability */
	public ResponseLoader() {
		throw new AssertionError();
	}

	/** Loads all responses and puts them in the response container */
	public static void loadResponses() {

		LoggerF.writeToLog("Loading responses");				// LOG

		for (File f : responsesFiles) {
			String responseName = f.getName();
			Response newResponse = new Response(responseName, f);
			ResponseContainer.getInstance().addToResponses(newResponse);
		}

		LoggerF.writeToLog("Responses loaded");					// LOG

	}
}
