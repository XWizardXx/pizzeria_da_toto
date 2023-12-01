package com.example.pizzeriadatot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class Login : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val noRegistrato : Button = findViewById(R.id.noRegistrato)
        noRegistrato.setOnClickListener {
            val toRegister = Intent(this, SignIn::class.java)
            startActivity(toRegister)
        }
    }
/*
    override fun onPause() {
        super.onPause()
        Log.d("premutaFrecciaIndietro", "torno al menu a tendina")
        val toMenuTendina = Intent(this, MenuTendina::class.java)
        startActivity(toMenuTendina)
    }

 */
}