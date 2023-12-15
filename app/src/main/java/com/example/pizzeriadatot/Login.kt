package com.example.pizzeriadatot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class Login : AppCompatActivity()
{
    private lateinit var auth: FirebaseAuth
    private lateinit var utenteFirebase : FirebaseUser

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()

        val inviaEmail : Button = findViewById(R.id.reinviaEmailVedifica)
        inviaEmail.visibility = INVISIBLE

        val accedi : Button = findViewById(R.id.LoginButton)
        accedi.setOnClickListener {
            login(inviaEmail)
        }

        val noRegistrato : Button = findViewById(R.id.noRegistrato)
        noRegistrato.setOnClickListener {
            val toRegister = Intent(this, SignIn::class.java)
            startActivity(toRegister)
        }
    }

    override fun onStart() {
        super.onStart()

        if (auth.currentUser != null)
        {
            Log.d("SchermataUtente", "utente loggato")
            val toUser = Intent(this, SchermataUtente::class.java)
            startActivity(toUser)
        }else
        {
            Log.d("SchermataUtente", "utente non loggato")
        }
    }

    private fun login(inviaEmail: Button)
    {
        val testoEmail : TextView = findViewById(R.id.LoginTextEmail)
        val testoPassword : TextView = findViewById(R.id.LoginTextPassword)

        val email = testoEmail.text.toString()
        val password = testoPassword.text.toString()

        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener { task ->
            if(task.isSuccessful){
                utenteFirebase = auth.currentUser!!
                if (utenteFirebase.isEmailVerified)
                {

                    Toast.makeText(this, "Accesso effettuato", Toast.LENGTH_LONG).show()

                    val toUserPage = Intent(this,SchermataUtente::class.java)
                    startActivity(toUserPage)
                    Log.d("Login", "lacio attività schermataUtente")
                    finish()
                }else
                {
                    Toast.makeText(this, "controlla la tua casella di posta, se non hai ricevuto la mail clicca il tasto apposito", Toast.LENGTH_LONG).show()
                    inviaEmail.visibility = VISIBLE
                    inviaEmail.setOnClickListener {
                        utenteFirebase.sendEmailVerification()
                        auth.signOut()
                        Toast.makeText(this, "Effettua nuovamente il login dopo aver verificato l'account", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }.addOnFailureListener { exception ->
            Toast.makeText(applicationContext,exception.localizedMessage, Toast.LENGTH_LONG).show()
        }
    }
}