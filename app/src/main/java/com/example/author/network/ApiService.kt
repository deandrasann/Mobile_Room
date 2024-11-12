package com.example.author.network

import com.example.author.model.Doa
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    //5. melengkapi url /api saat get author fungsi getAllAuthors dipanggil
    @GET("api")
    fun getAllDoa(): Call<List<Doa>>
}
