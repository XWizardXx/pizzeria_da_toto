package com.example.pizzeriadatot


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.pizzeriadatot.databinding.ActivitySignInBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.regex.Pattern

class SignIn : AppCompatActivity()
{
    private lateinit var binding: ActivitySignInBinding
    private lateinit var  database : DatabaseReference
    private lateinit var auth : FirebaseAuth
    /*private var PASSWORD_PATTERN : Pattern = Pattern.compile(
        "^" +
                "(?=.*[0-9])" +
                "(?=.*[a-z])" +
                "(?=.*[A-Z])" +
                "(?=.*[@#$%^&+=_\\-])" +
                "$")*/

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.Registrati.setOnClickListener {
            Log.d("premuto", "bottone premuto")
            registraUtente()
        }
    }

    private fun registraUtente()
    {
        val nome = binding.TextNomeUtente.text.toString()
        val cognome = binding.TextCognomeUtente.text.toString()
        val email = binding.RegTextEmail.text.toString()
        val password = binding.RegTextPassword.text.toString()
        /*valida(nome, cognome, email, password)*/
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener{
                if (it.isSuccessful)
                {
                    salvaSuDataBase(nome, cognome, email)
                }else
                {
                    Toast.makeText(this, it.exception!!.message, Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun salvaSuDataBase(nome : String, cognome : String, email : String)
    {
        database = FirebaseDatabase.getInstance().getReference("Utenti")
        val user = User(nome, cognome, email)
        database.child(FirebaseAuth.getInstance().currentUser!!.uid).setValue(user).addOnSuccessListener {
            binding.TextNomeUtente.text.clear()
            binding.TextCognomeUtente.text.clear()
            binding.RegTextEmail.text.clear()
            binding.RegTextPassword.text.clear()
            val toLogin = Intent(this, Login::class.java)
            startActivity(toLogin)
            finish()

            Toast.makeText(this, "Registrazione effettuata con successo!", Toast.LENGTH_LONG).show()
        }.addOnFailureListener {
            Toast.makeText(this, "Errore!\nRiprova", Toast.LENGTH_LONG).show()
        }
    }

    /*private fun valida(nome : String, cognome: String, email: String, password: String) //DA SISTEMARE
    {
        if (nome.isEmpty())
        {
            binding.TextNomeUtente.error = "Inserisci il tuo nome"
            binding.TextNomeUtente.requestFocus()
            return
        }
        if (cognome.isEmpty())
        {
            binding.TextCognomeUtente.error = "Inserisci il tuo cognome"
            binding.TextCognomeUtente.requestFocus()
            return
        }
        if (email.isEmpty())
        {
            binding.RegTextEmail.error = "Inserisci la tua email"
            binding.RegTextEmail.requestFocus()
            return
        }else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            binding.RegTextEmail.error = "Inserisci una email valida"
            binding.RegTextEmail.requestFocus()
            return
        }
        if (password.isEmpty())
        {
            binding.RegTextPassword.error = "Inserisci una password"
            binding.RegTextPassword.requestFocus()
            return
        }else if (!PASSWORD_PATTERN.matcher(password).matches())
        {
            binding.RegTextPassword.error = "Inserisci una password valida: deve contenere almeno un lettera maiuscola, un numero e un carattere speciale"
            binding.RegTextPassword.requestFocus()
            return
        }else if (password.length < 8)
        {
            binding.RegTextPassword.error = "Inserisci una password valida: deve avere almeno 8 caratteri"
            binding.RegTextPassword.requestFocus()
        }
    }*/
}