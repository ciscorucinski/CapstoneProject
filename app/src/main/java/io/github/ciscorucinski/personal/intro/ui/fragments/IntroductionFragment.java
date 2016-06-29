package io.github.ciscorucinski.personal.intro.ui.fragments;


import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.List;

import io.github.ciscorucinski.personal.intro.R;
import io.github.ciscorucinski.personal.intro.loader.PersonLoader;
import io.github.ciscorucinski.personal.intro.model.Resume;
import io.github.ciscorucinski.personal.intro.ui.custom.PersonView;
import timber.log.Timber;


/**
 * A simple {@link Fragment} subclass that displays my introduction. Use the {@link
 * IntroductionFragment#newInstance} factory method to create an instance of this fragment.
 */
public class IntroductionFragment extends SimplifiedLoaderFragment<Resume.People> {

    private static final String PERSON_ID = "id";

    private long personID;
    private boolean isWideDisplay = false;

    public IntroductionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of this fragment that can be changed after
     * creation
     *
     * @return A new instance of fragment IntroductionFragment.
     */
    public static IntroductionFragment newInstance(long id) {

        return newInstance(id, false);

    }

    /**
     * Use this factory method to create a new instance of this fragment that is designed not to
     * change after creation
     *
     * @return A new instance of fragment IntroductionFragment.
     */
    public static IntroductionFragment newUnchangingInstance(long id) {

        return newInstance(id, true);

    }

    private static IntroductionFragment newInstance(long id, boolean nonChangeable) {

        IntroductionFragment fragment = new IntroductionFragment();
        Bundle args = new Bundle();

        args.putLong(PERSON_ID, id);
        args.putBoolean(NON_CHANGEABLE, nonChangeable);

        fragment.setArguments(args);

        return fragment;

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        @LayoutRes int layoutID;
        isWideDisplay = getResources().getBoolean(R.bool.wide_display);

        personID = getArguments().getLong(PERSON_ID);

        layoutID = (isWideDisplay)
                ? (R.layout.fragment_resume_introduction_shim)
                : (R.layout.fragment_resume_section);

        // Inflate the layout for this fragment

        return inflater.inflate(layoutID, container, false);

    }

    @Override
    public String getName() {
        return "Introduction";
    }

    @Override
    public Loader<List<Resume.People>> onCreateLoader(int id, Bundle args) {

        Timber.i("Creating Introduction Loader with ID - %s - and personID - %s", id, personID);
        return new PersonLoader(getContext(), personID);

    }

    @Override
    public void onLoaderInit() {

        Timber.i("Initializing Introduction Loader with ID - %s", 0);
        getLoaderManager().initLoader(0, null, this);

    }

    @Override
    public void onNewDataAvailable(LinearLayout createdLayout, List<Resume.People> newData) {

        Timber.i("Adding new person to view...\n%s\n%s\n%s", createdLayout, newData, getContext());

        if (createdLayout == null) return;

        for (Resume.People person : newData) {

            createdLayout.addView(PersonView.populate(getContext(), person));

        }

    }

}
