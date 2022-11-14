package com.daelim.capstone22

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.daelim.capstone22.data.ListData

class ListFragRecylcerAdapter(private val context: MutableList<ListData>) : RecyclerView.Adapter<ListFragRecylcerAdapter.myViewHolder>() {

    var datas = mutableListOf<ListData>()
    var mPosition = 0

    fun getPosition():Int{
        return mPosition
    }
    private fun setPosition(position: Int){
        mPosition = position
    }
    fun addItem(listRequest: ListData){
        datas.add(listRequest)
        notifyDataSetChanged()
    }
    fun removeItem(position: Int){
        if (position>0){
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)
        return ListFragRecylcerAdapter.myViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: ListFragRecylcerAdapter.myViewHolder, position: Int) {
        val item = datas[position]
        holder.itemView.setOnClickListener { view ->
            setPosition(position)
            Toast.makeText(view.context, "$position 아이템 클릭", Toast.LENGTH_SHORT).show()
        }
        holder.itemView.setOnLongClickListener { view ->
            setPosition(position)
            Toast.makeText(view.context,"$position 아이템 롱클릭", Toast.LENGTH_SHORT).show()
            return@setOnLongClickListener true
        }
        holder.apply {
            bind(item)
            itemView.tag = item
        }
    }

    override fun getItemCount(): Int {
        Log.d("DATA SIZE",datas.size.toString())
        return datas.size
    }


    class myViewHolder(v : View) : RecyclerView.ViewHolder(v) {
        private val view: View = v
        private val txName = view.findViewById<TextView>(R.id.tvName)
        private val txAmount = view.findViewById<TextView>(R.id.tvAmount)
        private val txCategoryName = view.findViewById<TextView>(R.id.tvCategory)
        private val txTransactionType = view.findViewById<TextView>(R.id.tvTransaction)
        fun bind(item: ListData) {

            txName.text = item.name
            txAmount.text = item.amount
            txCategoryName.text = item.categoryName
            txTransactionType.text = item.transactionType

        }
    }
}