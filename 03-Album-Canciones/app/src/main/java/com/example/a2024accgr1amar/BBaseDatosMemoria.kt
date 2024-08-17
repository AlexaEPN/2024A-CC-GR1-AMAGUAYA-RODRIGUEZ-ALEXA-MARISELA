package com.example.a2024accgr1amar

class BBaseDatosMemoria {
    companion object {
        val arregloBAlbum = arrayListOf<BAlbum>()

        init {
            arregloBAlbum.add(
                BAlbum(1, "Lucid Dreams", "Boy With Uke", "14 canciones").apply {
                    canciones = arrayListOf(
                        BCancion(1, "Two Moons", "3:45", 1000f, 500f),
                        BCancion(2, "Blue", "4:20", 2000f, 1500f)
                    )
                }
            )
            arregloBAlbum.add(
                BAlbum(2, "Ultratumba", "Anthres", "10 canciones").apply {
                    canciones = arrayListOf(
                        BCancion(3, "Corazon Opiode", "2:50", 3000f, 2500f),
                        BCancion(4, "Hogar", "3:30", 4000f, 3500f)
                    )
                }
            )
            arregloBAlbum.add(
                BAlbum(3, "Flora", "Nasa Histoires", "5 canciones").apply {
                    canciones = arrayListOf(
                        BCancion(5, "Cancion 5", "4:10", 5000f, 4500f),
                        BCancion(6, "Cancion 6", "3:25", 6000f, 5500f)
                    )
                }
            )
        }

        // Métodos CRUD para Álbumes
        fun agregarAlbum(album: BAlbum): Boolean {
            return arregloBAlbum.add(album)
        }

        fun obtenerAlbumPorId(id: Int): BAlbum? {
            return arregloBAlbum.find { it.id == id }
        }

        fun obtenerTodosLosAlbumes(): List<BAlbum> {
            return arregloBAlbum
        }

        fun actualizarAlbum(albumActualizado: BAlbum): Boolean {
            val index = arregloBAlbum.indexOfFirst { it.id == albumActualizado.id }
            return if (index != -1) {
                arregloBAlbum[index] = albumActualizado
                true
            } else {
                false
            }
        }

        fun eliminarAlbumPorId(id: Int): Boolean {
            val album = obtenerAlbumPorId(id)
            return if (album != null) {
                arregloBAlbum.remove(album)
                true
            } else {
                false
            }
        }

        // Métodos CRUD para Canciones dentro de un Álbum
        fun agregarCancionAAlbum(albumId: Int, cancion: BCancion): Boolean {
            val album = obtenerAlbumPorId(albumId)
            return album?.canciones?.add(cancion) ?: false
        }

        fun obtenerCancionPorId(albumId: Int, cancionId: Int): BCancion? {
            val album = obtenerAlbumPorId(albumId)
            return album?.canciones?.find { it.idCancion == cancionId }
        }

        fun actualizarCancion(albumId: Int, cancionActualizada: BCancion): Boolean {
            val album = obtenerAlbumPorId(albumId)
            val index = album?.canciones?.indexOfFirst { it.idCancion == cancionActualizada.idCancion }
            return if (index != null && index != -1) {
                album.canciones[index] = cancionActualizada
                true
            } else {
                false
            }
        }

        fun eliminarCancionDeAlbum(albumId: Int, cancionId: Int): Boolean {
            val album = obtenerAlbumPorId(albumId)
            val cancion = album?.canciones?.find { it.idCancion == cancionId }
            return if (cancion != null) {
                album.canciones.remove(cancion)
                true
            } else {
                false
            }
        }
    }
}
