package com.example.pizzeriadatot

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.Objects

class SignIn : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        val dataBase : FirebaseDatabase = FirebaseDatabase.getInstance()
        val reference : DatabaseReference = dataBase.reference


        val testoNome : TextView = findViewById(R.id.NomeUtenteReg)
        val testoEmail : TextView = findViewById(R.id.RegTextEmail)
        val testoPassword : TextView = findViewById(R.id.RegTextPassword)
        val registrati : Button = findViewById(R.id.Registrati)
        registrati.setOnClickListener {
            val nome = testoNome.text.toString()
            val email = testoEmail.text.toString()
            val password = testoPassword.text.toString()

            val mappa : HashMap<String, String> = HashMap()
            mappa.put("Nome", nome)
            mappa.put("Email", email)
            mappa.put("Password", password)

            reference.child("Utente")
                .child(nome)
                .setValue(mappa)
                .addOnSuccessListener (OnSuccessListener {
                    //TODO
                })
        }
    }
}