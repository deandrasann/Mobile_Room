package com.example.author

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.author.databinding.ItemDoaBinding
import com.example.author.model.Doa

class DoaAdapter(private val doaList: List<Doa>) : RecyclerView.Adapter<DoaAdapter.DoaViewHolder>() {

    class DoaViewHolder(val binding: ItemDoaBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoaViewHolder {
        val binding = ItemDoaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DoaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DoaViewHolder, position: Int) {
        val doa = doaList[position]
        holder.binding.doaTextView.text = doa.doa
        // Set other views if necessary
    }

    override fun getItemCount(): Int {
        return doaList.size
    }
}
