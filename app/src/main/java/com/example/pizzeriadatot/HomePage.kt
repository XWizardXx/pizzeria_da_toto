package com.example.pizzeriadatot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView


class HomePage : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)

        val menu: ImageButton = findViewById(R.id.menu)
        menu.setOnClickListener {
            val toMenu = Intent(this, MenuTendina::class.java)
            Log.d("PremutoMenu", "cambio attivita")
            startActivity(toMenu)
        }



    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onStart()
    {
        super.onStart()
    }
}