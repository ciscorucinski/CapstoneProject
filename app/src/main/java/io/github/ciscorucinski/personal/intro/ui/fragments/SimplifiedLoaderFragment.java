package io.github.ciscorucinski.personal.intro.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import android.view.View;
import android.widget.LinearLayout;

import java.util.List;

import io.github.ciscorucinski.personal.intro.R;
import timber.log.Timber;

public abstract class SimplifiedLoaderFragment<T> extends Fragment implements LoaderCallbacks<List<T>>, Loadable<T> {

    static final String NON_CHANGEABLE = "non_changeable";

    private List<T> data;
    private boolean isViewCreated = false;
    private boolean isDataAvailable = false;
    private boolean isDataHandled = false;
    private LinearLayout layout;

    public abstract String getName();

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        boolean isUnchangeable = getArguments().getBoolean(NON_CHANGEABLE);

        Timber.i("Unchangeable View - %s", isUnchangeable);
        if (isUnchangeable) {

            Timber.i("Using view - %s (%s)", "R.id.fragment_container_intro_on_tablet", R.id.fragment_container_intro_on_tablet);
            layout = (LinearLayout) view.findViewById(R.id.fragment_container_intro_on_tablet);

        } else {

            Timber.i("Using view - %s (%s)", "R.id.fragment_container", R.id.fragment_container);
            layout = (LinearLayout) view.findViewById(R.id.fragment_container);

        }

        isViewCreated = true;

        //noinspection ConstantConditions
        Timber.i("isDataAvailable %s\nisViewCreated %s\nisDataHandled %s\ndata %s", isDataAvailable, isViewCreated, isDataHandled, data);
        if (isDataAvailable && !isDataHandled) {

            isDataHandled = true;
            onNewDataAvailable(layout, data);

        }

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        onLoaderInit();

    }

    @Override
    public void onLoadFinished(Loader<List<T>> loader, List<T> data) {

        this.data = data;
        this.isDataAvailable = true;

        //noinspection ConstantConditions
        Timber.i("isDataAvailable %s\nisViewCreated %s\ncontext %s\nisDataHandled %s\ndata %s", isDataAvailable, isViewCreated, getContext(), isDataHandled, data);
        if (isViewCreated && !isDataHandled && getContext() != null) {

            Timber.i(data.toString());
            isDataHandled = true;
            onNewDataAvailable(layout, data);

        }

    }

    @Override
    public void onLoaderReset(Loader<List<T>> loader) {

        this.data = null;
        this.isDataHandled = false;
        this.isDataAvailable = false;

    }

}
