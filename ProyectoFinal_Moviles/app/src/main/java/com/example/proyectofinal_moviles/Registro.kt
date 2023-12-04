package com.example.proyectofinal_moviles

import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class Registro : AppCompatActivity() {
    companion object {
        val usuariosRegistrados = mutableListOf<Usuario>()
        var ultimoId = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = Color.TRANSPARENT
        }

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        supportActionBar?.hide()
        setContentView(R.layout.activity_registro)

        val textViewTerms = findViewById<TextView>(R.id.txtTermino)

        textViewTerms.setOnClickListener {
            val intent = Intent(this, terminos::class.java)
            startActivity(intent)
        }

        val btnRegistrar = findViewById<Button>(R.id.btnRegistrar)
        btnRegistrar.setOnClickListener {
            registrarUsuario()
        }
    }

    private fun registrarUsuario() {
        val checkBoxTerminos = findViewById<CheckBox>(R.id.checkTerminos)
        val usuario = findViewById<EditText>(R.id.edtUs).text.toString()
        val correo = findViewById<EditText>(R.id.edtCorreo).text.toString()
        val telefono = findViewById<EditText>(R.id.edtTelefono).text.toString()
        val password = findViewById<EditText>(R.id.edtPassword).text.toString()
        var mensajeError = ""

        if (usuario.isEmpty()) mensajeError += "\nUsuario"
        if (correo.isEmpty()) mensajeError += "\nCorreo"
        if (telefono.isEmpty()) mensajeError += "\nTeléfono"
        if (password.isEmpty()) mensajeError += "\nContraseña"
        if (!checkBoxTerminos.isChecked) mensajeError += "\nDebe aceptar los términos y condiciones"

        if (mensajeError.isNotEmpty()) {
            Toast.makeText(this, "Te falta ingresar:$mensajeError", Toast.LENGTH_LONG).show()
            return
        }
        val nuevoId = usuariosRegistrados.size + 1
        val nuevoUsuario = Usuario(nuevoId, usuario, correo, telefono, password)

        if (usuariosRegistrados.size < 30) {
            usuariosRegistrados.add(nuevoUsuario)
            Toast.makeText(this, "Usuario registrado con ID $nuevoId", Toast.LENGTH_SHORT).show()
            finish()
        } else {
            Toast.makeText(this, "Límite de usuarios alcanzado", Toast.LENGTH_SHORT).show()
        }
    }
}

