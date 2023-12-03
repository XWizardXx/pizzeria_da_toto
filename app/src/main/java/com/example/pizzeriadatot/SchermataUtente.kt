package com.example.pizzeriadatot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class Utente : AppCompatActivity() {
    val loggato : Boolean = false;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_utente)
        if (!loggato)
        {
            val toUserPage = Intent(this, Login::class.java)
            startActivity(toUserPage)
        }
    }

    override fun onRestart() {
        super.onRestart()
        if(!loggato)
        {
            Log.d("non sono loggato", "torno al menu a tendina (restart)")
            val toMenuTendina = Intent(this, MenuTendina::class.java)
            startActivity(toMenuTendina)
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