package io.github.ciscorucinski.personal.intro.loader;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.support.v4.content.AsyncTaskLoader;

import com.squareup.sqldelight.RowMapper;

import java.util.ArrayList;
import java.util.List;

import io.github.ciscorucinski.personal.intro.model.ModelIndicator;
import io.github.ciscorucinski.personal.intro.provider.ResumeProvider;
import io.github.ciscorucinski.personal.intro.provider.ResumeRetriever;
import timber.log.Timber;


abstract class ResumeProviderLoader<T extends ModelIndicator>
        extends AsyncTaskLoader<List<T>> {

    private static final int DEFAULT_DATA_SIZE = 5;

    private static ResumeRetriever provider;
    private final RowMapper<T> mapper;

    private List<T> data;
    private boolean hasResult;

    ResumeProviderLoader(final Context context, RowMapper<T> mapper) {

        this(context, mapper, new ResumeProvider());

    }

    ResumeProviderLoader(final Context context, RowMapper<T> mapper, ResumeRetriever provider) {

        super(context);

        Timber.i("Creating a new loader with mapper - %s", mapper);
        ResumeProviderLoader.provider = provider;

        this.mapper = mapper;
        this.data = initializeResultSize();

        hasResult = false;
        onContentChanged();

    }

    abstract Cursor queryDelegate(ResumeRetriever provider);

    @Override
    public List<T> loadInBackground() {

        Timber.i("load in background started");
        Timber.i("Setting size and the query delegate");
        List<T> results = new ArrayList<>(data.size());
        Cursor cursor = queryDelegate(provider);

        Timber.i(DatabaseUtils.dumpCursorToString(cursor));

        while (cursor.moveToNext()) {

            T element = mapper.map(cursor);
            Timber.i("adding to results - %s", element);
            results.add(element);

        }

        data = results;
        hasResult = true;

        return results;

    }

    public List<T> initializeResultSize() {

        return new ArrayList<>(DEFAULT_DATA_SIZE);

    }

    @Override
    protected void onStartLoading() {

        if (hasResult) {

            Timber.i("Forcing CACHE access for old data");
            deliverResult(data);

        } else {

            Timber.i("Forcing DB access for new data");
            forceLoad();

        }
    }

    @Override
    protected void onReset() {

        super.onReset();
        onStopLoading();

        if (!hasResult) return;

        data = null;
        hasResult = false;

    }

    public List<T> getResult() {

        Timber.i("Get results from loader with mapper - %s", mapper);
        return data;

    }

}