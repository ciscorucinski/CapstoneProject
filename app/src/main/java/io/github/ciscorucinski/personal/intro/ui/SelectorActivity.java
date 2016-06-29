package io.github.ciscorucinski.personal.intro.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

import java.util.ArrayList;
import java.util.List;

import io.github.ciscorucinski.personal.intro.R;
import io.github.ciscorucinski.personal.intro.model.Resume;
import io.github.ciscorucinski.personal.intro.ui.fragments.PeopleFragment;

public class SelectorActivity extends AppCompatActivity
        implements PeopleFragment.PeopleDisplayer {

    private boolean isSinglePerson = false;

    public static Intent createIntent(Context context) {

        return new Intent(context, SelectorActivity.class);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_resume_selection);

        int personID = getResources().getInteger(R.integer.person_id);
        isSinglePerson = getResources().getBoolean(R.bool.is_single_person);

        PeopleFragment fragment = (isSinglePerson)
                ? (PeopleFragment.newInstance(this))
                : (PeopleFragment.newSinglePersonInstance(this, personID));

        // The fragment that goes on this screen does not show an UI. Only a white screen
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();

    }

    @Override
    public void onPeopleRetrieved(final List<Resume.People> people) {

        // Either show a dialog with all the available resumes, or open the predefined user

        if (isSinglePerson) {

            openResume(people.get(0));    // There should only be one person in this list, so grab it

        } else {

            List<String> jobList = new ArrayList<>(5);

            for (Resume.People person : people) {
                jobList.add(person.seeking_position());
            }

            MaterialDialog dialog = createDialog(jobList, people);
            dialog.show();

        }

    }

    private MaterialDialog createDialog(final List<String> jobList,
                                        final List<Resume.People> peopleList) {

        return new MaterialDialog.Builder(this)
                .title("Select Resume For...")
                .items(jobList)

                // On item click, bundle the information from the resume and pass it to the next
                // activity to use for the UI framework.
                .itemsCallback(new MaterialDialog.ListCallback() {

                    @Override
                    public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {

                        Resume.People person = peopleList.get(which);
                        openResume(person);

                    }
                })

                // When the user clicks the cancel button, then close this activity. If this is the
                // first screen the user as come to, then the app closes. If the user is selecting
                // another resume to view, then the previous resume is brought back for the user
                .negativeText(android.R.string.cancel)
                .onNegative(new MaterialDialog.SingleButtonCallback() {

                    @Override
                    public void onClick(@NonNull MaterialDialog dialog,
                                        @NonNull DialogAction which) {
                        finish();
                    }
                })

                // Cancels the dismiss event that triggers when the user clicks outside of the
                // dialog bounds
                .canceledOnTouchOutside(false)
                .build();

    }

    private void openResume(Resume.People person) {

        Bundle bundle = new Bundle();

        bundle.putLong(ResumeActivity.ID, person._id());
        bundle.putString(ResumeActivity.NAME, person.name());
        bundle.putString(ResumeActivity.EMAIL, person.email());
        bundle.putString(ResumeActivity.PHONE, person.phone());
        bundle.putString(ResumeActivity.GITHUB, person.github());
        bundle.putString(ResumeActivity.LINKEDIN, person.linkedin());
        bundle.putString(ResumeActivity.SEEKING, person.seeking_position());

        startActivity(ResumeActivity.createIntent(SelectorActivity.this, bundle));
        finish();

    }

}
