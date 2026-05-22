package com.guelar.persistencia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AndroidVersionsDbHelper extends SQLiteOpenHelper {

    public static final String DB_NAME    = "versiones_android.db";
    public static final String TABLE_NAME = "versiones";
    public static final String COL_ID     = "_id";
    public static final String COL_NOMBRE = "nombre";
    public static final String COL_ANIO   = "anio";

    private static final int DB_VERSION = 1;

    private static final String CREAR_TABLA =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COL_ID     + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COL_NOMBRE + " TEXT NOT NULL, " +
                    COL_ANIO   + " INTEGER NOT NULL)";

    public AndroidVersionsDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREAR_TABLA);
        db.execSQL("INSERT INTO " + TABLE_NAME + " (nombre, anio) VALUES ('Apple Pie 1.0', 2008)");
        db.execSQL("INSERT INTO " + TABLE_NAME + " (nombre, anio) VALUES ('Cupcake 1.5', 2009)");
        db.execSQL("INSERT INTO " + TABLE_NAME + " (nombre, anio) VALUES ('Donut 1.6', 2009)");
        db.execSQL("INSERT INTO " + TABLE_NAME + " (nombre, anio) VALUES ('Eclair 2.0', 2009)");
        db.execSQL("INSERT INTO " + TABLE_NAME + " (nombre, anio) VALUES ('Froyo 2.2', 2010)");
        db.execSQL("INSERT INTO " + TABLE_NAME + " (nombre, anio) VALUES ('Gingerbread 2.3', 2010)");
        db.execSQL("INSERT INTO " + TABLE_NAME + " (nombre, anio) VALUES ('Oreo 8.0', 2017)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}