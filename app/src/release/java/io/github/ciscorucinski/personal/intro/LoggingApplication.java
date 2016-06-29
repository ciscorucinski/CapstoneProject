package io.github.ciscorucinski.personal.intro;

import android.app.Application;

import io.github.ciscorucinski.personal.intro.logger.ReleaseTree;
import timber.log.Timber;

public  class LoggingApplication extends Application {

	private static LoggingApplication instance;

	public static LoggingApplication getInstance() {

		if (instance == null) instance = new LoggingApplication();
		return instance;

	}
	public void onCreate() {

		super.onCreate();

		Timber.plant(new ReleaseTree());

	}

}