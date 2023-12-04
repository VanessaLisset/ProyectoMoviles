package com.example.proyectofinal_moviles.ui.Detalle

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.proyectofinal_moviles.R
import com.example.proyectofinal_moviles.databinding.ActivityDetalleBinding
import com.example.proyectofinal_moviles.producto

class Detalleragment : Fragment() {
    private var _binding: ActivityDetalleBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        _binding = ActivityDetalleBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Recibir el ID del producto
        val productoIds = arguments?.getString("claveProducto")
        var productoId = 0;
        try {
            productoId = productoIds!!.toInt()
        } catch (ex: NumberFormatException) {
            println("The given string is non-numeric")
        }

        // Aquí simplemente estamos estableciendo textos de ejemplo
        val productoEncontrado = obtenerProducto(productoId)

        binding.txtproductName.text = productoEncontrado?.nombre
        binding.txtproductDetails.text = productoEncontrado?.modelo

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun obtenerProducto(id_producto: Int): producto? {
        val productos = listOf(
            producto(R.drawable.prod1, "Compactadora", "Marca X", "Modelo Y", "$550", 1),
            producto(R.drawable.prod1, "Compactadora", "Marca X", "Modelo Y", "$550", 2),
            producto(R.drawable.prod1, "Compactadora", "Marca X", "Modelo Y", "$550", 3),
            producto(R.drawable.prod1, "Compactadora", "Marca X", "Modelo Y", "$550", 4),
            producto(R.drawable.prod1, "Compactadora", "Marca X", "Modelo Y", "$550", 5),
            producto(R.drawable.prod1, "Compactadora", "Marca X", "Modelo Y", "$550", 6),
            producto(R.drawable.prod1, "Compactadora", "Marca X", "Modelo Y", "$550", 7),
            producto(R.drawable.prod1, "Compactadora", "Marca X", "Modelo Y", "$550", 8),
            producto(R.drawable.prod1, "Compactadora", "Marca X", "Modelo Y", "$550", 9),
        )
        return productos.find { it.id == id_producto }
    }

}