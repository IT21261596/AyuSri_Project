package com.example.ayusri

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class doctorView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctor_view)
        var disbtn = findViewById<Button>(R.id.diseases)

        disbtn.setOnClickListener{
            var i = Intent(this,diseaseFetching::class.java)
            startActivity(i)
        }
    }









}