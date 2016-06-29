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
import io.github.ciscorucinski.personal.intro.loader.ProjectLoader;
import io.github.ciscorucinski.personal.intro.model.Resume;
import io.github.ciscorucinski.personal.intro.ui.custom.ProjectView;
import timber.log.Timber;


/**
 * A simple {@link Fragment} subclass that displays my introduction. Use the {@link
 * ProjectsFragment#newInstance} factory method to create an instance of this fragment.
 */
public class ProjectsFragment extends SimplifiedLoaderFragment<Resume.Project> {


    public ProjectsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of this fragment
     *
     * @return A new instance of fragment IntroductionFragment.
     */
    public static ProjectsFragment newInstance() {

        ProjectsFragment fragment = new ProjectsFragment();
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
        return "Projects";
    }

    @Override
    public Loader<List<Resume.Project>> onCreateLoader(int id, Bundle args) {

        Timber.i("Creating Projects Loader with ID - %s", id);
        return new ProjectLoader(getContext());

    }

    @Override
    public void onLoaderInit() {

        Timber.i("Initializing Projects Loader with ID - %s", 1);
        getLoaderManager().initLoader(1, null, this);

    }

    @Override
    public void onNewDataAvailable(LinearLayout createdLayout, List<Resume.Project> newData) {

        Timber.i("Adding new person to view...\n%s\n%s\n%s", createdLayout, newData, getContext());
        for (Resume.Project project : newData) {

            createdLayout.addView(ProjectView.populate(getContext(), project));

        }

//		createdLayout.invalidate();

    }

}
