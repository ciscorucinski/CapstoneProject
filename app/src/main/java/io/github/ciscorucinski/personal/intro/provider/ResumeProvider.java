package io.github.ciscorucinski.personal.intro.provider;

import android.content.ContentProvider;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.OperationApplicationException;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import net.simonvt.schematic.provider.ResumeDatabase;
import net.simonvt.schematic.utils.SelectionBuilder;

import java.util.ArrayList;

import io.github.ciscorucinski.personal.intro.model.Resume;
import timber.log.Timber;

public class ResumeProvider extends ContentProvider implements ResumeRetriever {

    static final String AUTHORITY = "io.github.ciscorucinski.personal.intro";

    private static final int CONTRIBUTION_CONTENT_URI = 0;
    private static final int EDUCATION_CONTENT_URI = 1;
    private static final int PROJECT_CONTENT_URI = 2;
    private static final int SKILL_CONTENT_URI = 3;
    private static final int WORK_CONTENT_URI = 4;
    private static final int PERSON_ID_CONTENT_URI = 5;
    private static final int PERSON_CONTENT_URI = 6;

    private static final UriMatcher MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
    private static ResumeDatabase database;

    static {
        MATCHER.addURI(AUTHORITY, "contribution", CONTRIBUTION_CONTENT_URI);
        MATCHER.addURI(AUTHORITY, "education", EDUCATION_CONTENT_URI);
        MATCHER.addURI(AUTHORITY, "projects", PROJECT_CONTENT_URI);
        MATCHER.addURI(AUTHORITY, "skills", SKILL_CONTENT_URI);
        MATCHER.addURI(AUTHORITY, "work", WORK_CONTENT_URI);
        MATCHER.addURI(AUTHORITY, "people/#", PERSON_ID_CONTENT_URI);
        MATCHER.addURI(AUTHORITY, "people", PERSON_CONTENT_URI);
    }

    @Override
    public boolean onCreate() {
        database = ResumeDatabase.getInstance(getContext());
        return true;
    }

    private SelectionBuilder getBuilder(String table) {
        SelectionBuilder builder = new SelectionBuilder();
        return builder;
    }

    private void insertValues(SQLiteDatabase db, String table, ContentValues[] values) {
        for (ContentValues cv : values) {
            db.insertOrThrow(table, null, cv);
        }
    }

    @Override
    public int bulkInsert(Uri uri, ContentValues[] values) {
        final SQLiteDatabase db = database.getWritableDatabase();
        db.beginTransaction();

        switch (MATCHER.match(uri)) {
            case CONTRIBUTION_CONTENT_URI: {
                insertValues(db, "contribution", values);
            }
            case EDUCATION_CONTENT_URI: {
                insertValues(db, "education", values);
            }
            case PROJECT_CONTENT_URI: {
                insertValues(db, "projects", values);
            }
            case SKILL_CONTENT_URI: {
                insertValues(db, "skills", values);
            }
            case WORK_CONTENT_URI: {
                insertValues(db, "work", values);
            }
            case PERSON_ID_CONTENT_URI: {
                insertValues(db, "people", values);
            }
            case PERSON_CONTENT_URI: {
                insertValues(db, "people", values);
            }
        }

        db.setTransactionSuccessful();
        db.endTransaction();
        getContext().getContentResolver().notifyChange(uri, null);
        return values.length;
    }

    @Override
    public ContentProviderResult[] applyBatch(ArrayList<ContentProviderOperation> ops)
            throws OperationApplicationException {
        database.getWritableDatabase().beginTransaction();
        ContentProviderResult[] results = super.applyBatch(ops);
        database.getWritableDatabase().setTransactionSuccessful();
        database.getWritableDatabase().endTransaction();
        return results;
    }

    @Override
    public String getType(Uri uri) {
        switch (MATCHER.match(uri)) {
            case CONTRIBUTION_CONTENT_URI: {
                return "vnd.android.cursor.dir/contribution";
            }
            case EDUCATION_CONTENT_URI: {
                return "vnd.android.cursor.dir/education";
            }
            case PROJECT_CONTENT_URI: {
                return "vnd.android.cursor.dir/projects";
            }
            case SKILL_CONTENT_URI: {
                return "vnd.android.cursor.dir/skills";
            }
            case WORK_CONTENT_URI: {
                return "vnd.android.cursor.dir/work";
            }
            case PERSON_ID_CONTENT_URI: {
                return "vnd.android.cursor.item/people";
            }
            case PERSON_CONTENT_URI: {
                return "vnd.android.cursor.item/people";
            }
            default: {
                throw new IllegalArgumentException("Unknown URI " + uri);
            }
        }
    }

    /**
     * This is the preferred query method to call. The overridden query method does not use any
     * of the provided parameters except for the uri
     *
     * @param uri The URI to query.
     * @return a Cursor or null.
     */
    public Cursor query(Uri uri) {
        return query(uri, null, null, null, null);
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
                        String sortOrder) {

        final SQLiteDatabase db = database.getReadableDatabase();

        switch (MATCHER.match(uri)) {
            case CONTRIBUTION_CONTENT_URI: {
                Cursor cursor = queryAllContributions();
                cursor.setNotificationUri(getContext().getContentResolver(), uri);
                return cursor;
            }
            case EDUCATION_CONTENT_URI: {
                Cursor cursor = queryAllEducation();
                cursor.setNotificationUri(getContext().getContentResolver(), uri);
                return cursor;
            }
            case PROJECT_CONTENT_URI: {
                Cursor cursor = queryAllProjects();
                cursor.setNotificationUri(getContext().getContentResolver(), uri);
                return cursor;
            }
            case SKILL_CONTENT_URI: {
                Cursor cursor = queryAllSkills();
                cursor.setNotificationUri(getContext().getContentResolver(), uri);
                return cursor;
            }
            case WORK_CONTENT_URI: {
                Cursor cursor = queryAllWork();
                cursor.setNotificationUri(getContext().getContentResolver(), uri);
                return cursor;
            }
            case PERSON_ID_CONTENT_URI: {
                int personID = Integer.parseInt(uri.getPathSegments().get(1));
                Timber.i("%s - %s", "PERSON_ID_CONTENT_URI", uri);
                Timber.i("id (%s)", personID);
                Cursor cursor = queryPerson(personID);
                cursor.setNotificationUri(getContext().getContentResolver(), uri);
                return cursor;
            }
            case PERSON_CONTENT_URI: {
                Cursor cursor = queryAllPeople();
                cursor.setNotificationUri(getContext().getContentResolver(), uri);
                return cursor;
            }
            default: {
                throw new IllegalArgumentException("Unknown URI " + uri);
            }
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        final SQLiteDatabase db = database.getWritableDatabase();
        switch (MATCHER.match(uri)) {
            case CONTRIBUTION_CONTENT_URI: {
                final long id = db.insertOrThrow("contribution", null, values);
                getContext().getContentResolver().notifyChange(uri, null);
                return ContentUris.withAppendedId(uri, id);
            }
            case EDUCATION_CONTENT_URI: {
                final long id = db.insertOrThrow("education", null, values);
                getContext().getContentResolver().notifyChange(uri, null);
                return ContentUris.withAppendedId(uri, id);
            }
            case PROJECT_CONTENT_URI: {
                final long id = db.insertOrThrow("projects", null, values);
                getContext().getContentResolver().notifyChange(uri, null);
                return ContentUris.withAppendedId(uri, id);
            }
            case SKILL_CONTENT_URI: {
                final long id = db.insertOrThrow("skills", null, values);
                getContext().getContentResolver().notifyChange(uri, null);
                return ContentUris.withAppendedId(uri, id);
            }
            case WORK_CONTENT_URI: {
                final long id = db.insertOrThrow("work", null, values);
                getContext().getContentResolver().notifyChange(uri, null);
                return ContentUris.withAppendedId(uri, id);
            }
            case PERSON_ID_CONTENT_URI: {
                final long id = db.insertOrThrow("people", null, values);
                getContext().getContentResolver().notifyChange(uri, null);
                return ContentUris.withAppendedId(uri, id);
            }
            case PERSON_CONTENT_URI: {
                final long id = db.insertOrThrow("people", null, values);
                getContext().getContentResolver().notifyChange(uri, null);
                return ContentUris.withAppendedId(uri, id);
            }
            default: {
                throw new IllegalArgumentException("Unknown URI " + uri);
            }
        }
    }

    @Override
    public int update(Uri uri, ContentValues values, String where, String[] whereArgs) {
        final SQLiteDatabase db = database.getWritableDatabase();
        switch (MATCHER.match(uri)) {
            case CONTRIBUTION_CONTENT_URI: {
                SelectionBuilder builder = getBuilder("Contribution");
                builder.where(where, whereArgs);
                final int count = builder.table("contribution")
                        .update(db, values);
                if (count > 0) {
                    getContext().getContentResolver().notifyChange(uri, null);
                }
                return count;
            }
            case EDUCATION_CONTENT_URI: {
                SelectionBuilder builder = getBuilder("Education");
                builder.where(where, whereArgs);
                final int count = builder.table("education")
                        .update(db, values);
                if (count > 0) {
                    getContext().getContentResolver().notifyChange(uri, null);
                }
                return count;
            }
            case PROJECT_CONTENT_URI: {
                SelectionBuilder builder = getBuilder("Project");
                builder.where(where, whereArgs);
                final int count = builder.table("projects")
                        .update(db, values);
                if (count > 0) {
                    getContext().getContentResolver().notifyChange(uri, null);
                }
                return count;
            }
            case SKILL_CONTENT_URI: {
                SelectionBuilder builder = getBuilder("Skill");
                builder.where(where, whereArgs);
                final int count = builder.table("skills")
                        .update(db, values);
                if (count > 0) {
                    getContext().getContentResolver().notifyChange(uri, null);
                }
                return count;
            }
            case WORK_CONTENT_URI: {
                SelectionBuilder builder = getBuilder("Work");
                builder.where(where, whereArgs);
                final int count = builder.table("work")
                        .update(db, values);
                if (count > 0) {
                    getContext().getContentResolver().notifyChange(uri, null);
                }
                return count;
            }
            case PERSON_ID_CONTENT_URI: {
                SelectionBuilder builder = getBuilder("Person");
                builder.where("_id=?", uri.getPathSegments().get(1));
                builder.where(where, whereArgs);
                final int count = builder.table("people")
                        .update(db, values);
                if (count > 0) {
                    getContext().getContentResolver().notifyChange(uri, null);
                }
                return count;
            }
            case PERSON_CONTENT_URI: {
                SelectionBuilder builder = getBuilder("Person");
                builder.where(where, whereArgs);
                final int count = builder.table("people")
                        .update(db, values);
                if (count > 0) {
                    getContext().getContentResolver().notifyChange(uri, null);
                }
                return count;
            }
            default: {
                throw new IllegalArgumentException("Unknown URI " + uri);
            }
        }
    }

    @Override
    public int delete(Uri uri, String where, String[] whereArgs) {
        final SQLiteDatabase db = database.getWritableDatabase();
        switch (MATCHER.match(uri)) {
            case CONTRIBUTION_CONTENT_URI: {
                SelectionBuilder builder = getBuilder("Contribution");
                builder.where(where, whereArgs);
                final int count = builder
                        .table("contribution")
                        .delete(db);
                getContext().getContentResolver().notifyChange(uri, null);
                return count;
            }
            case EDUCATION_CONTENT_URI: {
                SelectionBuilder builder = getBuilder("Education");
                builder.where(where, whereArgs);
                final int count = builder
                        .table("education")
                        .delete(db);
                getContext().getContentResolver().notifyChange(uri, null);
                return count;
            }
            case PROJECT_CONTENT_URI: {
                SelectionBuilder builder = getBuilder("Project");
                builder.where(where, whereArgs);
                final int count = builder
                        .table("projects")
                        .delete(db);
                getContext().getContentResolver().notifyChange(uri, null);
                return count;
            }
            case SKILL_CONTENT_URI: {
                SelectionBuilder builder = getBuilder("Skill");
                builder.where(where, whereArgs);
                final int count = builder
                        .table("skills")
                        .delete(db);
                getContext().getContentResolver().notifyChange(uri, null);
                return count;
            }
            case WORK_CONTENT_URI: {
                SelectionBuilder builder = getBuilder("Work");
                builder.where(where, whereArgs);
                final int count = builder
                        .table("work")
                        .delete(db);
                getContext().getContentResolver().notifyChange(uri, null);
                return count;
            }
            case PERSON_ID_CONTENT_URI: {
                SelectionBuilder builder = getBuilder("Person");
                builder.where("_id=?", uri.getPathSegments().get(1));
                builder.where(where, whereArgs);
                final int count = builder
                        .table("people")
                        .delete(db);
                getContext().getContentResolver().notifyChange(uri, null);
                return count;
            }
            case PERSON_CONTENT_URI: {
                SelectionBuilder builder = getBuilder("Person");
                builder.where(where, whereArgs);
                final int count = builder
                        .table("people")
                        .delete(db);
                getContext().getContentResolver().notifyChange(uri, null);
                return count;
            }
            default: {
                throw new IllegalArgumentException("Unknown URI " + uri);
            }
        }
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

        Timber.i("Query String - \n%s\nWith id (%s)", Resume.People.GET_PERSON, id);
        SQLiteDatabase db = database.getReadableDatabase();
        return db.rawQuery(Resume.People.GET_PERSON,
                new String[]{String.valueOf(id)});

    }

//	@Override
//	public Cursor queryPersonContactInfo(int id) {
//
//		SQLiteDatabase db = database.getReadableDatabase();
//		return db.rawQuery(Resume.People.GET_NAVIAGATION_CONTACT_DATA_FOR,
//		                   new String[] { String.valueOf(id) });
//	}

}
