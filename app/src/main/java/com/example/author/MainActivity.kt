// MainActivity.kt
package com.example.author

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.author.database.FavoriteDoaDatabase
import com.example.author.model.Doa
import com.example.author.model.FavoriteDoa
import com.example.author.network.ApiClient
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var doaAdapter: DoaAdapter
    private lateinit var favoriteDoaDatabase: FavoriteDoaDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        favoriteDoaDatabase = FavoriteDoaDatabase.getDatabase(this)

        recyclerView = findViewById(R.id.rvDoa)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val btnViewFavorites = findViewById<Button>(R.id.btn_view_favorites)
        btnViewFavorites.setOnClickListener {
            val intent = Intent(this, FavoriteActivity::class.java)
            startActivity(intent)
        }

        fetchDoaList()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun fetchDoaList() {
        val apiService = ApiClient.getInstance()
        apiService.getAllDoa().enqueue(object : Callback<List<Doa>> {
            override fun onResponse(call: Call<List<Doa>>, response: Response<List<Doa>>) {
                if (response.isSuccessful) {
                    val doaList = response.body()!!
                    doaAdapter = DoaAdapter(doaList) { favoriteDoa ->
                        addToFavorites(favoriteDoa)
                    }
                    recyclerView.adapter = doaAdapter
                }
            }

            override fun onFailure(call: Call<List<Doa>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Failed to load data", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun addToFavorites(favoriteDoa: FavoriteDoa) {
        lifecycleScope.launch {
            favoriteDoaDatabase.favoriteDoaDao().insert(favoriteDoa)
            Toast.makeText(this@MainActivity, "Added to favorites", Toast.LENGTH_SHORT).show()
        }
    }
}
