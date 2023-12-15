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
            Log.d("HomePage", "cliccao menu a tendina")
            startActivity(toMenu)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }
    }
}