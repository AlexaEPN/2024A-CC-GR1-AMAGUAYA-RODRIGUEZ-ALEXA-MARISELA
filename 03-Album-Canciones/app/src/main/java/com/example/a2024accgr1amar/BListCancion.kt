package com.example.a2024accgr1amar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

class BListCancion : AppCompatActivity() {

    private var albumId: Int = -1
    private lateinit var listaCanciones: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blist_cancion)

        albumId = intent.getIntExtra("album_id", -1)
        listaCanciones = findViewById(R.id.lv_list_view_cancion)

        if (albumId != -1) {
            val album = BBaseDatosMemoria.obtenerAlbumPorId(albumId)
            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, album?.canciones ?: emptyList())
            listaCanciones.adapter = adapter

            // Registrar el ListView para el menú contextual
            registerForContextMenu(listaCanciones)
            val inputVerAlbum = findViewById<EditText>(R.id.input_ver_album)
            inputVerAlbum.setText(album?.nombreAlbum ?: "")

        } else {
            Toast.makeText(this, "Álbum no encontrado", Toast.LENGTH_SHORT).show()
        }

        val inputNombreCancion = findViewById<EditText>(R.id.input_nombre_cancion)
        val botonCrearCancion = findViewById<Button>(R.id.btn_crear_cacion)


        botonCrearCancion.setOnClickListener {
            val nombreCancion = inputNombreCancion.text.toString()
            if (nombreCancion.isNotEmpty()) {
                val nuevoId = (BBaseDatosMemoria.obtenerAlbumPorId(albumId)?.canciones?.size ?: 0) + 1
                val nuevaCancion = BCancion(nuevoId, nombreCancion, "", 0f, 0f)
                val agregado = BBaseDatosMemoria.agregarCancionAAlbum(albumId, nuevaCancion)

                if (agregado) {
                    mostrarSnackbar("Canción agregada: $inputNombreCancion")
                    onResume()  // Refrescar la lista de canciones
                    // Limpiar el campo de texto después de agregar la canción
                    findViewById<EditText>(R.id.input_nombre_cancion).text.clear()
                } else {
                    mostrarSnackbar("Error al agregar la canción")
                }
            } else {
                mostrarSnackbar("Nombre de la canción no puede estar vacío")
            }
        }


    }

    override fun onResume() {
        super.onResume()
        // Refrescar la lista de canciones al regresar a esta actividad
        val album = BBaseDatosMemoria.obtenerAlbumPorId(albumId)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, album?.canciones ?: emptyList())
        listaCanciones.adapter = adapter
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        val cancion = BBaseDatosMemoria.obtenerCancionPorId(albumId, info.position)
        return when (item.itemId) {
            R.id.mi_editar -> {
                val intent = Intent(this, AeditarCancionView::class.java)
                intent.putExtra("album_id", albumId)
                intent.putExtra("cancion_id", cancion?.idCancion)
                startActivity(intent)
                true
            }
            R.id.mi_eliminar -> {
                if (cancion != null) {
                    val eliminado = BBaseDatosMemoria.eliminarCancionDeAlbum(albumId, cancion.idCancion)
                    if (eliminado) {
                        Toast.makeText(this, "Canción eliminada", Toast.LENGTH_SHORT).show()
                        // Refrescar la lista de canciones
                        onResume()
                    } else {
                        Toast.makeText(this, "Error al eliminar la canción", Toast.LENGTH_SHORT).show()
                    }
                }
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    fun mostrarSnackbar(texto: String) {
        val snack = Snackbar.make(
            findViewById(R.id.cl_layout_cancion),
            texto,
            Snackbar.LENGTH_INDEFINITE
        )
        snack.show()
    }
}
