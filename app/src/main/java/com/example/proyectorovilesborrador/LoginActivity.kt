package com.example.proyectorovilesborrador


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var correo: EditText
    private lateinit var password: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        correo = findViewById(R.id.edtCorreo)
        password = findViewById(R.id.edtCorreo)

        var correoString = correo.text.toString()
        var passwordString = password.text.toString()

        val ingresarButton: Button = findViewById(R.id.btnIngresar)
        val registrarTextView: TextView = findViewById(R.id.textView3)

        val listaUsuarios = intent.getSerializableExtra("LISTA_USUARIOS") as? ArrayList<Usuario?>

        ingresarButton.setOnClickListener {
            if(listaUsuarios != null){
                for(usuario in listaUsuarios){
                    if(usuario != null && usuario.correo == correoString && usuario.password == passwordString){
                        val intent = Intent(this,MainActivity::class.java)
                        intent.putExtra("LISTA_USUARIOS", listaUsuarios)
                        startActivity(intent)
                        return@setOnClickListener
                    }
                }
            }else{
                Toast.makeText(this,"Registrate primero", Toast.LENGTH_LONG).show()
            }
        }

        //Registrar
        registrarTextView.setOnClickListener {
            val intent = Intent(this, RegistroActivity::class.java)
            startActivity(intent)
        }
    }
}
