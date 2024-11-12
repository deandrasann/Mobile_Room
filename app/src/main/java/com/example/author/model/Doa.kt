package com.example.author.model

import com.google.gson.annotations.SerializedName

//3. Yg di quote berdasarkan field yg disana
data class Doa(
    @SerializedName("id")
    val id: Int,
    @SerializedName("doa")
    val doa: String,
    @SerializedName("ayat")
    val ayat: String,
    @SerializedName("latin")
    val latin: String,
    @SerializedName("artinya")
    val artinya: String
)
