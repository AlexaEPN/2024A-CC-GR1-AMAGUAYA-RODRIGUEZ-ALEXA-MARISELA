package com.example.a2024accgr1amar

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class AddSongActivity : AppCompatActivity() {

    private lateinit var repository: Repository
    private var albumId: Int = 0
    private var songId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_song)

        repository = Repository(this)
        albumId = intent.getIntExtra("ALBUM_ID", 0)
        songId = intent.getIntExtra("SONG_ID", 0)

        val etSongName: EditText = findViewById(R.id.etSongName)
        val etSongDuration: EditText = findViewById(R.id.etSongDuration)
        val etSongReproductions: EditText = findViewById(R.id.etSongReproductions)
        val etSongLikes: EditText = findViewById(R.id.etSongLikes)
        val btnSaveSong: Button = findViewById(R.id.btnSaveSong)

        if (songId != 0) {
            // Cargar los datos de la canción para editar
            val song = repository.getCancionesForAlbum(albumId).find { it.idCancion == songId }
            song?.let {
                etSongName.setText(it.nombreCancion)
                etSongDuration.setText(it.duracionCancion)
                etSongReproductions.setText(it.reproducciones.toString())
                etSongLikes.setText(it.meGusta.toString())
            }
        }

        btnSaveSong.setOnClickListener {
            val nombre = etSongName.text.toString()
            val duracion = etSongDuration.text.toString()
            val reproducciones = etSongReproductions.text.toString().toFloat()
            val meGusta = etSongLikes.text.toString().toFloat()

            val cancion = BCancion(songId, nombre, duracion, reproducciones, meGusta)
            if (songId == 0) {
                repository.addCancion(cancion, albumId)  // Añadir nueva canción
            } else {
                repository.updateCancion(cancion)  // Actualizar canción existente
            }

            finish()  // Cerrar la actividad y regresar a la pantalla de detalles del álbum
        }
    }
}
