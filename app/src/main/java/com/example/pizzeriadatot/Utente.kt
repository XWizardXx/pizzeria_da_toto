package com.example.pizzeriadatot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Utente : AppCompatActivity() {
    val loggato : Boolean = true;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_utente)
        if (loggato)
        {
            val toUserPage = Intent(this, Login::class.java)
            startActivity(toUserPage)
        }
    }
}