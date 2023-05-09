package com.example.ayusri.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ayusri.Models.Medicine
import com.example.ayusri.R

class MediAdapter (private var DisList:ArrayList<Medicine>):
    RecyclerView.Adapter<MediAdapter.ViewHolder>() {

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener {
        fun onItemClick(position: Int)

    }

    fun setOnItemClickListener(clickListener: onItemClickListener) {
        mListener = clickListener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.medilist, parent, false)
        return ViewHolder(itemView, mListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentDoc = DisList[position]
        holder.tvtop.text = currentDoc.mediTopic
        holder.tvdis.text = currentDoc.mediAdd



    }

    override fun getItemCount(): Int {
        return DisList.size
    }

    class ViewHolder(itemView: View, clickListener: onItemClickListener) :
        RecyclerView.ViewHolder(itemView) {
        val tvtop: TextView = itemView.findViewById(R.id.distop)
        val tvdis: TextView = itemView.findViewById(R.id.disdec)


        init {
            itemView.setOnClickListener {
                clickListener.onItemClick(adapterPosition)
            }
        }

    }
}