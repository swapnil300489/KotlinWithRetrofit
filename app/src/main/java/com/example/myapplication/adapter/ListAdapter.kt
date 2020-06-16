package com.example.myapplication.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.pojo.DataList

class ListAdapter(val context: Context, var data: List<DataList>) : RecyclerView.Adapter<ListAdapter.ListHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListHolder {
        return ListHolder(LayoutInflater.from(context).inflate(R.layout.policy_item, parent, false))
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ListHolder, position: Int) {

        Log.e("Client ", data.get(position).client)


        holder.initText.text = data.get(position).client.substring(0,1).toUpperCase().toString()
        holder.nameText.text = data.get(position).client
        holder.policyText.text = data.get(position).policy_no.toString()
        holder.date1Text.text = data.get(position).dtc1
        holder.date2Text.text = data.get(position).dtc2

        holder.id_card.setOnClickListener {

            Toast.makeText(context, data.get(position).id.toString(), Toast.LENGTH_LONG).show()

        }

    }

    class ListHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val initText: TextView = itemView.findViewById(R.id.id_policyImg) as TextView
        val nameText: TextView = itemView.findViewById(R.id.id_name) as TextView
        val policyText: TextView = itemView.findViewById(R.id.id_policyNo) as TextView
        val date1Text: TextView = itemView.findViewById(R.id.id_date1) as TextView
        val date2Text: TextView = itemView.findViewById(R.id.id_date2) as TextView
        val id_card  : ConstraintLayout = itemView.findViewById(R.id.id_card) as ConstraintLayout

    }
}