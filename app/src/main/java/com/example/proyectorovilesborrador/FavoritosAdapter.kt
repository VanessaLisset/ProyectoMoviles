package com.example.proyectorovilesborrador
// FavoritosAdapter.kt

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FavoritosAdapter(private val listaFavoritos: List<String>) :
    RecyclerView.Adapter<FavoritosAdapter.FavoritosViewHolder>() {

    class FavoritosViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvFavorito: TextView = itemView.findViewById(R.id.tvFavorito)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritosViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_favorito, parent, false)

        return FavoritosViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FavoritosViewHolder, position: Int) {
        holder.tvFavorito.text = listaFavoritos[position]
    }

    override fun getItemCount(): Int {
        return listaFavoritos.size
    }
}
