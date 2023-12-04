package com.example.proyectofinal_moviles.ui.slideshow

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import android.Manifest
import android.net.Uri
import com.example.proyectofinal_moviles.Registro

import com.example.proyectofinal_moviles.databinding.FragmentSlideshowBinding

class SlideshowFragment : Fragment() {

    private var _binding: FragmentSlideshowBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val REQUEST_CODE_READ_EXTERNAL_STORAGE = 1
    private val REQUEST_CODE_PICK_IMAGE = 2

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val slideshowViewModel = ViewModelProvider(this).get(SlideshowViewModel::class.java)
        _binding = FragmentSlideshowBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val userId = getCurrentUserId()
        loadImageForUser(userId)

        binding.btnAvatar.setOnClickListener {
            checkPermissionAndPickImage()
        }


        val ultimoUsuario = Registro.usuariosRegistrados.lastOrNull()
        ultimoUsuario?.let {
            binding.txtMostrarID.text = it.id.toString()
            binding.txtMostrarNombre.text = it.usuario
            binding.txtMostrarCorreo.text = it.correo
            binding.txtMostrarTelefono.text = it.telefono
            binding.txtMostrarContrasena.text = it.password
        }
        binding.btnSaludarPerfil.setOnClickListener {
            Toast.makeText(context, "Saludos", Toast.LENGTH_SHORT).show()
        }
        binding.btnAvatar.setOnClickListener {
            checkPermissionAndPickImage()
        }

        return root
    }

    private fun getCurrentUserId(): Int {
        return 1
    }

    private fun loadImageForUser(userId: Int) {
        val sharedPreferences = requireActivity().getSharedPreferences("user_prefs", Activity.MODE_PRIVATE)
        val imageUriString = sharedPreferences.getString("user_image_uri_$userId", null)
        imageUriString?.let {
            val imageUri = Uri.parse(it)
            binding.btnAvatar.setImageURI(imageUri)
        }
    }

    private fun checkPermissionAndPickImage() {
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            pickImageFromGallery()
        } else {
            requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), REQUEST_CODE_READ_EXTERNAL_STORAGE)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            REQUEST_CODE_READ_EXTERNAL_STORAGE -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    pickImageFromGallery()
                } else {
                    Toast.makeText(context, "Permission denied", Toast.LENGTH_SHORT).show()
                }
            }
            else -> super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }

    private fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_CODE_PICK_IMAGE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_PICK_IMAGE && resultCode == Activity.RESULT_OK) {
            val imageUri: Uri? = data?.data
            val userId = getCurrentUserId()

            // Almacenar la URI de la imagen en SharedPreferences
            val sharedPreferences = requireActivity().getSharedPreferences("user_prefs", Activity.MODE_PRIVATE)
            with(sharedPreferences.edit()) {
                putString("user_image_uri_$userId", imageUri.toString())
                apply()
            }

            // Actualizar la vista del ImageButton con la nueva imagen
            binding.btnAvatar.setImageURI(imageUri)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}