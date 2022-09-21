package com.daelim.capstone22.calendar

import android.content.Context
import android.graphics.Typeface
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.daelim.capstone22.R
import kotlinx.android.synthetic.main.list_item_calendar.view.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

//FragmentStateAdapter
// (val cList: List<CalendarVO>): RecyclerView.Adapter<CalendarAdapter.CalendarViewHolder>()
// 높이를 구하는데 필요한 LinearLayout 과 FurangCalender를 사용할 때 필요한 date를 받는다.
class CalendarAdapter(val context: Context, val calendarLayout: LinearLayout, val date: Date): RecyclerView.Adapter<CalendarAdapter.CalendarItemHolder>(){

    private val TAG = javaClass.simpleName
    var dataList: ArrayList<Int> = arrayListOf()

    // furangCalendar을 이용하여 날짜 리스트 세팅
    var furangCalendar: FurangCalendar = FurangCalendar(date)
    init {
        furangCalendar.initBaseCalendar()
        dataList = furangCalendar.dateList
    }

    interface ItemClick{
        fun onClick(view: View, position: Int)
    }
    var itemClick: ItemClick? = null

    override fun onBindViewHolder(holder: CalendarItemHolder, position: Int) {

        // lsit_item_calendar 높이 지정
        val h = calendarLayout.height / 6
        holder.itemView.layoutParams.height = h

        holder?.bind(dataList[position], position, context)
        if (itemClick != null){
            holder?.itemView?.setOnClickListener {
                v -> itemClick?.onClick(v, position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarItemHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_item_calendar,parent,false)
        return CalendarItemHolder(view)
    }

    override fun getItemCount(): Int {
        Log.d("size", dataList.size.toString())
        return dataList.size
    }

    inner class  CalendarItemHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!){

        var itemCalendarDateText: TextView = itemView!!.item_calendar_date_text
        var itemCalendarDotView: View = itemView!!.item_calendar_date_text
        var itemCalendarMinusData: TextView = itemView!!.item_calendar_minus_date_text
        var itemCalendarMinusView: View = itemView!!.item_calendar_minus_date_text
        var itemCalendarPlusData: TextView = itemView!!.item_calendar_plus_date_text
        var itemCalendarPlusView: View = itemView!!.item_calendar_plus_date_text

        fun bind(data: Int, position: Int, context: Context){

            val firstDateindex = furangCalendar.prevTail
            val lastDateIndex = dataList.size - furangCalendar.nextHead -1

            // 날짜 표시
            itemCalendarDateText.setText(data.toString())

            // 오늘 날짜 처리
            var dateString: String = SimpleDateFormat("dd", Locale.KOREA).format(date)
            var dateInt = dateString.toInt()
            if(dataList[position] == dateInt){
                itemCalendarDateText.setTypeface(itemCalendarDateText.typeface, Typeface.BOLD)
            }

            // 현재 월의 1일 이전, 현재 월을 마지막일 이후 값의 텍스트 회색처리
            if (position < firstDateindex || position > lastDateIndex){
                itemCalendarDateText.setTextAppearance(R.style.LightColorTextViewStyle)
                itemCalendarDotView.background = null
                itemCalendarMinusData.setTextAppearance(R.style.transparent)
                itemCalendarMinusView.background = null
                itemCalendarPlusData.setTextAppearance(R.style.transparent)
                itemCalendarPlusView.background = null

                //itemCalendarDotView.setBackgroundResource(R.drawable.lightgray)
            }

        }

    }
}