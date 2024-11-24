// FavoriteDoaActivity.kt
package com.example.author

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.author.database.FavoriteDoaDatabase
import com.example.author.model.FavoriteDoa
import kotlinx.coroutines.launch

class FavoriteActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var favoriteDoaAdapter: FavoriteDoaAdapter
    private lateinit var favoriteDoaDatabase: FavoriteDoaDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)

        recyclerView = findViewById(R.id.rvFavoriteDoa)
        recyclerView.layoutManager = LinearLayoutManager(this)

        favoriteDoaDatabase = FavoriteDoaDatabase.getDatabase(this)

        loadFavoriteDoas()
    }

    private fun loadFavoriteDoas() {
        favoriteDoaDatabase.favoriteDoaDao().getAllFavoriteDoas().observe(this, Observer { favoriteDoas ->
            favoriteDoaAdapter = FavoriteDoaAdapter(favoriteDoas)
            recyclerView.adapter = favoriteDoaAdapter
        })
    }
}
