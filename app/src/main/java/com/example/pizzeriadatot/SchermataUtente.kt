package com.example.pizzeriadatot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class SchermataUtente : AppCompatActivity() {
    var loggato : Boolean = false
    private lateinit var auth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schermata_utente)

        auth = FirebaseAuth.getInstance()
        val disconnetti : Button = findViewById(R.id.Disconnetti)
        disconnetti.setOnClickListener {
            auth.signOut()
            val toLogin = Intent(this, Login::class.java)
            startActivity(toLogin)
            finish()
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