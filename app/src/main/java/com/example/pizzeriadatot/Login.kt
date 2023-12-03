package com.example.pizzeriadatot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity()
{
    private lateinit var  auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth= FirebaseAuth.getInstance()

        val noRegistrato : Button = findViewById(R.id.noRegistrato)
        noRegistrato.setOnClickListener {
            val toRegister = Intent(this, SignIn::class.java)
            startActivity(toRegister)
        }
    }

    fun login()
    {
        val testoEmail : TextView = findViewById(R.id.RegTextEmail)
        val testoPassword : TextView = findViewById(R.id.RegTextPassword)

        val email = testoEmail.text.toString()
        val password = testoPassword.text.toString()

        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener { task ->
            if(task.isSuccessful){
                val intent= Intent(this,Utente::class.java)
                intent.putExtra("loggato", true)
                startActivity(intent)
                finish()
            }
        }.addOnFailureListener { exception ->
            Toast.makeText(applicationContext,exception.localizedMessage, Toast.LENGTH_LONG).show()
        }
    }
/*
    override fun onPause() {
        super.onPause()
        Log.d("premutaFrecciaIndietro", "torno al menu a tendina")
        val toMenuTendina = Intent(this, MenuTendina::class.java)
        startActivity(toMenuTendina)
    }

 */
}