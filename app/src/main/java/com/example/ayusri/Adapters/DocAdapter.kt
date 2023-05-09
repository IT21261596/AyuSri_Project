package com.example.ayusri.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ayusri.Models.Doctors
import com.example.ayusri.R

class DocAdapter (private var DocList:ArrayList<Doctors>):
    RecyclerView.Adapter<DocAdapter.ViewHolder>() {

    private lateinit var mListener: onItemClickListener
    interface onItemClickListener{
        fun onItemClick(position: Int)

    }
    fun setOnItemClickListener(clickListener: onItemClickListener){
        mListener = clickListener
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.doclistcustom,parent,false)
        return ViewHolder(itemView,mListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentDoc = DocList[position]
        holder.tvDoc.text = currentDoc.docName
        holder.tvPhone.text = currentDoc.docPhone
        holder.tvAddress.text = currentDoc.docAddress
        holder.tvEmail.text = currentDoc.docEmail
        holder.tvHos.text = currentDoc.docHospital

    }

    override fun getItemCount(): Int {
         return DocList.size
    }

    class ViewHolder(itemView:View,clickListener: onItemClickListener):RecyclerView.ViewHolder(itemView){
        val tvDoc:TextView = itemView.findViewById(R.id.docname)
        val tvPhone:TextView = itemView.findViewById(R.id.docphone)
        val tvAddress:TextView = itemView.findViewById(R.id.docaddress)
        val tvEmail:TextView = itemView.findViewById(R.id.docemail)
        val tvHos:TextView = itemView.findViewById(R.id.dochospital)

        init {
            itemView.setOnClickListener{
                clickListener.onItemClick(adapterPosition)
            }
        }

    }

}