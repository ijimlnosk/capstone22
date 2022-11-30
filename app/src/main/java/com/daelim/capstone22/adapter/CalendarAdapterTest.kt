package com.daelim.capstone22.adapter

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.daelim.capstone22.CalendarUtil
import com.daelim.capstone22.R
import com.daelim.capstone22.`object`.ApiObject
import com.daelim.capstone22.data.CalendarData
import com.daelim.capstone22.data.CalendarDataResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.DecimalFormat
import java.util.*
import kotlin.collections.ArrayList

class CalendarAdapterTest(private val dateList: ArrayList<Date>)
    :RecyclerView.Adapter<CalendarAdapterTest.ItemViewHolder>() {
    var datas = mutableListOf<CalendarData>()
    var calendarIn : CalendarDataResponse? = null
    private lateinit var calendarDataAdapter : CalendarDataAdapter
    inner class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val dateText = itemView.findViewById<TextView>(R.id.item_calendar_date_text)
        var txMinus = itemView.findViewById<TextView>(R.id.item_calendar_minus_text)
        var txPlus = itemView.findViewById<TextView>(R.id.item_calendar_plus_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_calendar,parent,false)
        return ItemViewHolder(view)
    }

    //데이터 설정
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int,) {
        var monthDate = dateList[holder.adapterPosition]
        var dateCalendar = Calendar.getInstance()
        dateCalendar.time = monthDate
        var iYear = dateCalendar.get(Calendar.YEAR)
        var iMonth = dateCalendar.get(Calendar.MONTH) + 1
        var iDate = dateCalendar.get(Calendar.DAY_OF_MONTH)

        var selectYear = CalendarUtil.selectedDate.get(Calendar.YEAR)
        var selectMonth = CalendarUtil.selectedDate.get(Calendar.MONTH) + 1
        var selectDate = CalendarUtil.selectedDate.get(Calendar.DAY_OF_MONTH)
        val moneySplit = DecimalFormat("#,###")

        var monthCalendar = CalendarUtil.selectedDate.clone() as Calendar

        ApiObject.calendarService().getTran(
            iYear.toString(),
            iMonth.toString(),
            par = mapOf(
                "createdAt" to "",
                "transactionType" to "",
                "amount" to ""
            )
        ).enqueue(object : Callback<CalendarDataResponse> {
            override fun onResponse(
                call: Call<CalendarDataResponse>,
                response: Response<CalendarDataResponse>
            ) {
                datas.clear()
                if (response.isSuccessful) {
                    calendarIn = response.body()
                    val totalOfDate = response.body()?.totalOfDay
                    val totalOfMonth = response.body()?.plusMinus
                    Log.d("totalDate",totalOfDate.toString())
                    if (calendarIn?.result?.isEmpty() == true) {
                        datas.clear()
                        calendarDataAdapter = CalendarDataAdapter(datas)
                        calendarDataAdapter.datas = datas
                        calendarDataAdapter.notifyDataSetChanged() // 데이터 전체 갱신
                    } else {
                        datas.clear()
                        var myCal = calendarIn?.result?.map {
                            var myDate = it.createdAt.substring(8 until 10)
                            var result = 0
                            if (it.transactionType.equals("지출")) {
                                if (myDate.equals(iDate.toString())) {
                                    var dateData = totalOfDate?.get(myDate.toInt())
                                    holder.txMinus.text = "-" + moneySplit.format(dateData?.get("minus"))
                                }
                            } else if (it.transactionType.equals("수입")) {
                                if (myDate.equals(iDate.toString())) {
                                    var dateData = totalOfDate?.get(myDate.toInt())
                                    holder.txPlus.text = "+" + moneySplit.format(dateData?.get("plus"))
                                }
                            }
                        }
                    }
                }
            }

            override fun onFailure(call: Call<CalendarDataResponse>, t: Throwable) {
                Log.d("전송 실패", t.message.toString())
            }

        })

        var dateNo = dateCalendar.get(Calendar.DAY_OF_MONTH)
        holder.dateText.text = dateNo.toString()
        if (iYear == selectYear && iMonth == selectMonth) {
            holder.dateText.setTextColor(Color.BLACK)
            if (selectDate == dateNo) {
                holder.dateText.setBackgroundColor(Color.LTGRAY)
            }
            if ((position + 1) % 7 == 0) {
                holder.dateText.setTextColor(Color.BLUE)
            } else if (position == 0 || position % 7 == 0) {
                holder.dateText.setTextColor(Color.RED)
            }
        }
        else {
            holder.dateText.setTextColor(Color.LTGRAY)
            if ((position + 1) % 7 == 0) {
                holder.dateText.setTextColor(Color.parseColor("#b4ffff"))
            } else if (position == 0 || position % 7 == 0) {
                holder.dateText.setTextColor(Color.parseColor("#ffb4b4"))
            }
        }
        holder.itemView.setOnClickListener {
            var yearmonthDate = "$iYear 년 $iMonth 월 $iDate 일"
            Toast.makeText(holder.itemView.context, yearmonthDate, Toast.LENGTH_SHORT).show()
        }
    }
    override fun getItemCount(): Int {
        Log.d("dateListSize", dateList.size.toString())
        return dateList.size
    }
}