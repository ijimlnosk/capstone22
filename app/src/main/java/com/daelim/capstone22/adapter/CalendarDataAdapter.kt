package com.daelim.capstone22.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.daelim.capstone22.R
import com.daelim.capstone22.data.CalendarData
import kotlinx.android.synthetic.main.list_item_calendar.view.*

class CalendarDataAdapter(private val context: MutableList<CalendarData>) : RecyclerView.Adapter<CalendarDataAdapter.myViewHolder>() {

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
        private var itemCalendarPlusText: TextView = view.item_calendar_plus_text
        private var itemCalendarMinusText: TextView = view.item_calendar_minus_text
        fun bind(item: CalendarData) {
            itemCalendarPlusText.text = item.amount
            itemCalendarMinusText.text = item.amount
        }
    }
}
