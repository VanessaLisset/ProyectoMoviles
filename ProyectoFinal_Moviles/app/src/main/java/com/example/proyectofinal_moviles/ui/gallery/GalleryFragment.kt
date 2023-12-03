package com.example.proyectofinal_moviles.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.proyectofinal_moviles.Registro
import com.example.proyectofinal_moviles.databinding.FragmentGalleryBinding

class GalleryFragment : Fragment() {

    private var _binding: FragmentGalleryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.btnModificar.setOnClickListener {
            modificarCorreo()
        }
        val ultimoUsuario = Registro.usuariosRegistrados.lastOrNull()
        ultimoUsuario?.let {

            binding.txtNombre.text = it.correo
        }

        return root
    }

    private fun modificarCorreo() {
        val nuevoCorreo = binding.edtNuevoCorreo.text.toString()
        val confirmarCorreo = binding.edtConfirmarCorreo.text.toString()

        if (nuevoCorreo.isEmpty() || confirmarCorreo.isEmpty()) {
            Toast.makeText(context, "Los campos no pueden estar vacíos", Toast.LENGTH_SHORT).show()
            return
        }

        if (nuevoCorreo != confirmarCorreo) {
            Toast.makeText(context, "Los correos no coinciden", Toast.LENGTH_SHORT).show()
            return
        }

        val correoActual = binding.txtNombre.text.toString()

        val usuarioActual = Registro.usuariosRegistrados.find { it.correo == correoActual }
        if (usuarioActual != null) {
            usuarioActual.correo = nuevoCorreo
            binding.txtNombre.text = nuevoCorreo
            binding.edtNuevoCorreo.text.clear()
            binding.edtConfirmarCorreo.text.clear()
            Toast.makeText(context, "Correo modificado con éxito", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Usuario no encontrado", Toast.LENGTH_SHORT).show()
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
