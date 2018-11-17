package com.flizzet.rules;

import com.flizzet.chatwindow.ChatConversationArea;
import com.flizzet.debug.LoggerF;
import com.flizzet.otto.Otto;
import com.flizzet.searching.GoogleSearcher;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * Holds all rules
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class RuleContainer {

	private static final RuleContainer INSTANCE = new RuleContainer();
	private ArrayList<Rule> rules = new ArrayList<Rule>();

	/** Private constructor for single use */
	private RuleContainer() {
	}

	/** Returns and instance of RuleContainer */
	public static RuleContainer getInstance() {
		return INSTANCE;
	}

	/** Compares user input to all rules */
	public void compare(String message) {

		String ottoResponse = "";
		boolean connects = false;
		Rule ruleUsed = null;

		for (Rule r : rules) {
			if (!connects && r.getName().equals("mathsearches")) {
				connects = r.compare(message); 			// Checks to see if user input connects to rule
				if (connects) {
					Thread searchThread = new Thread() {
						public void run() {
							Otto.getInstance().respond("Please wait while I take a look in my hard drive");
							ChatConversationArea.getInstance().refresh();
						}
					};
					searchThread.start();

					ottoResponse = GoogleSearcher.searchAndReceive(message, true, true);

				}
			}
			else if (!connects) {
				connects = r.compare(message);        	// Checks to see if user input connects to rule
				if (connects) {
					ruleUsed = r;
					ottoResponse = ResponseContainer.getInstance().getResponse(ruleUsed);
				}
			}
		}

		if (!connects) {
			File ruleFile = new File("res/bot/responses/dontunderstand.txt");
			ottoResponse = ResponseContainer.getInstance().getResponse(new Rule("dontunderstand", ruleFile));
		}

		Otto.getInstance().respond(ottoResponse);
	}

	/** Adds a rule to the rules ArrayList */
	public void addToRules(Rule newRule) {
		rules.add(newRule);
	}

}
