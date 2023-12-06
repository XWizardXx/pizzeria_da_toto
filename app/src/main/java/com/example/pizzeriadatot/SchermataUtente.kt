package com.example.pizzeriadatot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.auth.FirebaseAuth

class SchermataUtente : AppCompatActivity() {
    var loggato : Boolean = false
    private lateinit var auth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schermata_utente)

        auth = FirebaseAuth.getInstance()
        if (auth.currentUser != null)
        {
            val toLogin = Intent(this, Login::class.java)
            startActivity(toLogin)
        }
    }

    override fun onRestart() {
        super.onRestart()
        if(auth.currentUser != null)
        {
            Log.d("non sono loggato", "torno al menu a tendina (restart)")
            val toMenuTendina = Intent(this, MenuTendina::class.java)
            startActivity(toMenuTendina)
        }
    }

}