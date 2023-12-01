package com.example.proyectorovilesborrador

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity



class RegistroActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        sharedPreferences = getSharedPreferences("UsuariosRegistrados", Context.MODE_PRIVATE)

        val edtNombre = findViewById<EditText>(R.id.edtNombre)
        val edtCorreo = findViewById<EditText>(R.id.edtCorreo)
        val edtPassword = findViewById<EditText>(R.id.edtPassword)
        val edtTelefono = findViewById<EditText>(R.id.edtTelefono)
        val checkBox = findViewById<CheckBox>(R.id.checkBox)
        val btnRegistrar = findViewById<Button>(R.id.btnRegistrar)

        btnRegistrar.setOnClickListener {
            if (validarCampos() && checkBox.isChecked) {
                guardarUsuario(
                    edtNombre.text.toString(),
                    edtCorreo.text.toString(),
                    edtPassword.text.toString(),
                    edtTelefono.text.toString()
                )

                marcarUsuarioRegistrado()

                Toast.makeText(this, "¡Registro exitoso!", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(
                    this,
                    "Por favor, complete todos los campos correctamente y acepte los términos y condiciones.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun validarCampos(): Boolean {
        return true
    }

    private fun guardarUsuario(nombre: String, correo: String, password: String, telefono: String) {
        println("Usuario guardado: Nombre=$nombre, Correo=$correo, Teléfono=$telefono")
    }

    private fun marcarUsuarioRegistrado() {
        val editor = sharedPreferences.edit()
        editor.putBoolean("usuarioRegistrado", true)
        editor.apply()
    }
}

