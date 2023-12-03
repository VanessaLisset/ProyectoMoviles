package com.example.proyectofinal_moviles

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProductAdapter(private val listaProductos: List<producto>) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val productImage: ImageView = view.findViewById(R.id.product_image)
        val productName: TextView = view.findViewById(R.id.product_name)
        val productBrand: TextView = view.findViewById(R.id.product_details)
        val productModel: TextView = view.findViewById(R.id.product_details2)
        val productPrice: TextView = view.findViewById(R.id.product_price)

        fun bind(producto: producto) {
            productImage.setImageResource(producto.imagen)
            productName.text = producto.nombre
            productBrand.text = producto.marca
            productModel.text = producto.modelo
            productPrice.text = producto.precio
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.producto_item, parent, false)
        return ProductViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val producto = listaProductos[position]
        holder.bind(producto)
    }

    override fun getItemCount() = listaProductos.size
}
