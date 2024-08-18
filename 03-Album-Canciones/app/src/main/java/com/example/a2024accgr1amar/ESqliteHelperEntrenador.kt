package com.example.a2024accgr1amar
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

package com.example.a2024accgr1amar

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ESqliteHelperEntrenador(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "Entrenador.db"
        private const val TABLE_ENTRENADOR = "Entrenador"
        private const val COLUMN_ID = "id"
        private const val COLUMN_NOMBRE = "nombre"
        private const val COLUMN_DESCRIPCION = "descripcion"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = ("CREATE TABLE $TABLE_ENTRENADOR ("
                + "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "$COLUMN_NOMBRE TEXT, "
                + "$COLUMN_DESCRIPCION TEXT" + ")")
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_ENTRENADOR")
        onCreate(db)
    }

    // Métodos CRUD
    fun crearEntrenador(nombre: String, descripcion: String): Boolean {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NOMBRE, nombre)
            put(COLUMN_DESCRIPCION, descripcion)
        }
        val result = db.insert(TABLE_ENTRENADOR, null, values)
        db.close()
        return result != -1L
    }

    fun consultarEntrenadorPorID(id: Int): Entrenador? {
        val db = this.readableDatabase
        val cursor = db.query(TABLE_ENTRENADOR, arrayOf(COLUMN_ID, COLUMN_NOMBRE, COLUMN_DESCRIPCION),
            "$COLUMN_ID=?", arrayOf(id.toString()), null, null, null)
        if (cursor != null && cursor.moveToFirst()) {
            val nombre = cursor.getString(cursor.getColumnIndex(COLUMN_NOMBRE))
            val descripcion = cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPCION))
            cursor.close()
            db.close()
            return Entrenador(id, nombre, descripcion)
        }
        cursor?.close()
        db.close()
        return null
    }

    fun actualizarEntrenadorFormulario(nombre: String, descripcion: String, id: Int): Boolean {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NOMBRE, nombre)
            put(COLUMN_DESCRIPCION, descripcion)
        }
        val result = db.update(TABLE_ENTRENADOR, values, "$COLUMN_ID=?", arrayOf(id.toString()))
        db.close()
        return result > 0
    }

    fun eliminarEntrenadorFormulario(id: Int): Boolean {
        val db = this.writableDatabase
        val result = db.delete(TABLE_ENTRENADOR, "$COLUMN_ID=?", arrayOf(id.toString()))
        db.close()
        return result > 0
    }
}
