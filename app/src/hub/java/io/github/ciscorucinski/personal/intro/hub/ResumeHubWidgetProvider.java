package io.github.ciscorucinski.personal.intro.hub;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RemoteViews;

import io.github.ciscorucinski.personal.intro.R;
import io.github.ciscorucinski.personal.intro.ui.ResumeActivity;
import timber.log.Timber;

import static io.github.ciscorucinski.personal.intro.ui.ResumeActivity.CREATE_INTENT_BUNDLE;

/**
 * Implementation of App Widget functionality.
 */
public class ResumeHubWidgetProvider extends AppWidgetProvider {

    static final String USE_REAL_DATA = "io.github.ciscorucinski.personal.intro.hub.USE_REAL_DATA";

    private static final boolean displayRealData = true;
    private static final String LAUNCH_RESUME_ACTION = "io.github.ciscorucinski.personal.intro.hub.LAUNCH_RESUME_ACTION";

    private static Intent createActionIntent(Context context, int widgetId) {

        Intent intent = new Intent(context, ResumeHubWidgetProvider.class);
        intent.setAction(ResumeHubWidgetProvider.LAUNCH_RESUME_ACTION);
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetId);

        return intent;

    }

    private static PendingIntent createPendingIntent(Context context, Intent intent) {
        return PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    private static int[] getAppWidgetIds(Context context, AppWidgetManager manager) {
        return manager.getAppWidgetIds(new ComponentName(context, ResumeHubWidgetProvider.class));
    }

    private static void updateAppWidget(Context context, AppWidgetManager manager, int widgetId) {

        // Create needed intents
        Intent intent = ResumeHubWidgetProvider.createActionIntent(context, widgetId);
        PendingIntent pendingIntent = ResumeHubWidgetProvider.createPendingIntent(context, intent);
        Intent serviceIntent = MyWidgetService.createIntent(context, widgetId);

        // Add a specific custom flag to indicate if the view should show test data or not
        serviceIntent.putExtra(USE_REAL_DATA, displayRealData);

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.resume_list_widget);
        views.setRemoteAdapter(R.id.widget_list, serviceIntent);
        views.setPendingIntentTemplate(R.id.widget_list, pendingIntent);

        // Instruct the widget manager to update the widget
        manager.updateAppWidget(widgetId, views);

    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        // There may be multiple similar widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }

    }

    @Override
    public void onReceive(Context context, Intent intent) {

        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);

        switch (intent.getAction()) {
            case LAUNCH_RESUME_ACTION:
                executeResumeAction(context, intent);
                break;
            default: // do nothing
        }

        int appWidgetIds[] = ResumeHubWidgetProvider.getAppWidgetIds(context, appWidgetManager);
        appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.widget_list);

        super.onReceive(context, intent);

    }

    private void executeResumeAction(Context context, Intent bundledIntent) {

        Timber.i("Intent Action is LAUNCH_RESUME_ACTION");
        Bundle bundledPerson = bundledIntent.getBundleExtra(CREATE_INTENT_BUNDLE);
        Intent launchActivityIntent = ResumeActivity
                .createFlagIntent(context, bundledPerson, Intent.FLAG_ACTIVITY_NEW_TASK);

        context.startActivity(launchActivityIntent);

    }

}

