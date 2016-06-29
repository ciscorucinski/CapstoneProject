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
import io.github.ciscorucinski.personal.intro.loader.SkillLoader;
import io.github.ciscorucinski.personal.intro.model.Resume;
import io.github.ciscorucinski.personal.intro.ui.custom.SkillsTextView;


/**
 * A simple {@link Fragment} subclass that displays my introduction. Use the {@link
 * SkillsFragment#newInstance} factory method to create an instance of this fragment.
 */
public class SkillsFragment extends SimplifiedLoaderFragment<Resume.Skill> {

    public SkillsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of this fragment
     *
     * @return A new instance of fragment IntroductionFragment.
     */
    public static SkillsFragment newInstance() {

        SkillsFragment fragment = new SkillsFragment();
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
        return "Skills";
    }

    @Override
    public Loader<List<Resume.Skill>> onCreateLoader(int id, Bundle args) {

        return new SkillLoader(getContext());

    }

    @Override
    public void onLoaderInit() {

        getLoaderManager().initLoader(5, null, this);

    }

    @Override
    public void onNewDataAvailable(LinearLayout createdLayout, List<Resume.Skill> newData) {

        for (Resume.Skill skills : newData) {

            createdLayout.addView(SkillsTextView.populate(getContext(), skills));

        }

    }

}
