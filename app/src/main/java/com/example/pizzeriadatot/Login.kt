package com.example.pizzeriadatot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Login : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        val noRegistrato : Button = findViewById(R.id.noRegistrato)
        noRegistrato.setOnClickListener {
            val toRegister = Intent(this, SignIn::class.java)
            startActivity(toRegister)
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }
}