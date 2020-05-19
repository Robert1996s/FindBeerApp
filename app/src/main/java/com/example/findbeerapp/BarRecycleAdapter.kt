package com.example.findbeerapp

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BarRecycleAdapter(private val context : Context, private val bars: List<Bar>) : RecyclerView.Adapter<BarRecycleAdapter.ViewHolder>() {


    private val layoutInflater = LayoutInflater.from(context)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = layoutInflater.inflate(R.layout.list_item_bar, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount() = bars.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val bar = bars[position]
    }


    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val textViewName = itemView.findViewById<TextView>(R.id.textName)
        val textViewPrice = itemView.findViewById<TextView>(R.id.textPrice)



    }



}