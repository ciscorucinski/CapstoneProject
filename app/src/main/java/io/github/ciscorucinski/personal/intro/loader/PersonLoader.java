package io.github.ciscorucinski.personal.intro.loader;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import io.github.ciscorucinski.personal.intro.model.Resume;
import io.github.ciscorucinski.personal.intro.provider.ResumeRetriever;
import timber.log.Timber;

public class PersonLoader extends ResumeProviderLoader<Resume.People> {

    private final long id;

    public PersonLoader(Context context, long id) {

        super(context, Resume.People.PERSON_MAPPER);
        this.id = id;

    }

    @Override
    Cursor queryDelegate(ResumeRetriever provider) {

        Timber.i("Query for a single person with id (%s)", id);
        return provider.queryPerson(id);

    }

    @Override
    public List<Resume.People> initializeResultSize() {

        // Only 1 person is ever returned by this loader
        return new ArrayList<>(1);

    }
}