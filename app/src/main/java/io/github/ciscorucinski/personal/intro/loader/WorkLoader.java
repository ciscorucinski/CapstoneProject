package io.github.ciscorucinski.personal.intro.loader;

import android.content.Context;
import android.database.Cursor;

import io.github.ciscorucinski.personal.intro.model.Resume;
import io.github.ciscorucinski.personal.intro.provider.ResumeRetriever;

public class WorkLoader extends ResumeProviderLoader<Resume.Work> {

    public WorkLoader(Context context) {

        super(context, Resume.Work.MAPPER);

    }

    @Override
    Cursor queryDelegate(ResumeRetriever provider) {

        return provider.queryAllWork();

    }

}