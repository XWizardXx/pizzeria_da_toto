package com.example.pizzeriadatot

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.pizzeriadatot.databinding.ActivityHomePageBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sin


class HomePage : AppCompatActivity()
{
    private lateinit var binding: ActivityHomePageBinding
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityHomePageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.PossoOrdinare.visibility = View.INVISIBLE
        val menu: ImageButton = findViewById(R.id.menu)
        menu.setOnClickListener {
            val toMenu = Intent(this, MenuTendina::class.java)
            Log.d("HomePage", "cliccao menu a tendina")
            startActivity(toMenu)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        prendiPosizione()
    }

    private fun prendiPosizione()
    {
        val latLocale = 0.6775570352619
        val longLocale = 0.2899988732358
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
            && ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED)
        {
            val location = fusedLocationProviderClient.lastLocation
            location.addOnSuccessListener {
                if (it != null)
                {
                    val latitude = it.latitude * (Math.PI / 180)
                    val logitude = it.longitude * (Math.PI / 180)
                    val d =  6371 * cos(
                        sin(latitude) * sin(latLocale) + cos(
                            latitude * cos(
                                latLocale
                            ) * cos(longLocale - logitude)
                        )
                    ).pow(-1.0)
                    if (d >= 30)
                    {
                        binding.PossoOrdinare.visibility = View.VISIBLE
                    }
                }else
                {
                    Toast.makeText(this, "Impossibile trovare le coordinate!", Toast.LENGTH_LONG).show()
                }
            }
        }else
        {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), 100)
            return
        }
    }
}