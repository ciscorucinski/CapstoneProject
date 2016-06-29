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
import io.github.ciscorucinski.personal.intro.loader.PeopleLoader;
import io.github.ciscorucinski.personal.intro.loader.PersonLoader;
import io.github.ciscorucinski.personal.intro.model.Resume;


/**
 * A simple {@link Fragment} subclass that displays my introduction. Use the {@link
 * PeopleFragment#newInstance} factory method to create an instance of this fragment.
 */
public class PeopleFragment extends SimplifiedLoaderFragment<Resume.People> {

    private static final String ID = "ID";
    private static final String IS_SINGLE_PERSON = "is_single_person";

    private static final int INITIAL_ID = -1;

    private static PeopleDisplayer displayer;

    private boolean isSinglePerson = false;
    private int personID = INITIAL_ID;

    public PeopleFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of this fragment that can be changed after
     * creation
     *
     * @return A new instance of fragment IntroductionFragment.
     */
    public static PeopleFragment newInstance(PeopleDisplayer displayer) {

        return newInstance(displayer, false, -1);

    }

    /**
     * Use this factory method to create a new instance of this fragment that is designed not to
     * change after creation
     *
     * @return A new instance of fragment IntroductionFragment.
     */
    public static PeopleFragment newSinglePersonInstance(PeopleDisplayer displayer, int id) {

        return newInstance(displayer, true, id);

    }

    /**
     * Use this factory method to create a new instance of this fragment
     *
     * @return A new instance of fragment IntroductionFragment.
     */
    private static PeopleFragment newInstance(PeopleDisplayer displayer, boolean isSinglePerson, int id) {

        PeopleFragment fragment = new PeopleFragment();
        Bundle args = new Bundle();

        args.putBoolean(IS_SINGLE_PERSON, isSinglePerson);
        args.putInt(ID, id);

        fragment.setArguments(args);

        PeopleFragment.displayer = displayer;

        return fragment;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (savedInstanceState != null) {

            isSinglePerson = savedInstanceState.getBoolean(IS_SINGLE_PERSON);
            personID = savedInstanceState.getInt(ID);

        }

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_resume_selector, container, false);


    }

    @Override
    public String getName() {
        return "";
    }

    @Override
    public Loader<List<Resume.People>> onCreateLoader(int id, Bundle args) {

        if (isSinglePerson) {

            if (personID < 0) personID = 1;
            return new PersonLoader(getContext(), personID);

        } else {

            return new PeopleLoader(getContext());

        }

    }

    @Override
    public void onLoaderInit() {

        getLoaderManager().initLoader(6, null, this);

    }

    @Override
    public void onNewDataAvailable(LinearLayout createdLayout, List<Resume.People> newData) {

        if (displayer == null) return;
        displayer.onPeopleRetrieved(newData);

    }

    public interface PeopleDisplayer {

        void onPeopleRetrieved(List<Resume.People> people);

    }

}
