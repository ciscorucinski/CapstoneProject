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
import io.github.ciscorucinski.personal.intro.loader.EducationLoader;
import io.github.ciscorucinski.personal.intro.model.Resume;
import io.github.ciscorucinski.personal.intro.ui.custom.EducationView;


/**
 * A simple {@link Fragment} subclass that displays my introduction. Use the {@link
 * EducationFragment#newInstance} factory method to create an instance of this fragment.
 */
public class EducationFragment extends SimplifiedLoaderFragment<Resume.Education> {

    public EducationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of this fragment
     *
     * @return A new instance of fragment IntroductionFragment.
     */
    public static EducationFragment newInstance() {

        EducationFragment fragment = new EducationFragment();
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
        return "Education";
    }

    @Override
    public Loader<List<Resume.Education>> onCreateLoader(int id, Bundle args) {

        return new EducationLoader(getContext());

    }

    @Override
    public void onLoaderInit() {

        getLoaderManager().initLoader(3, null, this);

    }

    @Override
    public void onNewDataAvailable(LinearLayout createdLayout, List<Resume.Education> newData) {

        for (Resume.Education education : newData) {

            createdLayout.addView(EducationView.populate(getContext(), education));

        }

    }

}
