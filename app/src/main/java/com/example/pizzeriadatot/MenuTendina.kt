package com.example.pizzeriadatot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class MenuTendina : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_tendina)

        val goBack : ImageButton = findViewById<ImageButton>(R.id.toHome)
        goBack.setOnClickListener {
            val toHome = Intent(this, HomePage::class.java)
            startActivity(toHome)
        }
    }
}