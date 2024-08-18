package com.example.a2024accgr1amar

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class AddAlbumActivity : AppCompatActivity() {

    private lateinit var repository: Repository
    private var albumId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_album)

        repository = Repository(this)
        albumId = intent.getIntExtra("ALBUM_ID", 0)

        val etAlbumName: EditText = findViewById(R.id.etAlbumName)
        val etAlbumComposer: EditText = findViewById(R.id.etAlbumComposer)
        val etAlbumDescription: EditText = findViewById(R.id.etAlbumDescription)
        val btnSaveAlbum: Button = findViewById(R.id.btnSaveAlbum)

        btnSaveAlbum.setOnClickListener {
            val nombre = etAlbumName.text.toString()
            val compositor = etAlbumComposer.text.toString()
            val descripcion = etAlbumDescription.text.toString()

            val album = BAlbum(albumId, nombre, compositor, descripcion)
            if (albumId == 0) {
                repository.addAlbum(album)  // Añadir nuevo álbum
            } else {
                repository.updateAlbum(album)  // Actualizar álbum existente
            }

            finish()  // Cerrar la actividad y regresar a la pantalla principal
        }

        if (albumId != 0) {
            // Cargar los datos del álbum para editar
            val album = repository.getAllAlbums().find { it.id == albumId }
            album?.let {
                etAlbumName.setText(it.nombreAlbum)
                etAlbumComposer.setText(it.nombreCompositor)
                etAlbumDescription.setText(it.descripcionAlbum)
            }
        }
    }
}
