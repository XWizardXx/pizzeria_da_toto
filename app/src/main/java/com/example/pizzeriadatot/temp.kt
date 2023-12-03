package com.example.pizzeriadatot

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class temp {
    class SignIn : AppCompatActivity()
    {
        private lateinit var  auth: FirebaseAuth
        override fun onCreate(savedInstanceState: Bundle?)
        {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_sign_in)

            auth = FirebaseAuth.getInstance()

            val registrati : Button = findViewById(R.id.Registrati)
            registrati.setOnClickListener {
                register()
            }
        }

        fun register()
        {
            val testoNome : TextView = findViewById(R.id.NomeUtenteReg)
            val testoEmail : TextView = findViewById(R.id.RegTextEmail)
            val testoPassword : TextView = findViewById(R.id.RegTextPassword)

            val email = testoEmail.text.toString()
            val nome = testoNome.text.toString()
            val password = testoPassword.text.toString()



            auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener { task ->
                if(task.isSuccessful){
                    val intent= Intent(this,Login::class.java)
                    startActivity(intent)
                    Toast.makeText(applicationContext, "Registrato con successo!", Toast.LENGTH_LONG)
                    finish()
                }
            }.addOnFailureListener { exception ->
                Toast.makeText(applicationContext,exception.localizedMessage, Toast.LENGTH_LONG).show()
            }
        }
    }
}