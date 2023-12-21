package com.example.pizzeriadatot

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pizzeriadatot.databinding.ActivityProdottoSingoloBinding

class ProdottoSingolo : AppCompatActivity() {

    private lateinit var binding : ActivityProdottoSingoloBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProdottoSingoloBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val nome = intent.getStringExtra("nome")
        val ingredienti = intent.getStringExtra("ingredienti")
        val prezzo = intent.getStringExtra("prezzo")
        val allergeni = intent.getStringExtra("allergeni")
        val imageID = intent.getIntExtra("imageID", R.drawable.calzone)

        binding.Nome.text = nome
        binding.foto.setImageResource(imageID)
        binding.Ingredienti.text = ingredienti
        binding.Allergeni.text = allergeni
        binding.Prezzo.text = prezzo


    }
}