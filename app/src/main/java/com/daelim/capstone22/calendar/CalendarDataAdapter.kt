package com.daelim.capstone22.calendar

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.daelim.capstone22.R
import com.daelim.capstone22.data.CalendarData
import com.daelim.capstone22.data.ListData

class CalendarDataAdapter(private val context: MutableList<ListData>) : RecyclerView.Adapter<CalendarDataAdapter.myViewHolder>() {

    var datas = mutableListOf<CalendarData>()
    var mPosition = 0

    fun getPosition():Int{
        return mPosition
    }
    private fun setPosition(position: Int){
        mPosition = position
    }
    fun addItem(calendarRequest: CalendarData){
        datas.add(calendarRequest)
        notifyDataSetChanged()
    }
    fun removeItem(position: Int){
        if (position>0){
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.list_item_calendar,parent,false)
        return CalendarDataAdapter.myViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: CalendarDataAdapter.myViewHolder, position: Int) {
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
        private val txPlus = view.findViewById<TextView>(R.id.item_calendar_plus_date_text)
        private val txMinus = view.findViewById<TextView>(R.id.item_calendar_minus_date_text)
        fun bind(item: CalendarData) {
            txPlus.text = item.transactionType
            txMinus.text = item.transactionType
        }
    }
}