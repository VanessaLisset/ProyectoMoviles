package com.example.proyectofinal_moviles.ui.Detalle

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        arguments?.let { bundle ->
            val productoNombre = bundle.getString("productoNombre", "")
            val productoMarca = bundle.getString("productoMarca", "")

            binding.txtproductName.text = productoNombre
            binding.txtproductDetails.text = productoMarca
        }

        return root
    }


    private fun navegarADetalleFragment(producto: producto) {
        val bundle = Bundle().apply {
            putInt("productoId", producto.id)
            putString("productoNombre", producto.nombre)
            putString("productoMarca", producto.marca)
        }

        findNavController().navigate(R.id.detalleragment, bundle)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}