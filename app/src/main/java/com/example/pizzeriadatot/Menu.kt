package com.example.pizzeriadatot

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pizzeriadatot.databinding.ActivityHomePageBinding
import com.example.pizzeriadatot.databinding.ActivityMenuBinding

class Menu : AppCompatActivity() {
    private lateinit var binding: ActivityMenuBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }




}