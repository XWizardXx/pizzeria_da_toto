package com.example.pizzeriadatot

import android.content.Intent
import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.pizzeriadatot.databinding.ActivitySignInBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignIn : AppCompatActivity()
{
    private lateinit var binding: ActivitySignInBinding
    private lateinit var  database : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.Registrati.setOnClickListener {
            Log.d("premuto", "bottone premuto")
            val nome = binding.TextNomeUtente.text.toString()
            val email = binding.RegTextEmail.text.toString()
            val password = binding.RegTextPassword.text.toString()

            database = FirebaseDatabase.getInstance().getReference("Utenti")
            val user = User(nome, email, password)
            database.child(nome).setValue(user).addOnSuccessListener {
                binding.TextNomeUtente.text.clear()
                binding.RegTextEmail.text.clear()
                binding.RegTextPassword.text.clear()

                Toast.makeText(this, "Dati salvati!", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(this, "Errore!", Toast.LENGTH_SHORT).show()
            }


        }
    }
}