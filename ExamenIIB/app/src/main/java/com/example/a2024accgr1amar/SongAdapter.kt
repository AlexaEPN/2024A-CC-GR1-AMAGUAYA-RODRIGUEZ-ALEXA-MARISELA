package com.example.a2024accgr1amar

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SongAdapter(private var songs: List<BCancion>, private val repository: Repository, private val albumId: Int) : RecyclerView.Adapter<SongAdapter.SongViewHolder>() {

    inner class SongViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvSongName: TextView = view.findViewById(R.id.tvSongName)
        val tvSongDuration: TextView = view.findViewById(R.id.tvSongDuration)
        val btnEditSong: Button = view.findViewById(R.id.btnEditSong)
        val btnDeleteSong: Button = view.findViewById(R.id.btnDeleteSong)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_song, parent, false)
        return SongViewHolder(view)
    }

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        val song = songs[position]
        holder.tvSongName.text = song.nombreCancion
        holder.tvSongDuration.text = song.duracionCancion

        // Acción de editar
        holder.btnEditSong.setOnClickListener {
            val intent = Intent(holder.itemView.context, AddSongActivity::class.java)
            intent.putExtra("ALBUM_ID", albumId)
            intent.putExtra("SONG_ID", song.idCancion)  // Pasar ID de la canción para editar
            holder.itemView.context.startActivity(intent)
        }

        // Acción de eliminar
        holder.btnDeleteSong.setOnClickListener {
            repository.deleteCancion(song.idCancion)  // Elimina la canción de la base de datos
            songs = repository.getCancionesForAlbum(albumId)  // Actualiza la lista
            notifyDataSetChanged()  // Refresca la vista
        }
    }

    override fun getItemCount(): Int {
        return songs.size
    }
}
