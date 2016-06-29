package io.github.ciscorucinski.personal.intro.hub;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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

    private static final String LAUNCH_RESUME_ACTION = "io.github.ciscorucinski.personal.intro.hub.LAUNCH_RESUME_ACTION";

    private static Intent createIntent(Context context) {
        return new Intent(context, ResumeHubWidgetProvider.class);
    }

    private static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                        int appWidgetId) {

        Intent serviceIntent = MyWidgetService.createIntent(context);
        serviceIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        serviceIntent.setData(Uri.parse(serviceIntent.toUri(Intent.URI_INTENT_SCHEME)));

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.resume_hub_list_widget);
        views.setRemoteAdapter(R.id.widget_list, serviceIntent);

        Intent widgetProviderIntent = ResumeHubWidgetProvider.createIntent(context);
        widgetProviderIntent.setAction(ResumeHubWidgetProvider.LAUNCH_RESUME_ACTION);
        widgetProviderIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        serviceIntent.setData(Uri.parse(serviceIntent.toUri(Intent.URI_INTENT_SCHEME)));

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, widgetProviderIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        views.setPendingIntentTemplate(R.id.widget_list, pendingIntent);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);

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

        if (intent.getAction().equals(LAUNCH_RESUME_ACTION)) {

            Timber.i("Intent Action is LAUNCH_RESUME_ACTION");

            // Open the Resume activity with the user selected resume info
            Bundle bundle = intent.getBundleExtra(CREATE_INTENT_BUNDLE);
            context.startActivity(ResumeActivity
                    .createIntentWithFlags(context, bundle,
                            Intent.FLAG_ACTIVITY_NEW_TASK));
        }

        int appWidgetIds[] = appWidgetManager.getAppWidgetIds(new ComponentName(context, ResumeHubWidgetProvider.class));
        appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.widget_list);

        super.onReceive(context, intent);

    }
}

