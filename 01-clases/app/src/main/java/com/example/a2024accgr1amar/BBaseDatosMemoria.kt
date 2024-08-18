package com.example.a2024accgr1amar

class BBaseDatosMemoria {
    companion object{
        val arregloBentrenador = arrayListOf<Bentrenador>()
        init {
            arregloBentrenador
                .add(
                    Bentrenador(1,"Adrian","a@a.com")
                )

            arregloBentrenador
                .add(
                    Bentrenador(2,"Vicente","b@b.com")
                )

            arregloBentrenador
                .add(
                    Bentrenador(3,"Carolina","c@c.com")
                )

        }
    }
}