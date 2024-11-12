package com.example.author

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.author.databinding.ActivityMainBinding
import com.example.author.model.Doa
import com.example.author.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var doaAdapter: DoaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        enableEdgeToEdge()

        recyclerView = findViewById(R.id.rvDoa)
        recyclerView.layoutManager = LinearLayoutManager(this)

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
                    doaAdapter = DoaAdapter(doaList)
                    recyclerView.adapter = doaAdapter
                }
            }

            override fun onFailure(call: Call<List<Doa>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Failed to load data", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
