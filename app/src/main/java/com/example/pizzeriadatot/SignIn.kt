package com.example.pizzeriadatot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import java.util.Objects

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

        /*
        val dataBase : FirebaseDatabase = FirebaseDatabase.getInstance()
        val reference : DatabaseReference = dataBase.reference


        val testoNome : TextView = findViewById(R.id.NomeUtenteReg)
        val testoEmail : TextView = findViewById(R.id.RegTextEmail)
        val testoPassword : TextView = findViewById(R.id.RegTextPassword)
        val registrati : Button = findViewById(R.id.Registrati)
        registrati.setOnClickListener(View.OnClickListener {
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
                    Toast.makeText(this, "Dati inseriti con successo!", Toast.LENGTH_SHORT)
                })
                .addOnFailureListener(OnFailureListener {
                    Toast.makeText(this, ""+it.message, Toast.LENGTH_SHORT)
                })
        })

 */
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
            Toast.makeText(applicationContext,exception.localizedMessage,Toast.LENGTH_LONG).show()
        }
    }
}