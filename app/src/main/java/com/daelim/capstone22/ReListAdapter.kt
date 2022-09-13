package com.daelim.capstone22

import android.content.Context
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ReListAdapter(private val context: Context) : RecyclerView.Adapter<ReListAdapter.ViewHolder>(){

    var datas = mutableListOf<ListData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return holder.bind(datas[position])
    }


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){

        private val tvBD = itemView.findViewById<TextView>(R.id.tvBD)
        private val tvPay = itemView.findViewById<TextView>(R.id.tvPay)
        private val tvCate = itemView.findViewById<TextView>(R.id.tvCate)

        fun bind(item: ListData){
            tvBD.text = item.breakdown
            tvPay.text = item.pay
            tvCate.text = item.name
        }

    }

}