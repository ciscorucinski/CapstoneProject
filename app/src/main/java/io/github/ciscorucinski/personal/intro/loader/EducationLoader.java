package io.github.ciscorucinski.personal.intro.loader;

import android.content.Context;
import android.database.Cursor;

import io.github.ciscorucinski.personal.intro.model.Resume;
import io.github.ciscorucinski.personal.intro.provider.ResumeRetriever;

public class EducationLoader extends ResumeProviderLoader<Resume.Education> {

    public EducationLoader(Context context) {

        super(context, Resume.Education.MAPPER);

    }

    @Override
    Cursor queryDelegate(ResumeRetriever provider) {

        return provider.queryAllEducation();

    }
}