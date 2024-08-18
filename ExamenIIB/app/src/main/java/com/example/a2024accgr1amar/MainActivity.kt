package com.example.a2024accgr1amar

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var repository: Repository
    private lateinit var recyclerView: RecyclerView
    private lateinit var albumAdapter: AlbumAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        repository = Repository(this)

        recyclerView = findViewById(R.id.recyclerViewAlbums)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val btnAddAlbum: Button = findViewById(R.id.fabAddAlbum)
        btnAddAlbum.setOnClickListener {
            val intent = Intent(this, AddAlbumActivity::class.java)
            startActivity(intent)
        }

        loadAlbums()
    }

    private fun loadAlbums() {
        val albums = repository.getAllAlbums()
        albumAdapter = AlbumAdapter(albums, repository) { album ->
            val intent = Intent(this, AlbumDetailActivity::class.java)
            intent.putExtra("ALBUM_ID", album.id)
            startActivity(intent)
        }
        recyclerView.adapter = albumAdapter
    }

    override fun onResume() {
        super.onResume()
        loadAlbums()
    }
}
