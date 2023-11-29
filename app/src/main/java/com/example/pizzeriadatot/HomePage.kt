package com.example.pizzeriadatot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView


class HomePage : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)

        val menu : Button = findViewById<Button>(R.id.menu)
        menu.setOnClickListener {
            val toMenu = Intent(this, MenuTendina::class.java)
            startActivity(toMenu)
        }
        val titolo : TextView = findViewById(R.id.titolo)



    }

    override fun onStart()
    {
        super.onStart()
    }
}