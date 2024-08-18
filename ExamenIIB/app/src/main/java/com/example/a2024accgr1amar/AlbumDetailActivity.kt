package com.example.a2024accgr1amar

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import android.widget.TextView

class AlbumDetailActivity : AppCompatActivity() {

    private lateinit var repository: Repository
    private lateinit var recyclerView: RecyclerView
    private lateinit var songAdapter: SongAdapter
    private var albumId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album_detail)

        repository = Repository(this)
        albumId = intent.getIntExtra("ALBUM_ID", 0)

        val album = repository.getAllAlbums().find { it.id == albumId }

        val tvAlbumDetailName: TextView = findViewById(R.id.tvAlbumDetailName)
        val tvAlbumDetailComposer: TextView = findViewById(R.id.tvAlbumDetailComposer)
        val tvAlbumDetailDescription: TextView = findViewById(R.id.tvAlbumDetailDescription)

        tvAlbumDetailName.text = album?.nombreAlbum
        tvAlbumDetailComposer.text = album?.nombreCompositor
        tvAlbumDetailDescription.text = album?.descripcionAlbum

        recyclerView = findViewById(R.id.recyclerViewSongs)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val fabAddSong: Button = findViewById(R.id.fabAddSong)
        fabAddSong.setOnClickListener {
            val intent = Intent(this, AddSongActivity::class.java)
            intent.putExtra("ALBUM_ID", albumId)
            startActivity(intent)
        }

        loadSongs()
    }

    private fun loadSongs() {
        val songs = repository.getCancionesForAlbum(albumId)
        songAdapter = SongAdapter(songs, repository, albumId) // Asegúrate de pasar el repository y albumId
        recyclerView.adapter = songAdapter
    }

     override fun onResume() {
    super.onResume()
    loadSongs() }
}
