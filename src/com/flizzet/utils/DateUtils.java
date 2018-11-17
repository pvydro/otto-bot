package com.flizzet.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Utilities for Date and Time.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
@SuppressWarnings("ALL")
public class DateUtils {

	private static final DateFormat DATE_TIME_FORMAT = new SimpleDateFormat("MM/dd/yy HH:mm:ss");
	private static final DateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yy");
	private static final DateFormat TIME_FORMAT = new SimpleDateFormat("HH:mm:ss");
	private static final DateFormat TIME_FORMAT_NO_SECONDS = new SimpleDateFormat("HH:mm");
	private static Date Date;


	/** Suppress default constructor for noninstantiability */
	private DateUtils() {
		throw new AssertionError();
	}

	/** Return the Date with time */
	public static String getDateAndTime(boolean showSeconds) {
		Date = new Date();
		if (showSeconds) {
			return (DATE_TIME_FORMAT.format(Date));
		}
		return (DATE_FORMAT.format(Date)) + " " + (TIME_FORMAT_NO_SECONDS.format(Date));
	}

	/** Return Date */
	public static String getDate() {
		Date = new Date();
		return (DATE_FORMAT.format(Date));
	}

	/** Return time */
	public static String getTime(boolean seconds) {
		Date = new Date();
		if (seconds) {
			return (TIME_FORMAT.format(Date));
		}
		return (TIME_FORMAT_NO_SECONDS.format(Date));
	}


}