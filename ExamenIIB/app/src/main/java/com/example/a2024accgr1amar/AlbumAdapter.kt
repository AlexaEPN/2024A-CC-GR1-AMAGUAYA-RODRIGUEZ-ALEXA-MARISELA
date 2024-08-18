package com.example.a2024accgr1amar

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AlbumAdapter(
    private var albums: List<BAlbum>,
    private val repository: Repository,
    private val onAlbumClick: (BAlbum) -> Unit
) : RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>() {

    inner class AlbumViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvAlbumName: TextView = view.findViewById(R.id.tvAlbumName)
        val btnEditAlbum: Button = view.findViewById(R.id.btnEditAlbum)
        val btnDeleteAlbum: Button = view.findViewById(R.id.btnDeleteAlbum)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_album, parent, false)
        return AlbumViewHolder(view)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        val album = albums[position]
        holder.tvAlbumName.text = album.nombreAlbum

        // Acción de editar
        holder.btnEditAlbum.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, AddAlbumActivity::class.java)
            intent.putExtra("ALBUM_ID", album.id)  // Pasar ID del álbum para editar
            context.startActivity(intent)
        }

            // Acción de editar
        holder.btnEditAlbum.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, AddAlbumActivity::class.java)
            intent.putExtra("ALBUM_ID", album.id)  // Pasar ID del álbum para editar
            context.startActivity(intent)
        }

        // Acción de eliminar
        holder.btnDeleteAlbum.setOnClickListener {
            repository.deleteAlbum(album.id)  // Elimina el álbum de la base de datos
            albums = repository.getAllAlbums()  // Actualiza la lista de álbumes
            notifyDataSetChanged()  // Refresca la vista
        }

        holder.itemView.setOnClickListener {
            onAlbumClick(album)
        }
    }

    override fun getItemCount(): Int {
        return albums.size
    }
}
