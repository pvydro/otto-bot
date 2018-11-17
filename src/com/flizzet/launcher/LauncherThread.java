package com.flizzet.launcher;

import com.flizzet.debug.LoggerF;

/**
 * Launcher thread for fps based elements.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class LauncherThread {

	private static final LauncherThread INSTANCE = new LauncherThread();
	private volatile boolean threadGoing = true;

	/** Private constructor for single use */
	private LauncherThread() {
		LoggerF.writeToLog("LauncherThread class created");
	}

	/** Returns instance of LauncherThread */
	public static LauncherThread getInstance() {
		return INSTANCE;
	}

	/** Starts launcher thread */
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

		LoggerF.writeToLog("Launcher thread started");			// LOG
	}

	/** Stops thread with a boolean and allows garbage collector to do the rest */
	public void stopThread() {
		threadGoing = false;

		LoggerF.writeToLog("Launcher thread stopped");			// LOG
	}

}
