package io.github.ciscorucinski.personal.intro.logger;

import timber.log.Timber;

public class DebugTree extends Timber.DebugTree {

    @Override
    protected String createStackElementTag(StackTraceElement element) {

        return String.format(" >> %s::%s [%s]",
                super.createStackElementTag(element),
                element.getMethodName(),
                element.getLineNumber()

        );

    }

}