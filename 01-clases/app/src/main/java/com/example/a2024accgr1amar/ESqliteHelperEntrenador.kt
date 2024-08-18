package com.example.a2024accgr1amar
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ESqliteHelperEntrenador(
    contexto: Context? //this
):SQLiteOpenHelper(
    contexto,
    "moviles",
    null,
    1
) {
    override fun onCreate(db: SQLiteDatabase?) {
        val scriptSQLCrearTablaEntrenador =
            """
                CREATE TABLE ENTRENADOR(
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nombre VARCHAR(50),
                descripcion VARCHAR(50)
                )
            """.trimIndent()
        db?.execSQL(scriptSQLCrearTablaEntrenador)
    }

    override fun onUpgrade(
        p0: SQLiteDatabase?, p1:Int, p2:Int) {}

        fun crearEntrenador(
            nombre:String,
            descripcion: String
        ):Boolean{
            val baseDatosEscritura = writableDatabase
            val valoresAGuardar = ContentValues()
            valoresAGuardar.put("nombre", nombre)
            valoresAGuardar.put("descripcion", descripcion)
            val resultadoAGuardar = baseDatosEscritura
                .insert(
                    "ENTRENADOR",//nombre de la table
                    null,
                    valoresAGuardar //valores
                )
            baseDatosEscritura.close()
            return if(resultadoAGuardar.toInt()==-1) false else true
        }

        fun eliminarEntrenadorFormulario(id:Int):Boolean{
            val conexionEscritura = writableDatabase
            //Consulta SQL: where ... ID=? AND NOMBRE=? [?=1, ?=2]
            val parametrosConsultaDelete = arrayOf(id.toString())
            val resultadoEliminacion = conexionEscritura
                .delete(
                    "ENTRENADOR",
                    "id=?",
                    parametrosConsultaDelete
                )
            conexionEscritura.close()
            return if(resultadoEliminacion.toInt()==-1) false else true
        }

        fun actualizarEntrenadorFormulario(
            nombre:String, descripcion: String, id: Int
        ):Boolean{
            val conexionEscritura = writableDatabase
            val valoresAActualizar = ContentValues()
            valoresAActualizar.put("nombre", nombre)
            valoresAActualizar.put("descripcion", descripcion)
            //Where: ....
            val parametrosConsultaActualizar = arrayOf(id.toString())
            val resultadoActualizacion = conexionEscritura
                .update(
                    "ENTRENADOR",
                    valoresAActualizar, //nombre=Adrian, descripcion=B
                "id=?",
                    parametrosConsultaActualizar // [1]
                )
            conexionEscritura.close()
            return  if(resultadoActualizacion.toInt()==-1) false else true
        }

        fun consultarEntrenadorPorID(id:Int):Bentrenador?{
            val baseDatosLectura = readableDatabase
            val scriptConsultaLectura = """
                SELECT * FROM ENTRENADOR WHERE ID = ?
            """.trimIndent()
            val arregloParametrosConsultaLectura = arrayOf(
                id.toString()
            )
            val resultadoConsultaLectura = baseDatosLectura
                .rawQuery(
                    scriptConsultaLectura,
                    arregloParametrosConsultaLectura
                )
            val existeAlMenosUno = resultadoConsultaLectura
                .moveToFirst()
            val arregloRespuesta = arrayListOf<Bentrenador>()
            if(existeAlMenosUno){
                do{
                    val entrenador = Bentrenador(
                        resultadoConsultaLectura.getInt(0),
                        resultadoConsultaLectura.getString(1),
                        resultadoConsultaLectura.getString(2)
                    )
                    arregloRespuesta.add(entrenador)
                }while (resultadoConsultaLectura.moveToNext())
            }
            resultadoConsultaLectura.close()
            baseDatosLectura.close()
            return if(arregloRespuesta.size > 0) arregloRespuesta[0] else null
        }


    }
