package com.guelar.persistencia;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class VersionesProvider extends ContentProvider {

    public static final String AUTHORITY =
            "com.guelar.persistencia.provider";
    public static final Uri CONTENT_URI =
            Uri.parse("content://" + AUTHORITY + "/versiones");

    private static final int CODE_TODAS  = 1;
    private static final int CODE_UNA    = 2;

    private static final UriMatcher uriMatcher =
            new UriMatcher(UriMatcher.NO_MATCH);

    static {
        uriMatcher.addURI(AUTHORITY, "versiones",   CODE_TODAS);
        uriMatcher.addURI(AUTHORITY, "versiones/#", CODE_UNA);
    }

    private AndroidVersionsDbHelper dbHelper;

    @Override
    public boolean onCreate() {
        dbHelper = new AndroidVersionsDbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor;
        if (uriMatcher.match(uri) == CODE_TODAS) {
            cursor = db.query(AndroidVersionsDbHelper.TABLE_NAME,
                    projection, selection, selectionArgs, null, null, sortOrder);
        } else {
            String id = uri.getLastPathSegment();
            cursor = db.query(AndroidVersionsDbHelper.TABLE_NAME,
                    projection, AndroidVersionsDbHelper.COL_ID + "=?",
                    new String[]{id}, null, null, null);
        }
        if (cursor != null && getContext() != null) {
            cursor.setNotificationUri(getContext().getContentResolver(), uri);
        }
        return cursor;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, ContentValues values) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long id = db.insert(AndroidVersionsDbHelper.TABLE_NAME, null, values);
        if (id > 0 && getContext() != null) {
            getContext().getContentResolver().notifyChange(CONTENT_URI, null);
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int filas;
        if (uriMatcher.match(uri) == CODE_UNA) {
            String id = uri.getLastPathSegment();
            filas = db.delete(AndroidVersionsDbHelper.TABLE_NAME,
                    AndroidVersionsDbHelper.COL_ID + "=?",
                    new String[]{id});
        } else {
            filas = db.delete(AndroidVersionsDbHelper.TABLE_NAME, selection, selectionArgs);
        }
        if (filas > 0 && getContext() != null) {
            getContext().getContentResolver().notifyChange(CONTENT_URI, null);
        }
        return filas;
    }

    @Override
    public int update(@NonNull Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) { return 0; }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) { return null; }
}