package com.example.pizzeriadatot


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import com.example.pizzeriadatot.databinding.ActivitySignInBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignIn : AppCompatActivity()
{
    private lateinit var binding: ActivitySignInBinding
    private lateinit var  database : DatabaseReference
    private lateinit var auth : FirebaseAuth
    private lateinit var utenteFirebase : FirebaseUser

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
        if (valida(nome, cognome, email, password))
        {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener{
                    if (it.isSuccessful)
                    {
                        utenteFirebase = auth.currentUser!!
                        salvaSuDataBase(nome, cognome, email)
                    }else
                    {
                        try
                        {
                            throw it.exception!!
                        }
                        catch (e : FirebaseAuthUserCollisionException)
                        {
                            binding.RegTextEmail.error = "Email già in uso, prova con un'altra"
                            binding.RegTextEmail.requestFocus()
                        }
                    }
                }
        }
    }

    private fun salvaSuDataBase(nome : String, cognome : String, email : String)
    {
        database = FirebaseDatabase.getInstance().getReference("Utenti")
        val user = User(nome, cognome, email)
        database.child(utenteFirebase.uid).setValue(user).addOnSuccessListener {
            binding.TextNomeUtente.text.clear()
            binding.TextCognomeUtente.text.clear()
            binding.RegTextEmail.text.clear()
            binding.RegTextPassword.text.clear()
            Toast.makeText(this, "Registrazione effettuata con successo!", Toast.LENGTH_LONG).show()
            utenteFirebase.sendEmailVerification()
            Toast.makeText(this, "Inviata email di verifica, controlla la tua casella di posta elettronica", Toast.LENGTH_LONG).show()
            auth.signOut()
            val toLogin = Intent(this, Login::class.java)
            toLogin.putExtra("email", email)
            startActivity(toLogin)
            finish()
        }.addOnFailureListener {
            Toast.makeText(this, "Errore!\nRiprova", Toast.LENGTH_LONG).show()
        }
    }

    private fun valida(nome : String, cognome: String, email: String, password: String) : Boolean //DA SISTEMARE
    {
        if (nome.isEmpty())
        {
            binding.TextNomeUtente.error = "Inserisci il tuo nome"
            binding.TextNomeUtente.requestFocus()
            return false
        }
        if (cognome.isEmpty())
        {
            binding.TextCognomeUtente.error = "Inserisci il tuo cognome"
            binding.TextCognomeUtente.requestFocus()
            return false
        }
        if (email.isEmpty())
        {
            binding.RegTextEmail.error = "Inserisci la tua email"
            binding.RegTextEmail.requestFocus()
            return false
        }else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            binding.RegTextEmail.error = "Inserisci una email valida"
            binding.RegTextEmail.requestFocus()
            return false
        }
        if (password.isEmpty())
        {
            binding.RegTextPassword.error = "Inserisci una password"
            binding.RegTextPassword.requestFocus()
            return false
        }else if (!passwordValida(password))
        {
            binding.RegTextPassword.error = "Inserisci una password valida: deve contenere almeno un lettera maiuscola, un numero e un carattere speciale (@#\$%^&+=)"
            binding.RegTextPassword.requestFocus()
            return false
        }else if (password.length < 8)
        {
            binding.RegTextPassword.error = "Inserisci una password valida: deve avere almeno 8 caratteri"
            binding.RegTextPassword.requestFocus()
            return false
        }
        else
        {
            return true
        }
    }

    private fun passwordValida(password: String): Boolean
    {
        var hasLower = false
        var hasUpper = false
        var hasNumber = false
        var hasSpecial = false

        for (char in password)
        {
            if (char.isLowerCase())
            {
                hasLower = true
            }else if (char.isUpperCase())
            {
                hasUpper = true
            }else if (char.isDigit())
            {
                hasNumber = true
            }else if (!char.isLetterOrDigit())
            {
                hasSpecial = true
            }
        }
        return hasLower && hasUpper && hasNumber && hasSpecial
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }
}