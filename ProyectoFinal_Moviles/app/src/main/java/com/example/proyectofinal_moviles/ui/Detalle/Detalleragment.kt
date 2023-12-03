package com.example.proyectofinal_moviles.ui.Detalle

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.proyectofinal_moviles.databinding.ActivityDetalleBinding

class Detalleragment : Fragment() {

    private var _binding: ActivityDetalleBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val slideshowViewModel =
            ViewModelProvider(this).get(DetalleViewModel::class.java)

        _binding = ActivityDetalleBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.txtDeta
        slideshowViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}