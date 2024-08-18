package com.example.a2024accgr1amar

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "music_db"

        const val TABLE_ALBUM = "album"
        const val TABLE_CANCION = "cancion"

        const val COLUMN_ALBUM_ID = "id"
        const val COLUMN_ALBUM_NOMBRE = "nombre_album"
        const val COLUMN_ALBUM_COMPOSITOR = "nombre_compositor"
        const val COLUMN_ALBUM_DESCRIPCION = "descripcion_album"

        const val COLUMN_CANCION_ID = "id"
        const val COLUMN_CANCION_NOMBRE = "nombre_cancion"
        const val COLUMN_CANCION_DURACION = "duracion_cancion"
        const val COLUMN_CANCION_REPRODUCCIONES = "reproducciones"
        const val COLUMN_CANCION_ME_GUSTA = "me_gusta"
        const val COLUMN_CANCION_ALBUM_ID = "album_id"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createAlbumTable = ("CREATE TABLE $TABLE_ALBUM (" +
                "$COLUMN_ALBUM_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$COLUMN_ALBUM_NOMBRE TEXT," +
                "$COLUMN_ALBUM_COMPOSITOR TEXT," +
                "$COLUMN_ALBUM_DESCRIPCION TEXT)")
        db.execSQL(createAlbumTable)

        val createCancionTable = ("CREATE TABLE $TABLE_CANCION (" +
                "$COLUMN_CANCION_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$COLUMN_CANCION_NOMBRE TEXT," +
                "$COLUMN_CANCION_DURACION TEXT," +
                "$COLUMN_CANCION_REPRODUCCIONES REAL," +
                "$COLUMN_CANCION_ME_GUSTA REAL," +
                "$COLUMN_CANCION_ALBUM_ID INTEGER," +
                "FOREIGN KEY($COLUMN_CANCION_ALBUM_ID) REFERENCES $TABLE_ALBUM($COLUMN_ALBUM_ID))")
        db.execSQL(createCancionTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_CANCION")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_ALBUM")
        onCreate(db)
    }
}
