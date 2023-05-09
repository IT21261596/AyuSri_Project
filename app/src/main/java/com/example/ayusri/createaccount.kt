package com.example.ayusri

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore

class   createaccount : AppCompatActivity() {
    private lateinit var auth:FirebaseAuth
    private lateinit var db: FirebaseFirestore
    lateinit var Continue:Button
    lateinit var EmailRegister:EditText
    lateinit var PasswordRegister:EditText
    lateinit var Name :EditText
    lateinit var Phone :EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_createaccount)
        Continue = findViewById(R.id.registerbutton)
        EmailRegister = findViewById(R.id.cemail)
        PasswordRegister = findViewById(R.id.cpassword1)
        Name =  findViewById(R.id.cname)
        Phone =  findViewById(R.id.cphone)



        auth= FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()
        Continue.setOnClickListener {
            if(checking())
            {
                var email=EmailRegister.text.toString()
                var password= PasswordRegister.text.toString()
                var name=Name.text.toString()
                var phone=Phone.text.toString()
                val user= hashMapOf(
                    "Name" to name,
                    "Phone" to phone,
                    "email" to email
                )
                val Users=db.collection("USERS")
                val query =Users.whereEqualTo("email",email).get()
                    .addOnSuccessListener {
                            tasks->
                        if(tasks.isEmpty)
                        {
                            auth.createUserWithEmailAndPassword(email,password)
                                .addOnCompleteListener(this){
                                        task->
                                    if(task.isSuccessful)
                                    {
                                        Users.document(email).set(user)
                                        val intent= Intent(this,login::class.java)
                                        intent.putExtra("email",email)
                                        startActivity(intent)
                                        finish()
                                    }
                                    else
                                    {
                                        Toast.makeText(this,"Authentication Failed", Toast.LENGTH_LONG).show()
                                    }
                                }
                        }
                        else
                        {
                            Toast.makeText(this,"User Already Registered", Toast.LENGTH_LONG).show()
                            val intent= Intent(this,cusloginmain::class.java)
                            startActivity(intent)
                        }
                    }
            }
            else{
                Toast.makeText(this,"Enter the Details", Toast.LENGTH_LONG).show()
            }
        }

    }
    private fun checking():Boolean{
        if(Name.text.toString().trim{it<=' '}.isNotEmpty()
            && Phone.text.toString().trim{it<=' '}.isNotEmpty()
            && EmailRegister.text.toString().trim{it<=' '}.isNotEmpty()
            && PasswordRegister.text.toString().trim{it<=' '}.isNotEmpty()
        )
        {
            return true
        }
        return false
    }
    }
