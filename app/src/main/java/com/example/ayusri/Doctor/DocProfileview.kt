package com.freelancingapp.freelancingapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ayusri.Models.Doctors
import com.example.ayusri.databinding.ActivityDocProfileviewBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class DocProfileview : AppCompatActivity() {

    private lateinit var binding: ActivityDocProfileviewBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var clientsRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDocProfileviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        clientsRef = FirebaseDatabase.getInstance().getReference("Doctors")

        val userId = firebaseAuth.currentUser?.uid

        if (userId == null) {
            Toast.makeText(this, "User ID not found", Toast.LENGTH_SHORT).show()
            return
        }

        clientsRef.child(userId).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val client = snapshot.getValue(Doctors::class.java)
                if (client != null) {
                    binding.docname.text = client.docName
                    binding.docemail.text = client.docEmail
                    binding.docphone.text = client.docPhone
                    binding.dochospital.text = client.docHospital
                    binding.docaddress.text = client.docAddress

                } else {
                    Toast.makeText(this@DocProfileview, "Client data not found", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@DocProfileview, "Error retrieving data: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        })


    }


}