package io.github.ciscorucinski.personal.intro.loader;

import android.content.Context;
import android.database.Cursor;

import io.github.ciscorucinski.personal.intro.model.Resume;
import io.github.ciscorucinski.personal.intro.provider.ResumeRetriever;

public class SkillLoader extends ResumeProviderLoader<Resume.Skill> {

    public SkillLoader(Context context) {

        super(context, Resume.Skill.MAPPER);

    }

    @Override
    Cursor queryDelegate(ResumeRetriever provider) {

        return provider.queryAllSkills();

    }

}