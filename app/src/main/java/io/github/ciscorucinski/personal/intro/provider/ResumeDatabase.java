package io.github.ciscorucinski.personal.intro.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import net.simonvt.schematic.annotation.Database;
import net.simonvt.schematic.annotation.OnCreate;
import net.simonvt.schematic.annotation.Table;

import io.github.ciscorucinski.personal.intro.model.Resume;

@Database(version = ResumeDatabase.VERSION)
public class ResumeDatabase {

    @Table(MyContract.ContributionColumns.class)
    public static final String CONTRIBUTIONS = MyContract.Contribution;
    @Table(MyContract.EducationColumns.class)
    public static final String EDUCATION = MyContract.Education;
    @Table(MyContract.ProjectColumns.class)
    public static final String PROJECTS = MyContract.Project;
    @Table(MyContract.SkillColumns.class)
    public static final String SKILLS = MyContract.Skill;
    @Table(MyContract.WorkColumns.class)
    public static final String WORK = MyContract.Work;
    @Table(MyContract.PeopleColumns.class)
    public static final String PEOPLE = MyContract.People;

    @SuppressWarnings("WeakerAccess")
    static final int VERSION = 1;

    private ResumeDatabase() {
    }

    @OnCreate
    public static void onCreate(Context context, SQLiteDatabase db) {

        db.execSQL(Resume.Contribution.INSERT_DATA_FOR_CONTRIBUTION);
        db.execSQL(Resume.Education.INSERT_DATA_FOR_EDUCATION);
        db.execSQL(Resume.People.INSERT_DATA_FOR_CHRIS);
        db.execSQL(Resume.Project.INSERT_DATA_FOR_PROJECTS);
        db.execSQL(Resume.Skill.INSERT_DATA_FOR_SKILLS);
        db.execSQL(Resume.Work.INSERT_DATA_FOR_WORK);


    }

}