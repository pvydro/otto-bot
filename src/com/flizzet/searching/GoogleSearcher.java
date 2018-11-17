package com.flizzet.searching;

import com.flizzet.debug.LoggerF;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.xml.bind.util.JAXBSource;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;

/**
 * Uses google to get results for Otto responses.
 *
 * @author Pedro Dutra 2017.
 * @version 1.0
 */
public class GoogleSearcher {

	private static final String GOOGLE_SEARCH_URL = "https://www.google.com/search";

	/**
	 * Suppress default constructor for noninstantiability
	 */
	private GoogleSearcher() {
		throw new AssertionError();
	}

	/** Get input from rule and return results */
	public static String searchAndReceive(String query, boolean title, boolean url) {

		String searchTerm = query;
		int numOfSearches = 3;
		String result = "I've founds these " + (numOfSearches) + " websites about " + query + "\n";
		Document doc = null;

		String searchURL = GOOGLE_SEARCH_URL + "?q=" + searchTerm + "&num=" + numOfSearches;

		try {
			doc = Jsoup.connect(searchURL).userAgent("Mozilla/5.0").get();
		} catch (IOException e) {
			LoggerF.logError(e);
		}

		Elements results = doc.select("h3.r > a");

		for (Element e : results) {
			String linkHref = e.attr("href");
			String linkText = e.text();
			if (title) {
				result = result + " " + linkText;
			}
			if (url) {
				result = result + ": \n" + linkHref.substring(6, linkHref.indexOf("&")).replaceAll("=", ""); // XXX
			}
			result = result + "\n------------------------\n";
		}



		return result;

	}

	/** Get scanner input and return results */
	public static void searchAndReceive() {

		//Taking search term input from console
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please enter the search term.");
		String searchTerm = scanner.nextLine();
		System.out.println("Please enter the number of results. Example: 5 10 20");
		int num = scanner.nextInt();
		scanner.close();

		String searchURL = GOOGLE_SEARCH_URL + "?q=" + searchTerm + "&num=" + num;
		//without proper User-Agent, we will get 403 error
		Document doc = null;
		try {
			doc = Jsoup.connect(searchURL).userAgent("Mozilla/5.0").get();
		} catch (IOException e) {
			LoggerF.logError(e);
		}

		Elements results = doc.select("h3.r > a");

		for (Element result : results) {
			String linkHref = result.attr("href");
			String linkText = result.text();
			System.out.println("Text: " + linkText + ", URL: " + linkHref.substring(6, linkHref.indexOf("&")));
		}

	}
}
