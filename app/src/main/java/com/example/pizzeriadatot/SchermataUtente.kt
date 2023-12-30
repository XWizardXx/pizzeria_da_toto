package com.example.pizzeriadatot

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.pizzeriadatot.databinding.ActivitySchermataUtenteBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sin

class SchermataUtente : AppCompatActivity() {
    private lateinit var binding : ActivitySchermataUtenteBinding
    private lateinit var auth : FirebaseAuth
    private lateinit var dataBase : DatabaseReference
    private lateinit var snapshot: DataSnapshot

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivitySchermataUtenteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        val disconnetti : Button = findViewById(R.id.Disconnetti)
        disconnetti.setOnClickListener {
            auth.signOut()
            val toLogin = Intent(this, Login::class.java)
            startActivity(toLogin)
            finish()
        }

        if (auth.currentUser != null)
        {
            leggiDataBase(utenteCorrente = auth.currentUser)
        }
    }

    private fun leggiDataBase(utenteCorrente : FirebaseUser?)
    {
        dataBase = FirebaseDatabase.getInstance().getReference("Utenti")
        dataBase.child(utenteCorrente!!.uid).get().addOnCompleteListener {
            if (it.isSuccessful)
            {
                if (it.result.exists())
                {
                    snapshot = it.result
                    val textNomeUtente = snapshot.child("nome").value.toString()
                    val textCognomeUtente = snapshot.child("cognome").value.toString()
                    val textEmailUtente = snapshot.child("email").value.toString()
                    binding.SUTextNome.text = textNomeUtente
                    binding.SUTextCognome.text = textCognomeUtente
                    binding.SUTextEmail.text = textEmailUtente
                }else
                {
                    Toast.makeText(this, "L'utente non esiste!", Toast.LENGTH_LONG).show()
                }
            }else
            {
                Toast.makeText(this, "Dati non trovati!", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onRestart() {
        super.onRestart()
        if(auth.currentUser != null)
        {
            Log.d("SchermataUtente", "torno al menu a tendina (restart)")
            val toMenuTendina = Intent(this, MenuTendina::class.java)
            startActivity(toMenuTendina)
            finish()
        }
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }
}