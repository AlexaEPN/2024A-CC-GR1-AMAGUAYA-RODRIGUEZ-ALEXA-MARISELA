import java.util.*
import kotlin.collections.ArrayList

fun main() {
    println("Hola Mundo")
    //INMUTABLE(No se reasigna "=")
    //val inmutable: String ="Alexa"
    //inmutable="Vicente"//Error
    //MUTABLE
    var mutable: String = "Alexa"
    mutable = "Vicente" //Ok
    println(mutable)
    //VAL>VAR
    val ejemploVariable:String = "Alexa Amaguaya"
    val edadEjemplo: Int =12
    //ejemploVariable = edadEjemplo
    ejemploVariable.trim()
    val nombreProfesor: String="Adrian Eguez"
    val sueldo: Double= 1.2
    val estadoCivil: Char=  'C'
    val mayorEdad:Boolean=  true
    //Clases Java

    val fechaNacimiento: Date= Date()

    //When (Switch)

    val estadoCivilWhen = "C"

    when (estadoCivilWhen){
        ("C")->{
            println("Casado")
        }
        ("S")->{
            println("Soltero")
        }
        else->{
            println("No sabemos")
        }


    }

    val esSoltero = (estadoCivilWhen=="S")
    val coqueteo = if(esSoltero) "Si" else "No" //if else chiquito

    calcularSueldo(10.00)
    calcularSueldo(10.00,15.00,20.00)
    calcularSueldo(10.00, bonoEspecial = 20.00)

    val sumaUno = Suma(1,1) //new Suma(1,1 en Kotlin no hay "new"
    val sumaDos = Suma(null,1)
    val sumaTres = Suma(1,null)
    val sumaCuatro = Suma(null,null)
    sumaUno.sumar()
    sumaDos.sumar()
    sumaTres.sumar()
    sumaCuatro.sumar()
    println(Suma.pi)
    //println(Suma.elevarAlCuadrado(2))
    println(Suma.historialSumas)

    //Arreglos
    //Estaticos
    val arregloEstatico: Array<Int> = arrayOf<Int>(1,2,3)
    println(arregloEstatico)
    //Dinamicos
    val arregloDinamico: ArrayList<Int> = arrayListOf<Int>(
        1,2,3,4,5,6,7,8,9,10
    )
    println("Arreglo Dinamico $arregloDinamico")
    arregloDinamico.add(11)
    arregloDinamico.add(12)
    println("Arreglo Dinamico: $arregloDinamico")

    // FOR EACH => UNIT
    val respuestaForEach:Unit=arregloDinamico
        .forEach {valorActual:Int->
        println("ValorActual $valorActual");
        }

    arregloDinamico.forEach{ println("Valor Actual (it): $it") }/*it se transforma en los que se encuentre en el arreglo*/

    /*Map -> muta o modifica el arreglo
    * 1) Enviamos el nuevo valor de la iteracion
    * 2) Nos devuelve un nuevo arreglo con valores de las iteraciones*/

    val respuestaMap: List<Double> = arregloDinamico
        .map { valorActual:Int->
            return@map valorActual.toDouble()+100.00
        }
    println(respuestaMap)
    val respuestaMapDos = arregloDinamico.map { it + 15 }
    println(respuestaMapDos)

    /*Filter ->Filtrar el arreglo
    1) Devolver una expresion (True o False)
    2) Nuevo arreglo Filtrado
    */
    val respuestaFilter:List<Int> = arregloDinamico
        .filter { valorActual:Int->
            //Expreson condicion
            val mayoresAAcinco: Boolean=valorActual>5
            return@filter mayoresAAcinco
        }
    val respuestaFilterDos=arregloDinamico.filter { it<=5 }
    println(respuestaFilter)
    println(respuestaFilterDos)

    /*OR AND
    * OR->ANY (Alguno Cumple)
    * OR->ALL (Todos Cumple)*/

    val respuestaAny: Boolean = arregloDinamico
        .any { valorActual:Int->
            return@any(valorActual>5)
        }
    println(respuestaAny) //True

    val respuestaAll: Boolean = arregloDinamico
        .all { valorActual:Int->
            return@all(valorActual>5)
        }
    println(respuestaAll) //False

    /*valor acumulado= 0 (Siempre empieza en 0 en kotlin)
    * [1,2,3,4,5] ->Acumulart "Sumar" estos valores del arreglo
    *valorIteracion1=valorEmpieza+1=0+1=1 ->Iteracion1
    *valorIteracion2=valorEmpieza+1=0+1=1 ->Iteracion1
    *valorIteracion3=valorEmpieza+1=0+1=1 ->Iteracion1
    *valorIteracion4=valorEmpieza+1=0+1=1 ->Iteracion1
    *valorIteracion5=valorEmpieza+1=0+1=1 ->Iteracion1
    */

    val respuestaReduce:Int = arregloDinamico
        .reduce{acumulado:Int, valorActual:Int->
    return@reduce (acumulado+valorActual)
        }//->Cambiar o usar la logica de negocio
    println(respuestaReduce)










}
/*##########################################Funciones##################################################################*/
fun imprimirNombre(nombre:String): Unit{
    println("Nombre: $nombre")
}


fun calcularSueldo(sueldo:Double,tasa:Double=12.00,bonoEspecial:Double?=null):Double{
    if(bonoEspecial==null){
        return sueldo *(100/tasa)
    }else
        return sueldo
}


abstract class NumerosJava{

    protected val numerosUno:Int
    private val numerosDos:Int

    constructor(
        uno:Int,
        dos:Int
    ){
        this.numerosUno=uno
        this.numerosDos=dos
        println("Inicializando")
    }

}

abstract class Numeros(//Constructor Primario
    //Caso 1) Parametros normal
    //Uno:Int,(paramtero (sin modificar))
    //Caso 2) parametro y propiedad (atributo) (private)
    //private var uno:Int (propiedad "instancia.uno")
    //Caso 3) Parametro y propiedad publica
    //Uno:Int,


    protected val numerosUno:Int,
    protected val numerosDos:Int){

    init {
        println("Inicializando")
    }
}

class Suma(
    unoParametro:Int,
    dosParametro:Int
):Numeros(unoParametro,dosParametro){
    public val soyPublicoExplicito:String="Explicito" //Publicas
    val soyPublicaImplicito:String="Implicito"
    init {
        this.numerosUno
        this.numerosDos
        numerosUno
        numerosDos
        this.soyPublicoExplicito
        soyPublicaImplicito
    }

    constructor(
        uno:Int?,
        dos: Int
    ):this(
        if (uno==null) 0 else uno, dos
    )

    constructor(
        uno:Int,
        dos: Int?
    ):this(
        uno,
        if (dos==null) 0 else dos,
    )


    constructor(
        uno:Int?,
        dos: Int?
    ):this(
        if (uno==null) 0 else uno,
        if (dos==null) 0 else dos,

        )



    public fun sumar():Int{
        val total = numerosUno + numerosDos
        agregarHistorial(total)
        return total
    }

    companion object{
        //Se definen funcione sy variables
        val pi = 3.14

        fun elevarCuadrado(num:Int):Int{
            return num * num
        }
        val historialSumas = arrayListOf<Int>()
        fun agregarHistorial(valorTotalSuma:Int){
            historialSumas.add(valorTotalSuma)
        }
    }
}
