package io.github.ciscorucinski.personal.intro;

import android.app.Application;

import com.facebook.stetho.Stetho;

import io.github.ciscorucinski.personal.intro.logger.DebugTree;
import timber.log.Timber;

public class LoggingApplication extends Application {

    private static LoggingApplication instance;

    public static LoggingApplication getInstance() {

        if (instance == null) instance = new LoggingApplication();
        return instance;

    }

    public void onCreate() {

        super.onCreate();

        Timber.plant(new DebugTree());

        // Allow viewing of the database via Google Chrome via its Inspector
        //
        // Put in the URL ...
        //     chrome://inspect
        //
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build());

    }

}