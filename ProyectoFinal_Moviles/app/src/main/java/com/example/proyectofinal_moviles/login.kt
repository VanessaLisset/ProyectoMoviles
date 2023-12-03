package com.example.proyectofinal_moviles

import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = Color.TRANSPARENT
        }

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        supportActionBar?.hide()

        setContentView(R.layout.activity_login)

        val textViewRegistrate = findViewById<TextView>(R.id.Registrate)
        val btnIniciarSesion = findViewById<Button>(R.id.btnIngresar)
        val edtUs = findViewById<EditText>(R.id.edtUs)
        val edtPassword = findViewById<EditText>(R.id.edtPassword)

        textViewRegistrate.setOnClickListener {
            val intent = Intent(this, Registro::class.java)
            startActivity(intent)
        }

        btnIniciarSesion.setOnClickListener {
            val usuario = edtUs.text.toString()
            val password = edtPassword.text.toString()

            iniciarSesion(usuario, password)
        }
    }

    private fun iniciarSesion(usuario: String, password: String) {
        val usuarioValido = Registro.usuariosRegistrados.find {
            it.usuario == usuario && it.password == password
        }

        if (usuarioValido != null) {
            Toast.makeText(this, "Bienvenido ${usuarioValido.usuario}", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, Productos::class.java)
            startActivity(intent)

        } else {
            Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show()
        }
    }
}
