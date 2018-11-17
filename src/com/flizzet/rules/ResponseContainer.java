package com.flizzet.rules;

import com.flizzet.debug.LoggerF;

import java.util.ArrayList;

/**
 * Holds responses.
 *
 * @author Pedro Dutra 2017.
 * @version 1.0
 */
public class ResponseContainer {

	private static final ResponseContainer INSTANCE = new ResponseContainer();
	private ArrayList<Response> responses = new ArrayList<>();

	/** Private constructor for single use */
	private ResponseContainer() {}

	/** Returns and instance of the ResponseContainer */
	public static ResponseContainer getInstance() {
		return INSTANCE;
	}

	/** Adds response to the responses ArrayList */
	public void addToResponses(Response newResponse) {
		responses.add(newResponse);
	}

	/** Returns a String response based on the rule triggered */
	public String getResponse(Rule rule) {
		for (Response r : responses) {						// For every response
			if (r.getName().equals(rule.getName())) {		// If the rule name and response name match up
				return r.getRandomResponse();				// Get a random response from that rule
			}
		}

		NullPointerException e = new NullPointerException();
		LoggerF.logError(e);
		throw e;
	}

}
