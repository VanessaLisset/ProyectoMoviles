package com.example.proyectofinal_moviles.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proyectofinal_moviles.Favorito
import com.example.proyectofinal_moviles.FavoritosAdapter
import com.example.proyectofinal_moviles.ProductAdapter
import com.example.proyectofinal_moviles.R
import com.example.proyectofinal_moviles.databinding.FragmentHomeBinding
import com.example.proyectofinal_moviles.producto
import com.example.proyectofinal_moviles.ui.Detalle.Detalleragment
import com.example.proyectofinal_moviles.ui.Favoritos.FavoritosViewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val listaFavoritos: MutableList<Favorito> = mutableListOf()
    private lateinit var favoritosAdapter: FavoritosAdapter
    private val favoritosViewModel: FavoritosViewModel by viewModels({ requireActivity() })

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        favoritosAdapter = FavoritosAdapter(listaFavoritos) { position ->
            eliminarFavorito(position)
        }

        val productos = listOf(
            producto(R.drawable.prod1, "Compactadora", "Marca X", "Modelo Y", "$550", 1,""),
            producto(R.drawable.prod2, "Excavadora", "Marca:Cat Construction", "Modelo Y23", "$550", 2,""),
            producto(R.drawable.prod3, "PS L", "Marca: terrenaitor", "Modelo: 2EYplus", "3550", 3,"Esta serie PS L, ha sido especialmente diseñada, para que los operadores hombre caminando, utilicen un equipo seguro y confortable en su trabajo. Gracias a su suave desplazamiento de ascenso y descenso, las operaciones de estiba, se vuelven más seguras y rápidas. "),
            producto(R.drawable.prod1, "Compactadora", "Marca X", "Modelo Y", "$550", 4,""),
            producto(R.drawable.prod1, "Compactadora", "Marca X", "Modelo Y", "$550", 5,""),
            producto(R.drawable.prod1, "Compactadora", "Marca X", "Modelo Y", "$550", 6,""),
            producto(R.drawable.prod1, "Compactadora", "Marca X", "Modelo Y", "$550", 7,""),
            producto(R.drawable.prod1, "Compactadora", "Marca X", "Modelo Y", "$550", 8,""),
            producto(R.drawable.prod1, "Compactadora", "Marca X", "Modelo Y", "$550", 9,""),
        )
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = ProductAdapter(
                productos,
                { producto -> navegarADetalleFragment(producto) }, // función onClick
                { producto -> onFavoritoClick(producto) } // función onFavoritoClick
            )
        }
        return root
    }
    private fun onFavoritoClick(producto: producto) {
        val favorito = Favorito(
            producto.id,
            producto.nombre,
            producto.marca,
            producto.modelo,
            producto.precio,
            producto.imagen
        )
        favoritosViewModel.agregarFavorito(favorito)


        // Opcional: Puedes mostrar un mensaje o realizar otras acciones relacionadas con los favoritos.
        val mensaje = "Añadido a favoritos: ${producto.nombre}"
        Toast.makeText(requireContext(), mensaje, Toast.LENGTH_SHORT).show()
    }

    private fun eliminarFavorito(position: Int) {
        // Aquí debes eliminar el favorito de la lista de favoritos en tu ViewModel
        favoritosViewModel.eliminarFavorito(position)
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
