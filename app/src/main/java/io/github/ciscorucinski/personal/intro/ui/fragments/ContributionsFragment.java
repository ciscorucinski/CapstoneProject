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
import io.github.ciscorucinski.personal.intro.loader.ContributionLoader;
import io.github.ciscorucinski.personal.intro.model.Resume;
import io.github.ciscorucinski.personal.intro.ui.custom.ContributionView;


/**
 * A simple {@link Fragment} subclass that displays my introduction. Use the {@link
 * ContributionsFragment#newInstance} factory method to create an instance of this fragment.
 */
public class ContributionsFragment extends SimplifiedLoaderFragment<Resume.Contribution> {

    public ContributionsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of this fragment
     *
     * @return A new instance of fragment IntroductionFragment.
     */
    public static ContributionsFragment newInstance() {

        ContributionsFragment fragment = new ContributionsFragment();
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
        return "Contributions";
    }

    @Override
    public Loader<List<Resume.Contribution>> onCreateLoader(int id, Bundle args) {

        return new ContributionLoader(getContext());

    }


    @Override
    public void onLoaderInit() {

        getLoaderManager().initLoader(2, null, this);

    }

    @Override
    public void onNewDataAvailable(LinearLayout createdLayout, List<Resume.Contribution> newData) {

        for (Resume.Contribution contribution : newData) {

            createdLayout.addView(ContributionView.populate(getContext(), contribution));

        }

    }

}
