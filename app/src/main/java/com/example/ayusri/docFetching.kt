package com.example.ayusri

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ayusri.Adapters.DocAdapter
import com.example.ayusri.Models.Doctors
import com.google.firebase.database.*

class docFetching : AppCompatActivity() {
    private lateinit var DocRecyclerView: RecyclerView
    private lateinit var tvLoadingData: TextView
    private lateinit var docList: ArrayList<Doctors>
    private lateinit var adapter: DocAdapter
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doc_cusfetch)

        DocRecyclerView = findViewById(R.id.DocRecyclerView)
        DocRecyclerView.layoutManager = LinearLayoutManager(this)
        DocRecyclerView.setHasFixedSize(true)
        tvLoadingData = findViewById(R.id.tvLoadingData)

        docList = arrayListOf<Doctors>()

        getDoctors()
    }

    private fun getDoctors() {
        DocRecyclerView.visibility = View.GONE
        tvLoadingData.visibility = View.VISIBLE

        dbRef = FirebaseDatabase.getInstance().getReference("Doctors")

        dbRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                docList.clear()
                if (snapshot.exists()) {
                    for (docSnap in snapshot.children) {
                        val docData = docSnap.getValue(Doctors::class.java)
                        docList.add(docData!!)
                    }
                    val mAdapter = DocAdapter(docList)
                    DocRecyclerView.adapter = mAdapter

                    mAdapter.setOnItemClickListener(object: DocAdapter.onItemClickListener{
                        override fun onItemClick(position: Int) {
                            val intent = Intent(this@docFetching,DocDetailsActivity::class.java)
                            //put extra
                            intent.putExtra("docID", docList[position].docID)
                            intent.putExtra("docName", docList[position].docName)
                            intent.putExtra("docEmail", docList[position].docEmail)
                            intent.putExtra("docPhone", docList[position].docPhone)
                            intent.putExtra("docHospital", docList[position].docHospital)
                            intent.putExtra("docAddress", docList[position].docAddress)
                            startActivity(intent)
                        }

                    })
                    DocRecyclerView.visibility =View.VISIBLE
                    tvLoadingData.visibility = View.GONE

                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}