package io.github.ciscorucinski.personal.intro.provider;

import android.database.Cursor;

public interface ResumeRetriever {

    Cursor queryAllContributions();

    Cursor queryAllEducation();

    Cursor queryAllProjects();

    Cursor queryAllSkills();

    Cursor queryAllWork();

    Cursor queryAllPeople();

    Cursor queryPerson(long id);
//	Cursor queryPersonContactInfo(int id);

}