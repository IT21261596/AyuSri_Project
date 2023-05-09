package com.example.ayusri

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.example.ayusri.location.locusfetch
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class cusloginmain : AppCompatActivity() {

    lateinit var toggle : ActionBarDrawerToggle
    private lateinit var docbtn: Button
    private lateinit var disbtn: Button
    private lateinit var locbtn: Button
    private lateinit var medibtn: Button
    private lateinit var viewEmail:TextView
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cusloginmain)
        disbtn=findViewById(R.id.diseases)
        docbtn=findViewById(R.id.doctor)
        locbtn = findViewById(R.id.locations)
        medibtn = findViewById(R.id.medicines)
        viewEmail = findViewById(R.id.use)

        auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser

        if (currentUser != null) {
            val email = currentUser.email


            viewEmail.text = "$email"


        }
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
        medibtn.setOnClickListener{
            var i = Intent(this, medicufetch::class.java)
            startActivity(i)
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
                Toast.makeText(applicationContext,"Clicked Login", Toast.LENGTH_SHORT).show()
            }

            fun home(){
                var intent = Intent(this,cusloginmain::class.java)
                startActivity(intent)
                Toast.makeText(applicationContext,"Clicked Home", Toast.LENGTH_SHORT).show()
            }

            fun logout(){
                var intent = Intent(this,login::class.java)
                startActivity(intent)
                Toast.makeText(applicationContext,"Clicked Logout", Toast.LENGTH_SHORT).show()
            }


            when(it.itemId){

                R.id.nav_home -> home()
                R.id.nav_message -> Toast.makeText(applicationContext,"Clicked Message", Toast.LENGTH_SHORT).show()
                R.id.nav_sync -> Toast.makeText(applicationContext,"Clicked Sync", Toast.LENGTH_SHORT).show()
                R.id.nav_trash -> Toast.makeText(applicationContext,"Clicked Delete", Toast.LENGTH_SHORT).show()
                R.id.nav_setting -> Toast.makeText(applicationContext,"Clicked Setting", Toast.LENGTH_SHORT).show()
                R.id.nav_login ->  login()
                R.id.nav_share -> Toast.makeText(applicationContext, "Clicked Share", Toast.LENGTH_SHORT).show()
                R.id.nav_rate_us -> Toast.makeText(applicationContext,"Clicked Rate us", Toast.LENGTH_SHORT).show()
                R.id.nav_logout -> logout()

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

}
