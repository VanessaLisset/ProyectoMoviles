package com.example.proyectofinal_moviles.ui.Miscompras

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectofinal_moviles.R
import com.example.proyectofinal_moviles.producto

class CartAdapter(private val items: MutableList<producto>) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    fun updateCart(newItems: List<producto>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    class CartViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val productName: TextView = view.findViewById(R.id.tvCartProductName)
        val pricePerDay: TextView = view.findViewById(R.id.tvCartProductPricePerDay)
        val daysToRent: TextView = view.findViewById(R.id.tvCartDaysToRent)
        val totalPrice: TextView = view.findViewById(R.id.tvCartTotalPrice)
        val productImage: ImageView = view.findViewById(R.id.ivCartProduct)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cart, parent, false)
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val item = items[position]
        holder.productName.text = item.nombre
        holder.pricePerDay.text = "Precio al Día: ${item.precio}"
        holder.daysToRent.text = "Días a rentar: ${item.diasARentar}"

        val precioPorDia = item.precio.replace("$", "").trim().toDoubleOrNull() ?: 0.0
        val total = precioPorDia * item.diasARentar
        item.precioTotal = "$${total}" // Actualiza el precio total en el objeto producto.
        holder.totalPrice.text = "Total: ${item.precioTotal}"
        holder.productImage.setImageResource(item.imagen)
    }


    override fun getItemCount(): Int = items.size
}
