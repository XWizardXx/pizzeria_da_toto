package com.example.pizzeriadatot

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class SignIn : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        val registrati : Button = findViewById(R.id.Registrati)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
    }
}