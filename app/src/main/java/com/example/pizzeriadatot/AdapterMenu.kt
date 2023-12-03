package com.example.pizzeriadatot

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class AdapterMenu(private val contex: Activity, private val arrayList: ArrayList<Prodotto>) : ArrayAdapter<Prodotto>(contex,
                    R.layout.list_item, arrayList)
{
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View
    {
        val inflater : LayoutInflater = LayoutInflater.from(contex)
        val view : View = inflater.inflate(R.layout.list_item, null)

        val imageView : ImageView = view.findViewById(R.id.foto)
        val nomePiatto : TextView = view.findViewById(R.id.nomePiatto)
        val ingredienti : TextView = view.findViewById(R.id.ingredienti)
        val prezzo : TextView = view.findViewById(R.id.prezzo)

        imageView.setImageResource(arrayList[position].immagine)
        nomePiatto.text = arrayList[position].nomePiatto
        ingredienti.text = arrayList[position].ingredienti
        prezzo.text = arrayList[position].prezzo

        return view
    }
}