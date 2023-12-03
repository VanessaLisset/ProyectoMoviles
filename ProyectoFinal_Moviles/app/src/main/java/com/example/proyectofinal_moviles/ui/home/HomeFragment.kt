package com.example.proyectofinal_moviles.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proyectofinal_moviles.ProductAdapter
import com.example.proyectofinal_moviles.R
import com.example.proyectofinal_moviles.databinding.FragmentHomeBinding
import com.example.proyectofinal_moviles.producto // Asegúrate de que esta sea la ruta correcta de tu clase Producto

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Configura el RecyclerView
        val productos = listOf(
            producto(R.drawable.prod1, "Compactadora", "Marca X", "Modelo Y", "$550"),
            producto(R.drawable.prod1, "Compactadora", "Marca X", "Modelo Y", "$550"),
            producto(R.drawable.prod1, "Compactadora", "Marca X", "Modelo Y", "$550"),
            producto(R.drawable.prod1, "Compactadora", "Marca X", "Modelo Y", "$550"),
            producto(R.drawable.prod1, "Compactadora", "Marca X", "Modelo Y", "$550"),
            producto(R.drawable.prod1, "Compactadora", "Marca X", "Modelo Y", "$550"),
            producto(R.drawable.prod1, "Compactadora", "Marca X", "Modelo Y", "$550"),
            producto(R.drawable.prod1, "Compactadora", "Marca X", "Modelo Y", "$550"),
            producto(R.drawable.prod1, "Compactadora", "Marca X", "Modelo Y", "$550"),

        )

        // Asegúrate de que el ID del RecyclerView en tu FragmentHomeBinding es recyclerView
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = ProductAdapter(productos)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
