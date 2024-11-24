package com.example.author

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.author.model.FavoriteDoa
import androidx.room.Query
@Dao
interface FavoriteDoaDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(favoriteDoa: FavoriteDoa)

    @Delete
    suspend fun delete(favoriteDoa: FavoriteDoa)


    @Query("SELECT * FROM fav_doa_table ORDER BY id ASC")
    fun getAllFavoriteDoas(): LiveData<List<FavoriteDoa>>
}