package com.example.a2024accgr1amar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar

class BListAlbum : AppCompatActivity() {

    val arreglo = BBaseDatosMemoria.arregloBAlbum
    var posicionItemSeleccionado = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blist_view)

        val listView = findViewById<ListView>(R.id.lv_list_view)
        val adaptador = ArrayAdapter(
            this, // contexto
            android.R.layout.simple_list_item_1, // layout xml a usar
            arreglo
        )
        listView.adapter = adaptador
        adaptador.notifyDataSetChanged() // Refrescar la interfaz

        val inputNombreAlbum = findViewById<EditText>(R.id.input_album)
        val botonCrearAlbum = findViewById<Button>(R.id.btn_crear_album)

        botonCrearAlbum.setOnClickListener {
            val nombreAlbum = inputNombreAlbum.text.toString()
            if (nombreAlbum.isNotEmpty()) {
                // Crear un nuevo álbum con el nombre ingresado y atributos nulos
                val nuevoAlbum = BAlbum(arreglo.size + 1, nombreAlbum, "", "")
                BBaseDatosMemoria.agregarAlbum(nuevoAlbum)
                actualizarAdaptador()
                mostrarSnackbar("Álbum creado: $nombreAlbum")
                // Limpiar el campo de texto después de agregar el álbum
                inputNombreAlbum.text.clear()
            } else {
                mostrarSnackbar("Nombre del álbum no puede estar vacío")
            }
        }

        registerForContextMenu(listView)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        posicionItemSeleccionado = info.position
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.mi_editar -> {
                mostrarSnackbar("Editar $posicionItemSeleccionado")
                val albumId = arreglo[posicionItemSeleccionado].id
                irActividad(AeditarAlbumView::class.java, albumId)
                true
            }
            R.id.mi_eliminar -> {
                mostrarSnackbar("Eliminar $posicionItemSeleccionado")
                abrirDialogoEliminar()
                return true
            }
            R.id.mi_ver_canciones -> {
                val albumId = arreglo[posicionItemSeleccionado].id
                irActividad(BListCancion::class.java, albumId)
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    fun abrirDialogoEliminar() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Desea Eliminar")
        builder.setPositiveButton("Aceptar") { dialogInterface, i ->
            eliminarAlbum()
        }
        builder.setNegativeButton("Cancelar", null)
        val dialogo = builder.create()
        dialogo.show()
    }

    fun eliminarAlbum() {

        val album = arreglo[posicionItemSeleccionado]
        BBaseDatosMemoria.eliminarAlbumPorId(album.id)
        actualizarAdaptador()
        mostrarSnackbar("Álbum eliminado")
    }


    fun mostrarSnackbar(texto: String) {
        val snack = Snackbar.make(
            findViewById(R.id.cl_blist_view),
            texto,
            Snackbar.LENGTH_INDEFINITE
        )
        snack.show()
    }

    fun actualizarAdaptador() {
        val listView = findViewById<ListView>(R.id.lv_list_view)
        val adaptador = listView.adapter as ArrayAdapter<BAlbum>
        adaptador.notifyDataSetChanged()
    }

    fun irActividad(clase: Class<*>, albumId: Int, cancionId: Int? = null) {
        val intent = Intent(this, clase)
        intent.putExtra("album_id", albumId)
        cancionId?.let {
            intent.putExtra("cancion_id", it)
        }
        // Log para depuración
        Log.d("irActividad", "Iniciando actividad ${clase.simpleName} con albumId: $albumId, cancionId: $cancionId")
        startActivity(intent)
    }


}
