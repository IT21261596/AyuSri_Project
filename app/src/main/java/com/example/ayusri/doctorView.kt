package com.example.ayusri

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.ayusri.location.locfetching

class doctorView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctor_view)
        var disbtn = findViewById<Button>(R.id.diseases)
        var docbtn = findViewById<Button>(R.id.doctor)
        var locbtn = findViewById<Button>(R.id.locations)
        var medibtn = findViewById<Button>(R.id.medicines)

        disbtn.setOnClickListener{
            var i = Intent(this,diseaseFetching::class.java)
            startActivity(i)
        }
        docbtn.setOnClickListener{
            var i = Intent(this,docFetching::class.java)
            startActivity(i)
        }
        locbtn.setOnClickListener{
            var i = Intent(this,locfetching::class.java)
            startActivity(i)
        }
        medibtn.setOnClickListener{
            var i = Intent(this,MedecineFetch::class.java)
            startActivity(i)
        }

    }









}