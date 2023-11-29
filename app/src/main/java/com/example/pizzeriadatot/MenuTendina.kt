package com.example.pizzeriadatot

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class MenuTendina : AppCompatActivity()
{
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_tendina)

        val goBack : ImageButton = findViewById<ImageButton>(R.id.imageButton9)
    }
}