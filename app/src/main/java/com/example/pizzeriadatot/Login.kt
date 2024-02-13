package com.example.pizzeriadatot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
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

        val accedi : Button = findViewById(R.id.LoginButton)
        accedi.setOnClickListener {
            login()
        }

        val noRegistrato : Button = findViewById(R.id.noRegistrato)
        noRegistrato.setOnClickListener {
            val toRegister = Intent(this, SignIn::class.java)
            startActivity(toRegister)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }
    }

    override fun onRestart() {
        super.onRestart()

        if (auth.currentUser != null)
        {
            finish()
        }else
        {
            Log.d("SchermataUtente", "utente non loggato")
        }
    }

    override fun onStart() {
        super.onStart()

        if (auth.currentUser != null)
        {
            Log.d("SchermataUtente", "utente loggato")
            val toUser = Intent(this, SchermataUtente::class.java)
            startActivity(toUser)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
            finish()
        }else
        {
            Log.d("SchermataUtente", "utente non loggato")
        }
    }

    private fun login()
    {
        val testoEmail : TextView = findViewById(R.id.LoginTextEmail)
        val testoPassword : TextView = findViewById(R.id.LoginTextPassword)

        val email = testoEmail.text.toString()
        val password = testoPassword.text.toString()

        if (valida(email, password, testoEmail, testoPassword))
            auth.signInWithEmailAndPassword(email,password).addOnCompleteListener { task ->
            if(task.isSuccessful){
                utenteFirebase = auth.currentUser!!
                if (utenteFirebase.isEmailVerified)
                {

                    Toast.makeText(this, "Accesso effettuato", Toast.LENGTH_LONG).show()

                    val toUserPage = Intent(this,SchermataUtente::class.java)
                    startActivity(toUserPage)
                    Log.d("Login", "lacio attività schermataUtente")
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                }else
                {
                    Toast.makeText(this, "Account non verificato, controlla la tua casella di posta elettronica", Toast.LENGTH_LONG).show()
                }
            }
            else
            {
                try
                {
                    throw task.exception!!
                }
                catch (e : FirebaseAuthInvalidCredentialsException)
                {
                    Toast.makeText(this, "Email o password errate!", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun valida(email: String, password: String, testoEmail: TextView, testoPassword: TextView): Boolean
    {
        if (email.isEmpty())
        {
            testoEmail.error = "Inserisci un indirizzo email"
            testoEmail.requestFocus()
            return false
        }else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            testoEmail.error = "Inserisci una mail valida"
            testoEmail.requestFocus()
            return false
        }
        if (password.isEmpty())
        {
            testoPassword.error = "inserisci la password"
            testoPassword.requestFocus()
            return false
        }else
        {
            return true
        }
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }
}