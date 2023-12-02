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

//    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        //capturar datos
        val edtNombre = findViewById<EditText>(R.id.edtNombre)
        val edtCorreo = findViewById<EditText>(R.id.edtCorreo)
        val edtPassword = findViewById<EditText>(R.id.edtPassword)
        val edtTelefono = findViewById<EditText>(R.id.edtTelefono)
        val checkBox = findViewById<CheckBox>(R.id.checkBox)
        val btnRegistrar = findViewById<Button>(R.id.btnRegistrar)

        val nombre = edtNombre.text.toString()
        var correo = edtCorreo.text.toString()
        val password = edtPassword.text.toString()
        val telefono = edtTelefono.text.toString()

        btnRegistrar.setOnClickListener {

            if (validarCampos() && checkBox.isChecked) {

                //Guardar Usuario
                val listaUsuarios = ArrayList<Usuario>()
                listaUsuarios.add(Usuario(nombre, correo, password, telefono))

                val intent = Intent(this, LoginActivity::class.java)
                intent.putExtra("LISTA_USUARIOS", listaUsuarios)
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

//    private fun guardarUsuario(nombre: String, correo: String, password: String, telefono: String) {
//        listaUsuarios.add(Usuario(nombre, correo, password, telefono))
//
////        println("Usuario guardado: Nombre=$nombre, Correo=$correo, Teléfono=$telefono")
//    }
//
//    private fun marcarUsuarioRegistrado() {
//        val editor = sharedPreferences.edit()
//        editor.putBoolean("usuarioRegistrado", true)
//        editor.apply()
//    }
}

