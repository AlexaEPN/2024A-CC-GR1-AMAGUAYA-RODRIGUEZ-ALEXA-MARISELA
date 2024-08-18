package com.example.a2024accgr1amar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class AeditarAlbumView : AppCompatActivity() {
    var albumId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aeditar_album_view)

        albumId = intent.getIntExtra("album_id", -1)

        if (albumId != -1) {
            val album = BBaseDatosMemoria.obtenerAlbumPorId(albumId)
            val inputNombre = findViewById<EditText>(R.id.input_nombre_album)
            val inputCompositor = findViewById<EditText>(R.id.input_compositor_album)
            val inputDescripcion = findViewById<EditText>(R.id.input_descripcion_album)

            inputNombre.setText(album?.nombreAlbum ?: "")
            inputCompositor.setText(album?.nombreCompositor ?: "")
            inputDescripcion.setText(album?.descripcionAlbum ?: "")

            val botonEditarAlbum = findViewById<Button>(R.id.btn_editar_abum)
            botonEditarAlbum.setOnClickListener {
                val nuevoNombre = inputNombre.text.toString()
                val nuevoCompositor = inputCompositor.text.toString()
                val nuevaDescripcion = inputDescripcion.text.toString()

                if (album != null) {
                    album.nombreAlbum = nuevoNombre
                    album.nombreCompositor = nuevoCompositor
                    album.descripcionAlbum = nuevaDescripcion

                    val actualizado = BBaseDatosMemoria.actualizarAlbum(album)
                    if (actualizado) {
                        Toast.makeText(this, "Álbum actualizado", Toast.LENGTH_SHORT).show()
                        irActividad(BListAlbum::class.java)
                        finish()
                    } else {
                        Toast.makeText(this, "Error al actualizar el álbum", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        } else {
            Toast.makeText(this, "Álbum no encontrado", Toast.LENGTH_SHORT).show()
        }
    }
    fun irActividad(clase: Class<*>){
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}