package com.example.a2024accgr1amar

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase

class Repository(context: Context) {
    private val dbHelper = DatabaseHelper(context)
    private val database: SQLiteDatabase = dbHelper.writableDatabase

    // CRUD for Album

    fun addAlbum(album: BAlbum) {
        val values = ContentValues().apply {
            put(DatabaseHelper.COLUMN_ALBUM_NOMBRE, album.nombreAlbum)
            put(DatabaseHelper.COLUMN_ALBUM_COMPOSITOR, album.nombreCompositor)
            put(DatabaseHelper.COLUMN_ALBUM_DESCRIPCION, album.descripcionAlbum)
        }
        database.insert(DatabaseHelper.TABLE_ALBUM, null, values)
    }

    fun getAllAlbums(): List<BAlbum> {
        val albums = mutableListOf<BAlbum>()
        val cursor = database.query(DatabaseHelper.TABLE_ALBUM, null, null, null, null, null, null)
        with(cursor) {
            while (moveToNext()) {
                val id = getInt(getColumnIndexOrThrow(DatabaseHelper.COLUMN_ALBUM_ID))
                val nombreAlbum = getString(getColumnIndexOrThrow(DatabaseHelper.COLUMN_ALBUM_NOMBRE))
                val compositor = getString(getColumnIndexOrThrow(DatabaseHelper.COLUMN_ALBUM_COMPOSITOR))
                val descripcion = getString(getColumnIndexOrThrow(DatabaseHelper.COLUMN_ALBUM_DESCRIPCION))
                val album = BAlbum(id, nombreAlbum, compositor, descripcion)
                albums.add(album)
            }
        }
        cursor.close()
        return albums
    }


    fun updateAlbum(album: BAlbum) {
        val values = ContentValues().apply {
            put(DatabaseHelper.COLUMN_ALBUM_NOMBRE, album.nombreAlbum)
            put(DatabaseHelper.COLUMN_ALBUM_COMPOSITOR, album.nombreCompositor)
            put(DatabaseHelper.COLUMN_ALBUM_DESCRIPCION, album.descripcionAlbum)
        }
        database.update(DatabaseHelper.TABLE_ALBUM, values, "${DatabaseHelper.COLUMN_ALBUM_ID} = ?", arrayOf(album.id.toString()))
    }

    fun deleteAlbum(albumId: Int) {
        database.delete(
            DatabaseHelper.TABLE_ALBUM,
            "${DatabaseHelper.COLUMN_ALBUM_ID} = ?",
            arrayOf(albumId.toString())
        )
    }

    // CRUD for Cancion

    fun addCancion(cancion: BCancion, albumId: Int) {
        val values = ContentValues().apply {
            put(DatabaseHelper.COLUMN_CANCION_NOMBRE, cancion.nombreCancion)
            put(DatabaseHelper.COLUMN_CANCION_DURACION, cancion.duracionCancion)
            put(DatabaseHelper.COLUMN_CANCION_REPRODUCCIONES, cancion.reproducciones)
            put(DatabaseHelper.COLUMN_CANCION_ME_GUSTA, cancion.meGusta)
            put(DatabaseHelper.COLUMN_CANCION_ALBUM_ID, albumId)
        }
        database.insert(DatabaseHelper.TABLE_CANCION, null, values)
    }

    fun getCancionesForAlbum(albumId: Int): List<BCancion> {
        val canciones = mutableListOf<BCancion>()
        val cursor = database.query(DatabaseHelper.TABLE_CANCION, null, "${DatabaseHelper.COLUMN_CANCION_ALBUM_ID} = ?", arrayOf(albumId.toString()), null, null, null)
        with(cursor) {
            while (moveToNext()) {
                val id = getInt(getColumnIndexOrThrow(DatabaseHelper.COLUMN_CANCION_ID))
                val nombreCancion = getString(getColumnIndexOrThrow(DatabaseHelper.COLUMN_CANCION_NOMBRE))
                val duracion = getString(getColumnIndexOrThrow(DatabaseHelper.COLUMN_CANCION_DURACION))
                val reproducciones = getFloat(getColumnIndexOrThrow(DatabaseHelper.COLUMN_CANCION_REPRODUCCIONES))
                val meGusta = getFloat(getColumnIndexOrThrow(DatabaseHelper.COLUMN_CANCION_ME_GUSTA))
                val cancion = BCancion(id, nombreCancion, duracion, reproducciones, meGusta)
                canciones.add(cancion)
            }
        }
        cursor.close()
        return canciones
    }

    fun updateCancion(cancion: BCancion) {
        val values = ContentValues().apply {
            put(DatabaseHelper.COLUMN_CANCION_NOMBRE, cancion.nombreCancion)
            put(DatabaseHelper.COLUMN_CANCION_DURACION, cancion.duracionCancion)
            put(DatabaseHelper.COLUMN_CANCION_REPRODUCCIONES, cancion.reproducciones)
            put(DatabaseHelper.COLUMN_CANCION_ME_GUSTA, cancion.meGusta)
        }
        database.update(DatabaseHelper.TABLE_CANCION, values, "${DatabaseHelper.COLUMN_CANCION_ID} = ?", arrayOf(cancion.idCancion.toString()))
    }

    fun deleteCancion(cancionId: Int) {
        database.delete(
            DatabaseHelper.TABLE_CANCION,
            "${DatabaseHelper.COLUMN_CANCION_ID} = ?",
            arrayOf(cancionId.toString())
        )
    }

}
