// FavoriteDoaAdapter.kt
package com.example.author

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.author.databinding.ItemDoaBinding
import com.example.author.databinding.ItemFavoriteBinding
import com.example.author.model.FavoriteDoa

class FavoriteDoaAdapter(private val favoriteDoaList: List<FavoriteDoa>) : RecyclerView.Adapter<FavoriteDoaAdapter.FavoriteDoaViewHolder>() {

    class FavoriteDoaViewHolder(val binding: ItemFavoriteBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteDoaViewHolder {
        val binding = ItemFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteDoaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteDoaViewHolder, position: Int) {
        val favoriteDoa = favoriteDoaList[position]
        holder.binding.doaFavorit.text = favoriteDoa.doa
        // Set other views if necessary
    }

    override fun getItemCount(): Int {
        return favoriteDoaList.size
    }
}
