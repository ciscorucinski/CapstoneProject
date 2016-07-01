package io.github.ciscorucinski.personal.intro.hub;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.Loader;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import java.util.ArrayList;
import java.util.List;

import io.github.ciscorucinski.personal.intro.loader.PeopleLoader;
import io.github.ciscorucinski.personal.intro.model.Resume;
import io.github.ciscorucinski.personal.intro.ui.ResumeActivity;
import timber.log.Timber;

import static io.github.ciscorucinski.personal.intro.ui.ResumeActivity.CREATE_INTENT_BUNDLE;

class HubWidgetAdapter implements RemoteViewsService.RemoteViewsFactory,
        Loader.OnLoadCompleteListener<List<Resume.People>> {

    private final Context context;
    private final int appWidgetId;
    private List<Resume.People> data;
    private PeopleLoader loader;

    HubWidgetAdapter(Context context, Intent intent) {
        this.context = context;
        this.data = new ArrayList<>();
        appWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                AppWidgetManager.INVALID_APPWIDGET_ID);
    }

    @Override
    public RemoteViews getViewAt(int position) {

        RemoteViews view = new RemoteViews(context.getPackageName(),
                android.R.layout.simple_list_item_1);

        Resume.People person = data.get(position);

        view.setTextViewText(android.R.id.text1, person.seeking_position());
        view.setTextColor(android.R.id.text1, Color.BLACK);

        Bundle bundle = new Bundle();

        bundle.putLong(ResumeActivity.ID, person._id());
        bundle.putString(ResumeActivity.NAME, person.name());
        bundle.putString(ResumeActivity.EMAIL, person.email());
        bundle.putString(ResumeActivity.PHONE, person.phone());
        bundle.putString(ResumeActivity.GITHUB, person.github());
        bundle.putString(ResumeActivity.LINKEDIN, person.linkedin());
        bundle.putString(ResumeActivity.SEEKING, person.seeking_position());

        Intent intent = new Intent();
        intent.putExtra(CREATE_INTENT_BUNDLE, bundle);

        Timber.i("Bundled Person%s", intent);
        view.setOnClickFillInIntent(android.R.id.text1, intent);

        return view;

    }

    @Override
    public void onLoadComplete(Loader<List<Resume.People>> loader, List<Resume.People> data) {
        this.data = data;
    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onDestroy() {

        if (loader != null) {

            loader.unregisterListener(this);
            loader.cancelLoad();
            loader.stopLoading();
            loader = null;

        }

        data = null;

    }

    @Override public void onDataSetChanged() {
        loader = new PeopleLoader(context);
        loader.registerListener(0, this);
        loader.startLoading();
    }

    @Override public int getCount() { return data.size(); }
    @Override public RemoteViews getLoadingView() {
        return null;
    }
    @Override public int getViewTypeCount() { return 1; }
    @Override public long getItemId(int position) { return position; }
    @Override public boolean hasStableIds() { return true; }

}