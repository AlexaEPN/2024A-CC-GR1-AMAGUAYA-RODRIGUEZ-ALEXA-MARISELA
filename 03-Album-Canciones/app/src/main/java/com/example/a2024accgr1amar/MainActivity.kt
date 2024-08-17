package com.example.a2024accgr1amar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    fun mostrarSnackBar(texto:String){
        val snack  = Snackbar.make(
            findViewById(R.id.layout_album),
            texto,
            Snackbar.LENGTH_INDEFINITE
        )
        snack.show()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val botonListView = findViewById<Button>(R.id.btn_ir_view_list)
        botonListView
            .setOnClickListener {
                irActividad(BListAlbum::class.java)
            }

    }


    fun irActividad(clase: Class<*>){
        val intent = Intent(this, clase)
        startActivity(intent)
    }





}