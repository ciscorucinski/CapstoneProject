package io.github.ciscorucinski.personal.intro.loader;

import android.content.Context;
import android.database.Cursor;

import io.github.ciscorucinski.personal.intro.model.Resume;
import io.github.ciscorucinski.personal.intro.provider.ResumeRetriever;

public class ContributionLoader extends ResumeProviderLoader<Resume.Contribution> {

    public ContributionLoader(Context context) {

        super(context, Resume.Contribution.MAPPER);

    }

    @Override
    Cursor queryDelegate(ResumeRetriever provider) {

        return provider.queryAllContributions();

    }
}