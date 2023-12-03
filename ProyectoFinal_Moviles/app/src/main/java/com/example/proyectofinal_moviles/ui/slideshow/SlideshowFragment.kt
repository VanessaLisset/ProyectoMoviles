package com.example.proyectofinal_moviles.ui.slideshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.proyectofinal_moviles.Registro
import com.example.proyectofinal_moviles.databinding.FragmentSlideshowBinding

class SlideshowFragment : Fragment() {

    private var _binding: FragmentSlideshowBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val slideshowViewModel =
            ViewModelProvider(this).get(SlideshowViewModel::class.java)

        _binding = FragmentSlideshowBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val ultimoUsuario = Registro.usuariosRegistrados.lastOrNull()
        ultimoUsuario?.let {
            binding.txtMostrarNombre.text = it.usuario
            binding.txtMostrarCorreo.text = it.correo
            binding.txtMostrarTelefono.text = it.telefono
            binding.txtMostrarContrasena.text = it.password
        }
        binding.btnSaludarPerfil.setOnClickListener {
            Toast.makeText(context, "Saludos", Toast.LENGTH_SHORT).show()
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}