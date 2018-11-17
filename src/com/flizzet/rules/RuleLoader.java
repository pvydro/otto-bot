package com.flizzet.rules;

import com.flizzet.debug.LoggerF;

import java.io.File;

/**
 * Loads rule files for future use.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class RuleLoader {

	private static final File rulesFolder = new File("res/bot/rules/");
	private static File[] rulesFiles = rulesFolder.listFiles();

	/** Suppress default constructor for noninstantiability */
	private RuleLoader() {
		throw new AssertionError();
	}

	/** Loads all rules and puts them in the rule container */
	public static void loadRules() {

		LoggerF.writeToLog("Loading rules");			// LOG

		for (File f : rulesFiles) {
			String ruleName = f.getName();
			Rule newRule = new Rule(ruleName, f);
			RuleContainer.getInstance().addToRules(newRule);
		}

		LoggerF.writeToLog("Rules loaded");				// LOG
	}

}