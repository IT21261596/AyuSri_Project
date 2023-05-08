package com.example.ayusri

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.ayusri.Models.Medicine
import com.google.firebase.database.FirebaseDatabase

class buyyMedicine : AppCompatActivity() {
    private lateinit var tvDisTopic: TextView
    private lateinit var tvDisAdd: TextView
    private lateinit var medprice: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buyy_medicine)

        initView()
        setValueToViews()


    }






    private fun setValueToViews() {

        tvDisTopic.text = intent.getStringExtra("mediTopic")
        tvDisAdd.text = intent.getStringExtra("mediAdd")
        medprice.text = intent.getStringExtra("mediPrice")

    }

    private fun initView() {

        tvDisTopic = findViewById(R.id.tvmedicine)
        tvDisAdd = findViewById(R.id.tvDiscription)
        medprice = findViewById(R.id.tvprice)



    }
}
