    package io.github.ciscorucinski.personal.intro.provider;

    import android.content.ContentProvider;
    import android.content.ContentValues;
    import android.database.Cursor;
    import android.database.sqlite.SQLiteDatabase;
    import android.net.Uri;
    import android.support.annotation.Nullable;

    import io.github.ciscorucinski.personal.intro.model.Resume;
    import io.github.ciscorucinski.personal.intro.provider.database.ResumeDatabase;

    public class ResumeProvider extends ContentProvider implements ResumeRetriever {

        private static ResumeDatabase database;

        @Override
        public boolean onCreate() {
            database = new ResumeDatabase(getContext());
            return true;
        }

        /**
         * By default, this method throws an {@link java.lang.UnsupportedOperationException}. You must
         * subclass {@link ResumeProvider} if you want to provide different functionality.
         */
        @Nullable @Override
        public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
            throw new UnsupportedOperationException("No external queries");
        }

        /**
         * By default, this method throws an {@link java.lang.UnsupportedOperationException}. You must
         * subclass {@link ResumeProvider} if you want to provide different functionality.
         */
        @Nullable @Override
        public String getType(Uri uri) {
            throw new UnsupportedOperationException("Uri's are not supported");
        }

        /**
         * By default, this method throws an {@link java.lang.UnsupportedOperationException}. You must
         * subclass {@link ResumeProvider} if you want to provide different functionality.
         */
        @Nullable @Override
        public Uri insert(Uri uri, ContentValues values) {
            throw new UnsupportedOperationException("No external inserts");
        }

        /**
         * By default, this method throws an {@link java.lang.UnsupportedOperationException}. You must
         * subclass {@link ResumeProvider} if you want to provide different functionality.
         */
        @Override
        public int delete(Uri uri, String selection, String[] selectionArgs) {
            throw new UnsupportedOperationException("No external deletes");
        }

        /**
         * By default, this method throws an {@link java.lang.UnsupportedOperationException}. You must
         * subclass {@link ResumeProvider} if you want to provide different functionality.
         */
        @Override
        public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
            throw new UnsupportedOperationException("No external updates");
        }

        @Override
        public Cursor queryAllContributions() {
            SQLiteDatabase db = database.getReadableDatabase();
            return db.rawQuery(Resume.Contribution.GET_ALL_CONTRIBUTIONS, null);
        }

        @Override
        public Cursor queryAllEducation() {
            SQLiteDatabase db = database.getReadableDatabase();
            return db.rawQuery(Resume.Education.GET_ALL_EDUCATION, null);
        }

        @Override
        public Cursor queryAllProjects() {
            SQLiteDatabase db = database.getReadableDatabase();
            return db.rawQuery(Resume.Project.GET_PROJECTS, null);
        }

        @Override
        public Cursor queryAllSkills() {
            SQLiteDatabase db = database.getReadableDatabase();
            return db.rawQuery(Resume.Skill.GET_ALL_SKILLS, null);
        }

        @Override
        public Cursor queryAllWork() {
            SQLiteDatabase db = database.getReadableDatabase();
            return db.rawQuery(Resume.Work.GET_ALL_WORK, null);
        }

        @Override
        public Cursor queryAllPeople() {
            SQLiteDatabase db = database.getReadableDatabase();
            return db.rawQuery(Resume.People.GET_ALL_PERSONS, null);
        }

        @Override
        public Cursor queryPerson(long id) {
            SQLiteDatabase db = database.getReadableDatabase();
            return db.rawQuery(Resume.People.GET_PERSON, new String[] { String.valueOf(id) });
        }

    }
