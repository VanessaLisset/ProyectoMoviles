package com.example.proyectofinal_moviles.ui.Favoritos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.proyectofinal_moviles.databinding.ActivityFavoritosBinding
import com.example.proyectofinal_moviles.ui.Favoritos.FavoritosViewModel

class FavoritosFragment : Fragment() {

    private var _binding: ActivityFavoritosBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val FavoritosViewModel =
            ViewModelProvider(this).get(FavoritosViewModel::class.java)

        _binding = ActivityFavoritosBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textFavs
        FavoritosViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}