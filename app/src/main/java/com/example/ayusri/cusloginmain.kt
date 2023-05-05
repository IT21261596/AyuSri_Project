package com.example.ayusri

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.ayusri.location.locusfetch

class cusloginmain : AppCompatActivity() {
    private lateinit var docbtn: Button
    private lateinit var disbtn: Button
    private lateinit var locbtn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cusloginmain)
        disbtn=findViewById(R.id.diseases)
        docbtn=findViewById(R.id.doctor)
        locbtn = findViewById(R.id.locations)
        docbtn.setOnClickListener{
            var i = Intent(this, docCusfetch::class.java)
            startActivity(i)
        }

        disbtn.setOnClickListener{
            var i = Intent(this,discusfetch::class.java)
            startActivity(i)
        }

        locbtn.setOnClickListener{
            var i = Intent(this, locusfetch::class.java)
            startActivity(i)
        }

    }
}