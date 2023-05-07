package com.example.ayusri.Doctor

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import android.widget.Toast
import com.example.ayusri.Models.Doctors
import com.example.ayusri.R

import com.example.ayusri.databinding.ActivityDocProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class DocProfile : AppCompatActivity() {
    private lateinit var binding: ActivityDocProfileBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var  dbRef: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDocProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dbRef = FirebaseDatabase.getInstance().getReference("Doctors")
        binding.dregisterbutton.setOnClickListener {

            val docId = firebaseAuth.currentUser?.uid

            if (docId != null) {

                val docName = binding.dname.text.toString()
                val docEmail = binding.demail.text.toString()
                val docPhone = binding.dcontactnum.text.toString()
                val docHos = binding.dhospital.text.toString()
                val docAdd = binding.daddress.text.toString()
//validation
                if (docName.isEmpty()) {
                    binding.dname.error = "Please enter name"
                }
                if (docEmail.isEmpty()) {
                    binding.demail.error = "Please enter Email"
                }
                if (docPhone.isEmpty()) {
                    binding.dcontactnum.error = "Please enter Phone Number"
                }
                if (docHos.isEmpty()) {
                    binding.dhospital.error = "Please enter Hospital name"
                }
                if (docAdd.isEmpty()) {
                    binding.daddress.error = "Please enter Address"
                } else {

                    val doctors = Doctors(docId, docName, docEmail, docPhone, docHos, docAdd)
                    dbRef.child(docId).setValue(doctors)
                        .addOnCompleteListener {
                            Toast.makeText(this, "Data inserted successfully", Toast.LENGTH_LONG)
                                .show()

                            binding.dname.text.clear()
                            binding.demail.text.clear()
                            binding.dcontactnum.text.clear()
                            binding.dhospital.text.clear()
                            binding.daddress.text.clear()

                        }
                        .addOnFailureListener { err ->
                            Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
                        }

                }
            } else {
                Toast.makeText(this, "User not authenticated", Toast.LENGTH_SHORT).show()
            }
        }
    }
}


