package com.example.ayusri

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.example.ayusri.Models.Doctors
import com.google.android.material.navigation.NavigationView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class docreg : AppCompatActivity() {

    private lateinit var etdname:EditText
    private lateinit var etdemail:EditText
    private lateinit var etdcontactnum :EditText
    private lateinit var etdhospital:EditText
    private lateinit var etdaddress:EditText
    private lateinit var dregbtn:Button

    private lateinit var dbRef:DatabaseReference

    lateinit var toggle : ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_docreg)

        etdname = findViewById(R.id.dname)
        etdemail = findViewById(R.id.demail)
        etdcontactnum = findViewById(R.id.dcontactnum)
        etdhospital = findViewById(R.id.dhospital)
        etdaddress = findViewById(R.id.daddress)
        dregbtn = findViewById(R.id.dregisterbutton)


        dbRef = FirebaseDatabase.getInstance().getReference("Doctors")

        dregbtn.setOnClickListener{
            saveDoctorData()
        }

        val drawerLayout : DrawerLayout = findViewById(R.id.drawerLayout)
        val navView : NavigationView = findViewById(R.id.nav_view)

        toggle = ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navView.setNavigationItemSelectedListener {
            fun login(){
                var intent =  Intent(this,loginmain::class.java)
                startActivity(intent)
                Toast.makeText(applicationContext,"Clicked Login",Toast.LENGTH_SHORT).show()
            }

            fun home(){
                var intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
                Toast.makeText(applicationContext,"Clicked Home",Toast.LENGTH_SHORT).show()
            }


            when(it.itemId){

                R.id.nav_home -> home()
                R.id.nav_message -> Toast.makeText(applicationContext,"Clicked Message",Toast.LENGTH_SHORT).show()
                R.id.nav_sync -> Toast.makeText(applicationContext,"Clicked Sync",Toast.LENGTH_SHORT).show()
                R.id.nav_trash -> Toast.makeText(applicationContext,"Clicked Delete",Toast.LENGTH_SHORT).show()
                R.id.nav_setting -> Toast.makeText(applicationContext,"Clicked Setting",Toast.LENGTH_SHORT).show()
                R.id.nav_login ->  login()
                R.id.nav_share -> Toast.makeText(applicationContext, "Clicked Share",Toast.LENGTH_SHORT).show()
                R.id.nav_rate_us -> Toast.makeText(applicationContext,"Clicked Rate us",Toast.LENGTH_SHORT).show()

            }

            true
        }


    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(toggle.onOptionsItemSelected(item)){

            return true
        }

        return super.onOptionsItemSelected(item)
    }

    private fun saveDoctorData() {
        //getting values
        val docName = etdname.text.toString()
        val docEmail = etdemail.text.toString()
        val docPhone = etdcontactnum.text.toString()
        val docHos = etdhospital.text.toString()
        val docAdd = etdaddress.text.toString()
//validation
        if (docName.isEmpty()) {
            etdname.error = "Please enter name"
        }
        if (docEmail.isEmpty()) {
            etdemail.error = "Please enter Email"
        }
        if (docPhone.isEmpty()) {
            etdcontactnum.error = "Please enter Phone Number"
        }
        if (docHos.isEmpty()) {
            etdhospital.error = "Please enter Hospital name"
        }
        if (docAdd.isEmpty()) {
            etdaddress.error = "Please enter Address"
        }else {
            val docId = dbRef.push().key!!

            val doctors = Doctors(docId, docName, docEmail, docPhone, docHos, docAdd)

            dbRef.child(docId).setValue(doctors)
                .addOnCompleteListener {
                    Toast.makeText(this, "Data inserted successfully", Toast.LENGTH_LONG).show()

                    etdname.text.clear()
                    etdemail.text.clear()
                    etdcontactnum.text.clear()
                    etdhospital.text.clear()
                    etdaddress.text.clear()

                }
                .addOnFailureListener { err ->
                    Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
                }


        }

    }
}


