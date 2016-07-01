package io.github.ciscorucinski.personal.intro.hub;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViewsService;

public class MyWidgetService extends RemoteViewsService {

    static Intent createIntent(Context context) {
        return new Intent(context, MyWidgetService.class);
    }

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {

        boolean isTesting = intent.getBooleanExtra(ResumeHubWidgetProvider.TEST, false);

        if (isTesting) {
            return new ImagePreviewHubWidgetAdapter(this.getApplicationContext(), intent);
        } else {
            return new HubWidgetAdapter(this.getApplicationContext(), intent);
        }
    }

}