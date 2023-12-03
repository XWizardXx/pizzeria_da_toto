package com.example.pizzeriadatot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pizzeriadatot.databinding.ActivityHomePageBinding
import com.example.pizzeriadatot.databinding.ActivityMenuBinding

class Menu : AppCompatActivity()
{
    private lateinit var binding: ActivityMenuBinding
    private lateinit var prodottiArrayList: ArrayList<Prodotto>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val nomi = arrayOf(
            "calzone"
        )

        val ingredienti = arrayOf(
            "sudo, podomoro, zozzarella"
        )

        val prezzi = arrayOf(
            "$3"
        )

        val allergeni = arrayOf(
            "glutine, latticini"
        )

        val imageIDs = intArrayOf(
            R.drawable.calzone
        )


        prodottiArrayList = ArrayList()
        for (i in nomi.indices)
        {
            val prodotto = Prodotto(nomi[i], ingredienti[i], prezzi[i], allergeni[i], imageIDs[i])
            prodottiArrayList.add(prodotto)
        }

        binding.listview.isClickable = true
        binding.listview.adapter = AdapterMenu(this, prodottiArrayList)
        binding.listview.setOnItemClickListener { parent, view, position, id ->
            val nome = nomi[position]
            val ingredenti = ingredienti[position]
            val prezzo = prezzi[position]
            val allergeni = allergeni[position]
            val imageID = imageIDs[position]

            val intent = Intent(this, ProdottoSingolo::class.java)
            intent.putExtra("nome", nome)
            intent.putExtra("ingredienti", ingredenti)
            intent.putExtra("prezzo", prezzo)
            intent.putExtra("allergeni", allergeni)
            intent.putExtra("imageID", imageID)
            startActivity(intent)
        }
    }
}