package com.example.pizzeriadatot

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView


class HomePage : AppCompatActivity()
{
    val menu : Button = findViewById(R.id.menu)
    val titolo : TextView = findViewById(R.id.titolo)

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)
    }
}