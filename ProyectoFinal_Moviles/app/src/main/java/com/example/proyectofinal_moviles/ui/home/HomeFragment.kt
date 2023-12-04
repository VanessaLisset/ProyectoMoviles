package com.example.proyectofinal_moviles.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proyectofinal_moviles.ProductAdapter
import com.example.proyectofinal_moviles.R
import com.example.proyectofinal_moviles.databinding.FragmentHomeBinding
import com.example.proyectofinal_moviles.producto
import com.example.proyectofinal_moviles.ui.Detalle.Detalleragment

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

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
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = ProductAdapter(productos) { producto ->
                navegarADetalleFragment(producto)
            }
        }
        return root
    }

    private fun navegarADetalleFragment(producto: producto) {
        val detalleFragment = Detalleragment().apply {
            arguments = Bundle().apply {
                putString("claveProducto", producto.id.toString())
            }
        }

        val navController = findNavController()
        navController.navigate(R.id.detalleragment, detalleFragment.arguments)
    }

override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
