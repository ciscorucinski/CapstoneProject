package io.github.ciscorucinski.personal.intro.loader;

import android.content.Context;
import android.database.Cursor;

import io.github.ciscorucinski.personal.intro.model.Resume;
import io.github.ciscorucinski.personal.intro.provider.ResumeRetriever;

public class PeopleLoader extends ResumeProviderLoader<Resume.People> {

    public PeopleLoader(Context context) {

        super(context, Resume.People.PEOPLE_MAPPER);

    }

    @Override
    Cursor queryDelegate(ResumeRetriever provider) {

        return provider.queryAllPeople();

    }
}