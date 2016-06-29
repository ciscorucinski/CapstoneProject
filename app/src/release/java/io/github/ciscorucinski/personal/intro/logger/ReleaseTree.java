package io.github.ciscorucinski.personal.intro.logger;

import android.util.Log;

import timber.log.Timber;

public class ReleaseTree extends Timber.Tree {

//	private static final int MAX_LOG_LENGTH = 4000;

	@Override
	protected boolean isLoggable(int priority) {

		// Only show Warnings, Errors, and WTF's
		return !(priority == Log.VERBOSE || priority == Log.DEBUG || priority == Log.INFO);

	}

	@Override
	protected void log(int priority, String tag, String message, Throwable t) {

		if (!isLoggable(priority)) return;

		super.log(priority, tag, t);

//		if (message.length() < MAX_LOG_LENGTH) {
//			if (priority == Log.ASSERT) Log.wtf(tag, message);
//			else Log.println(priority, tag, message);
//			return;
//		}
//
//		breakLogIntoParts(priority, tag, message);

	}

//	private void breakLogIntoParts(int priority, String tag, String message) {
//
//		for (int i = 0, length = message.length(); i < length; i++) {
//
//			int newLine = message.indexOf('\n', i);
//			newLine = (newLine != -1) ? (newLine) : (length);
//
//			do {
//
//				int end = Math.min(newLine, i + MAX_LOG_LENGTH);
//				String part = message.substring(i, end);
//
//				if (priority == Log.ASSERT) Log.wtf(tag, part);
//				else Log.println(priority, tag, message);
//
//				i = end;
//
//			} while (i < newLine);
//
//		}
//	}
}