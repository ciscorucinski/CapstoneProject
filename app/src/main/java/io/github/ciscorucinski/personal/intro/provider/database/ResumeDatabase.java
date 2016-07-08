    package io.github.ciscorucinski.personal.intro.provider.database;

    import android.content.Context;
    import android.database.sqlite.SQLiteDatabase;
    import android.database.sqlite.SQLiteOpenHelper;

    import io.github.ciscorucinski.personal.intro.model.Resume;

    public class ResumeDatabase extends SQLiteOpenHelper {

        public ResumeDatabase(Context context) {
            super(context, Resume.DATABASE_NAME, null, Resume.DATABASE_VERSION);
        }

        public SQLiteDatabase getDatabase() {
            return this.getReadableDatabase();
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(Resume.People.CREATE_TABLE);
            db.execSQL(Resume.Contribution.CREATE_TABLE);
            db.execSQL(Resume.Education.CREATE_TABLE);
            db.execSQL(Resume.Project.CREATE_TABLE);
            db.execSQL(Resume.Skill.CREATE_TABLE);
            db.execSQL(Resume.Work.CREATE_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        }
    }
