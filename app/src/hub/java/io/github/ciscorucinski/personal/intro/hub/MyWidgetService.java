package io.github.ciscorucinski.personal.intro.hub;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViewsService;

public class MyWidgetService extends RemoteViewsService {

    static Intent createIntent(Context context, int appWidgetId) {

        Intent serviceIntent = new Intent(context, MyWidgetService.class);

        serviceIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        serviceIntent.setData(Uri.parse(serviceIntent.toUri(Intent.URI_INTENT_SCHEME)));

        return serviceIntent;
    }

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {

        boolean usingRealData = intent.getBooleanExtra(ResumeHubWidgetProvider.USE_REAL_DATA, true);
        return (usingRealData)
                ? new HubWidgetAdapter(this.getApplicationContext(), intent)
                : new ImagePreviewHubWidgetAdapter(this.getApplicationContext(), intent);

    }

}