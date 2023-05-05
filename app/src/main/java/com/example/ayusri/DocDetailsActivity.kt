package com.example.ayusri

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class DocDetailsActivity : AppCompatActivity() {
    private lateinit var tvDocId: TextView
    private lateinit var tvDocName: TextView
    private lateinit var tvDocEmail: TextView
    private lateinit var tvEmpSa: TextView
    private lateinit var btnUpdate: Button
    private lateinit var btnDelete: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doc_details)
    }
}