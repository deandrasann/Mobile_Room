package com.example.author.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "fav_doa_table")
data class FavoriteDoa(
    @PrimaryKey
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
