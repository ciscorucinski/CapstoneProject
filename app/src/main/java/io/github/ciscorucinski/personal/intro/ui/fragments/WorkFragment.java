package io.github.ciscorucinski.personal.intro.ui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.List;

import io.github.ciscorucinski.personal.intro.R;
import io.github.ciscorucinski.personal.intro.loader.WorkLoader;
import io.github.ciscorucinski.personal.intro.model.Resume;
import io.github.ciscorucinski.personal.intro.ui.custom.WorkView;
import timber.log.Timber;


/**
 * A simple {@link Fragment} subclass that displays my introduction. Use the {@link
 * WorkFragment#newInstance} factory method to create an instance of this fragment.
 */
public class WorkFragment extends SimplifiedLoaderFragment<Resume.Work> {

    public WorkFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of this fragment
     *
     * @return A new instance of fragment IntroductionFragment.
     */
    public static WorkFragment newInstance() {

        Timber.i("Creating a new instance of %s", WorkFragment.class.getSimpleName());
        WorkFragment fragment = new WorkFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);

        return fragment;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_resume_section, container, false);

    }

    @Override
    public String getName() {
        return "Work";
    }

    @Override
    public Loader<List<Resume.Work>> onCreateLoader(int id, Bundle args) {

        return new WorkLoader(getContext());

    }

    @Override
    public void onLoaderInit() {

        getLoaderManager().initLoader(4, null, this);

    }

    @Override
    public void onNewDataAvailable(LinearLayout createdLayout, List<Resume.Work> newData) {

        for (Resume.Work work : newData) {

            createdLayout.addView(WorkView.populate(getContext(), work));

        }

    }

}
