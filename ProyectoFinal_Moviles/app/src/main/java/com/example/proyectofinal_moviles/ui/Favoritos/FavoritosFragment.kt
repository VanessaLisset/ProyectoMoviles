package com.example.proyectofinal_moviles.ui.Favoritos

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proyectofinal_moviles.Favorito
import com.example.proyectofinal_moviles.FavoritosAdapter
import com.example.proyectofinal_moviles.databinding.ActivityFavoritosBinding
import com.example.proyectofinal_moviles.ui.Favoritos.FavoritosViewModel

class FavoritosFragment : Fragment() {

    private var _binding: ActivityFavoritosBinding? = null
    private val binding get() = _binding!!
    private val listaFavoritos: MutableList<Favorito> = mutableListOf()
    private lateinit var favoritosAdapter: FavoritosAdapter
    private val favoritosViewModel: FavoritosViewModel by viewModels({ requireActivity() })


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val FavoritosViewModel =
            ViewModelProvider(this).get(FavoritosViewModel::class.java)
        _binding = ActivityFavoritosBinding.inflate(inflater, container, false)
        val root: View = binding.root

        favoritosAdapter = FavoritosAdapter(listaFavoritos) { position ->
            // Llamamos a la función eliminarFavorito del fragmento
            eliminarFavorito(position)
        }

        favoritosViewModel.listaFavoritos.observe(viewLifecycleOwner, { favoritos ->
            listaFavoritos.clear()
            listaFavoritos.addAll(favoritos)
            favoritosAdapter.notifyDataSetChanged()
        })


        // Imprime la lista de favoritos para verificar si contiene elementos
        Log.d("FavoritosFragment", "Lista de Favoritos: $listaFavoritos")

        binding.recyclerFavoritos.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = favoritosAdapter
        }

        return root
    }

    private fun eliminarFavorito(position: Int) {
        // Aquí debes eliminar el favorito de la lista de favoritos en tu ViewModel
        favoritosViewModel.eliminarFavorito(position)
    }

    private fun obtenerFavoritos(): List<Favorito> {
        // Lógica para obtener la lista de favoritos, si es necesario
        return listaFavoritos
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}