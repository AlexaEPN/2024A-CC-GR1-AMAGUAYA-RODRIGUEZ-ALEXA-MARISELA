package com.example.a2024accgr1amar

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar

class BListView : AppCompatActivity() {

    val arreglo = BBaseDatosMemoria.arregloBentrenador
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blist_view)
        val listView = findViewById<ListView>(R.id.lv_list_view)
        val adaptador = ArrayAdapter(
            this, //contexto
        android.R.layout.simple_list_item_1, //layout xm a usar
        arreglo
        )
        listView.adapter = adaptador
        adaptador.notifyDataSetChanged() //Refrescar la interfaz

        val botonAnadirListView = findViewById<Button>(
            R.id.btn_anadir_list_view
        )
        botonAnadirListView.setOnClickListener {
            anadirEntrenador(adaptador)
        }
        registerForContextMenu(listView)
    }
    //Aqui acaba el oneCreate
    var posicionItemSeleccionado = -1

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        //llenamos opciones del menu
        val inflater = menuInflater
        inflater.inflate(R.menu.menu,menu)
        //Obtener id
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val posicion = info.position
        posicionItemSeleccionado = posicion
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.mi_editar->{
                mostrarSnackbar("Editar $posicionItemSeleccionado")
                return true
            }
            R.id.mi_eliminar->{
                mostrarSnackbar("Eliminar $posicionItemSeleccionado")
                abrirDialogo()
                return true
            }

            else -> super.onContextItemSelected(item)
        }
    }

    fun abrirDialogo(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Desea Eliminar")
        builder.setPositiveButton(
            "Aceptar",
            DialogInterface.OnClickListener {
                    dialogInterface, i ->
                mostrarSnackbar("Eliminar Aceptado")
            }
        )
        builder.setNegativeButton("Cancelar", null)
        val opciones = resources.getStringArray(
            R.array.String_array_opciones
        )
        val seleccionPrevia = booleanArrayOf(
            true, false, false
        )
        builder.setMultiChoiceItems(
            opciones, seleccionPrevia
        ) { dialog, which, isChecked ->
            mostrarSnackbar("Dio click en el item $which")
        }
        val dialogo = builder.create()
        dialogo.show()
    }















    fun anadirEntrenador(adaptador: ArrayAdapter<Bentrenador>){
        arreglo.add(
            Bentrenador(4,"Wendy","d@d.com")
            )
        adaptador.notifyDataSetChanged()

    }
    fun mostrarSnackbar(texto:String){
        val snack = Snackbar.make(
            findViewById(R.id.cl_blist_view),
            texto,
            Snackbar.LENGTH_INDEFINITE
        )
        snack.show()
    }
}