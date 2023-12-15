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
            Log.d("MenuTendina", "torno alla home page")
            startActivity(toHome)
            finish()
        }

        val userPage : Button = findViewById(R.id.toUser)
        userPage.setOnClickListener {
            val toLogin = Intent(this, Login::class.java)
            Log.d("MenuTendina", "torno alla homepage")
            startActivity(toLogin)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }

        val menu : Button = findViewById(R.id.toMenu)
        menu.setOnClickListener {
            val toMenu = Intent(this, Menu::class.java)
            Log.d("MenuTendina", "torno alla homepage")
            startActivity(toMenu)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }

    /*
    override fun onPause() {
        super.onPause()
        Log.d("PremutaFrecciaIndietro", "torno alla home page")
        val toHomepAge = Intent(this, HomePage::class.java)
        startActivity(toHomepAge)
    }*/
}