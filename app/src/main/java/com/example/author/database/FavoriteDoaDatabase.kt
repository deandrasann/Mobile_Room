package com.example.author.database

import android.content.Context
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.author.FavoriteDoaDao
import com.example.author.model.FavoriteDoa


@Database(entities = [FavoriteDoa::class], version = 1, exportSchema = false)
abstract class FavoriteDoaDatabase : RoomDatabase() {
    abstract fun favoriteDoaDao(): FavoriteDoaDao

    companion object {
        @Volatile
        private var INSTANCE: FavoriteDoaDatabase? = null

        fun getDatabase(context: Context): FavoriteDoaDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FavoriteDoaDatabase::class.java,
                    "fav_doa_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
