package com.example.pizzeriadatot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Utente : AppCompatActivity() {
    val loggato : Boolean = false;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_utente)
        if (!loggato)
        {
            val toLogin = Intent(this, Login::class.java)
            startActivity(toLogin)
        }
        else
        {
            val toUserPage = Intent(this, UserPage::class.java)
            startActivity(toUserPage)
        }
    }
}