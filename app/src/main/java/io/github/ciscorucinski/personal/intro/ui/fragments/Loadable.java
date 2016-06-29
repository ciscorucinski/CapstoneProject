package io.github.ciscorucinski.personal.intro.ui.fragments;

import android.widget.LinearLayout;

import java.util.List;

interface Loadable<T> {

    void onLoaderInit();

    void onNewDataAvailable(LinearLayout createdLayout, List<T> newData);

}