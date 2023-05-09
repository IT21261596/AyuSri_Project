package com.example.ayusri

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ayusri.Adapters.DisAdapter
import com.example.ayusri.Adapters.MediAdapter
import com.example.ayusri.Models.Disease
import com.example.ayusri.Models.Medicine
import com.google.firebase.database.*

class MedecineFetch : AppCompatActivity() {
    private lateinit var DisRecyclerView: RecyclerView
    private lateinit var tvLoadingData: TextView
    private lateinit var medilist: ArrayList<Medicine>
    private lateinit var adapter: DisAdapter

    private lateinit var dbRef: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medecine_fetch)
        var addisbtn=findViewById<Button>(R.id.addissub)
        addisbtn.setOnClickListener{
            openInsertDialog()
        }
        DisRecyclerView = findViewById(R.id.DisRecyclerView)
        DisRecyclerView.layoutManager = LinearLayoutManager(this)
        DisRecyclerView.setHasFixedSize(true)
        tvLoadingData = findViewById(R.id.tvLoadingData)

        medilist = arrayListOf<Medicine>()

        getDisease()
    }
    private fun getDisease() {
        DisRecyclerView.visibility = View.GONE
        tvLoadingData.visibility = View.VISIBLE

        dbRef = FirebaseDatabase.getInstance().getReference("Medicines")

        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                medilist.clear()
                if (snapshot.exists()) {
                    for (disSnap in snapshot.children) {
                        val disData = disSnap.getValue(Medicine::class.java)
                        medilist.add(disData!!)
                    }
                    val mAdapter = MediAdapter(medilist)
                    DisRecyclerView.adapter = mAdapter

                    mAdapter.setOnItemClickListener(object:MediAdapter.onItemClickListener{
                        override fun onItemClick(position: Int) {
                            val intent = Intent(this@MedecineFetch,Medidetailsactivity::class.java)
                            //put extra
                            intent.putExtra("mediID", medilist[position].mediID)
                            intent.putExtra("mediTopic", medilist[position].mediTopic)
                            intent.putExtra("mediAdd", medilist[position].mediAdd)

                            startActivity(intent)
                        }

                    })
                    DisRecyclerView.visibility = View.VISIBLE
                    tvLoadingData.visibility = View.GONE

                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
    private fun openInsertDialog() {
        val mDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val mDialogView = inflater.inflate(R.layout.activity_addmedicine, null)
        mDialog.setView(mDialogView)

        val disTopic = mDialogView.findViewById<EditText>(R.id.topicmedicine)
        val disAdd = mDialogView.findViewById<EditText>(R.id.prescription)


        val btnAdd = mDialogView.findViewById<Button>(R.id.medicineaddbutton)

        val alertDialog = mDialog.create()
        alertDialog.show()
        dbRef = FirebaseDatabase.getInstance().getReference("Medicines")
        btnAdd.setOnClickListener {
            val empName = disTopic.text.toString()
            val empAge = disAdd.text.toString()


            if (empName.isEmpty()) {
                disTopic.error = "Please enter Topic"
            }
            if (empAge.isEmpty()) {
                disAdd.error = "Please enter Discription"
            }

            else {
                val disId = dbRef.push().key!!

                val employee = Medicine(disId, empName, empAge)

                dbRef.child(disId).setValue(employee)
                    .addOnCompleteListener {
                        Toast.makeText(this, "Data inserted successfully", Toast.LENGTH_LONG).show()

                        disTopic.text.clear()
                        disAdd.text.clear()



                    }.addOnFailureListener { err ->
                        Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
                    }
                alertDialog.dismiss()

            }
        }

    }


}