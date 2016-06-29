package io.github.ciscorucinski.personal.intro.loader;

import android.content.Context;
import android.database.Cursor;

import io.github.ciscorucinski.personal.intro.model.Resume;
import io.github.ciscorucinski.personal.intro.provider.ResumeRetriever;

public class ProjectLoader extends ResumeProviderLoader<Resume.Project> {

    public ProjectLoader(Context context) {

        super(context, Resume.Project.MAPPER);

    }

    @Override
    Cursor queryDelegate(ResumeRetriever provider) {

        return provider.queryAllProjects();

    }

}