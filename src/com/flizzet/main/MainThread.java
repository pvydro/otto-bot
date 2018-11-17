package com.flizzet.main;

import com.flizzet.debug.LoggerF;

/**
 * Initial thread for fps based elements.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class MainThread {

	private static final MainThread INSTANCE = new MainThread();
	private volatile boolean threadGoing = true;

	/** Private constructor for single use */
	private MainThread() {
		LoggerF.writeToLog("MainThread class created");
	}

	/** Returns instance of Main Thread */
	public static MainThread getInstance() {
		return INSTANCE;
	}

	/** Starts Main Thread */
	public void startThread() {
		Thread loopThread = new Thread(() -> {

			while (threadGoing) {

				try { 											// Maintaining 60 fps
					Thread.sleep(1000 / 60);
				} catch (InterruptedException e) {
					LoggerF.logError(e);
				}

			}

		});

		loopThread.start();

		LoggerF.writeToLog("Main thread started");				// LOG
	}

	/** Stops thread with a boolean and allows garbage collector to do the rest */
	public void stopThread() {
		threadGoing = false;

		LoggerF.writeToLog("Main thread stopped");				// LOG
	}

}