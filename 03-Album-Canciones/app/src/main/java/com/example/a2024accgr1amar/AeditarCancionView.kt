package com.example.a2024accgr1amar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class AeditarCancionView : AppCompatActivity() {

    var albumId: Int = -1
    var cancionId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aeditar_cancion_view)

        albumId = intent.getIntExtra("album_id", -1)
        cancionId = intent.getIntExtra("cancion_id", -1)

        val inputNombreCancion = findViewById<EditText>(R.id.input_editar_cancion)
        val inputDuracionCancion = findViewById<EditText>(R.id.input_duracion)
        val inputReproducciones = findViewById<EditText>(R.id.input_reproducciones)
        val inputMeGusta = findViewById<EditText>(R.id.input_likes)
        val botonGuardarCancion = findViewById<Button>(R.id.btn_editar_cancion)

        if (cancionId != -1) {
            val cancion = BBaseDatosMemoria.obtenerCancionPorId(albumId, cancionId)

            inputNombreCancion.setText(cancion?.nombreCancion ?: "")
            inputDuracionCancion.setText(cancion?.duracionCancion ?: "")
            inputReproducciones.setText(cancion?.reproducciones?.toString() ?: "")
            inputMeGusta.setText(cancion?.meGusta?.toString() ?: "")

            botonGuardarCancion.setOnClickListener {
                val nuevoNombre = inputNombreCancion.text.toString()
                val nuevaDuracion = inputDuracionCancion.text.toString()
                val nuevasReproducciones = inputReproducciones.text.toString().toFloatOrNull() ?: 0f
                val nuevosMeGusta = inputMeGusta.text.toString().toFloatOrNull() ?: 0f

                if (cancion != null) {
                    cancion.nombreCancion = nuevoNombre
                    cancion.duracionCancion = nuevaDuracion
                    cancion.reproducciones = nuevasReproducciones
                    cancion.meGusta = nuevosMeGusta

                    val actualizado = BBaseDatosMemoria.actualizarCancion(albumId, cancion)
                    if (actualizado) {
                        Toast.makeText(this, "Canción actualizada", Toast.LENGTH_SHORT).show()
                        finish()
                    } else {
                        Toast.makeText(this, "Error al actualizar la canción", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        } else {
            botonGuardarCancion.setOnClickListener {
                val nuevoNombre = inputNombreCancion.text.toString()
                val nuevaDuracion = inputDuracionCancion.text.toString()
                val nuevasReproducciones = inputReproducciones.text.toString().toFloatOrNull() ?: 0f
                val nuevosMeGusta = inputMeGusta.text.toString().toFloatOrNull() ?: 0f

                val nuevaCancion = BCancion(
                    idCancion = BBaseDatosMemoria.obtenerTodosLosAlbumes().flatMap { it.canciones }.maxOfOrNull { it.idCancion }?.plus(1) ?: 1,
                    nombreCancion = nuevoNombre,
                    duracionCancion = nuevaDuracion,
                    reproducciones = nuevasReproducciones,
                    meGusta = nuevosMeGusta
                )

                val agregado = BBaseDatosMemoria.agregarCancionAAlbum(albumId, nuevaCancion)
                if (agregado) {
                    Toast.makeText(this, "Canción agregada", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(this, "Error al agregar la canción", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
