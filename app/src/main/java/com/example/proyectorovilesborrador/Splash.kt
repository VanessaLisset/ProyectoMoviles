package com.example.proyectorovilesborrador

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class Splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)  // Duración del SplashScreen
        val splashDuration: Long = 3000 // 5 segundos

        // Navega a la LoginActivity después de la duración del SplashScreen
        Handler().postDelayed({
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }, splashDuration)
    }
}