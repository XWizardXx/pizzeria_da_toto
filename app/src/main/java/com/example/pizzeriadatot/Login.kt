package com.example.pizzeriadatot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity()
{
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth= FirebaseAuth.getInstance()

        val registrato : Button = findViewById(R.id.LoginButton)
        registrato.setOnClickListener {
            login()
        }

        val noRegistrato : Button = findViewById(R.id.noRegistrato)
        noRegistrato.setOnClickListener {
            val toRegister = Intent(this, SignIn::class.java)
            startActivity(toRegister)
        }
    }

    fun login()
    {
        val testoEmail : TextView = findViewById(R.id.LoginTextEmail)
        val testoPassword : TextView = findViewById(R.id.LoginTextPassword)

        val email = testoEmail.text.toString()
        val password = testoPassword.text.toString()

        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener { task ->
            if(task.isSuccessful){
                val toUserPage = Intent(this,SchermataUtente::class.java)
                startActivity(toUserPage)
                finish()

                Toast.makeText(this, "Accesso effettuato", Toast.LENGTH_LONG).show()
            }
        }.addOnFailureListener { exception ->
            Toast.makeText(applicationContext,exception.localizedMessage, Toast.LENGTH_LONG).show()
        }
    }
}