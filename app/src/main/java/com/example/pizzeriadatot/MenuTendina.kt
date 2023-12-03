package com.example.pizzeriadatot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton

class MenuTendina : AppCompatActivity()
{

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_tendina)

        val goBack : ImageButton = findViewById(R.id.toHome)
        goBack.setOnClickListener {
            val toHome = Intent(this, HomePage::class.java)
            Log.d("PremutoGoBack", "cambio attivita")
            startActivity(toHome)
        }

        val userPage : Button = findViewById(R.id.toUser)
        userPage.setOnClickListener {
            val toUser = Intent(this, Utente::class.java)
            Log.d("PremutoUser", "cambio attivita")
            startActivity(toUser)
        }

        val menu : Button = findViewById(R.id.toMenu)
        menu.setOnClickListener {
            val toMenu = Intent(this, Menu::class.java)
            Log.d("PremutoMenu", "cambio attivita")
            startActivity(toMenu)
        }
    }

    /*
    override fun onPause() {
        super.onPause()
        Log.d("PremutaFrecciaIndietro", "torno alla home page")
        val toHomepAge = Intent(this, HomePage::class.java)
        startActivity(toHomepAge)
    }

     */
}