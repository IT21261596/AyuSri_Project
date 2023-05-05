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
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth

class login : AppCompatActivity() {

    lateinit var toggle : ActionBarDrawerToggle
    private lateinit var auth:FirebaseAuth
    lateinit var signin:Button
    lateinit var Email:EditText
    lateinit var Password:EditText
    lateinit var Register:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        auth= FirebaseAuth.getInstance()

        signin = findViewById(R.id.sigin)
        Email = findViewById(R.id.lemail)
        Password = findViewById(R.id.lpassword)
        Register = findViewById(R.id.ccreate)

        Register.setOnClickListener {
            var intent =Intent(this,createaccount::class.java)
            startActivity(intent)
            finish()
        }

        //Login
        signin.setOnClickListener {
            if(checking()){
                val email=Email.text.toString()
                val password= Password.text.toString()
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            var intent =Intent(this,cusloginmain::class.java)
                            intent.putExtra("email",email)
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(this, "Wrong Details", Toast.LENGTH_LONG).show()
                        }
                    }
            }
            else{
                Toast.makeText(this,"Enter the Details",Toast.LENGTH_LONG).show()
            }
        }

        val drawerLayout : DrawerLayout = findViewById(R.id.drawerLayout)
        val navView : NavigationView = findViewById(R.id.nav_view)

        toggle = ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        var Ccrbtn = findViewById<Button>(R.id.ccreate)

        Ccrbtn.setOnClickListener{
            var intent =  Intent(this,createaccount::class.java)
            startActivity(intent)
        }


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

    private fun checking():Boolean
    {
        if(Email.text.toString().trim{it<=' '}.isNotEmpty()
            && Password.text.toString().trim{it<=' '}.isNotEmpty())
        {
            return true
        }
        return false
    }

}
