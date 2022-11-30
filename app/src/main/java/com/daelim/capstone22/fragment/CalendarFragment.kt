package com.daelim.capstone22.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.daelim.capstone22.*
import com.daelim.capstone22.`object`.ApiObject
import com.daelim.capstone22.adapter.CalendarAdapterTest
import com.daelim.capstone22.adapter.CalendarDataAdapter
import com.daelim.capstone22.data.CalendarData
import com.daelim.capstone22.data.CalendarDataResponse
import kotlinx.android.synthetic.main.fragment_calendar.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.DecimalFormat
import java.time.LocalDate
import java.util.*
import kotlin.collections.ArrayList

//(index:Int)
class CalendarFragment() : Fragment(R.layout.fragment_calendar){
    private lateinit var calendarDataAdapter: CalendarDataAdapter
    private val datas = mutableListOf<CalendarData>()
    var calendarIn : CalendarDataResponse? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_calendar,container,false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnCalLeft = view.findViewById<Button>(R.id.btn_calLeft)
        val btnCalRight = view.findViewById<Button>(R.id.btn_calRight)
        val txCalPlusResult = view.findViewById<TextView>(R.id.tvCalPlusResult)
        val txCalMinusResult = view.findViewById<TextView>(R.id.tvCalMinusResult)
        val moneySplit = DecimalFormat("#,###")

        var now = LocalDate.now()
        var year = now.year
        var month = now.monthValue

        datas.clear()
        ApiObject.calendarService().getTran(
            year.toString(),
            month.toString(),
            par = mapOf(
                "createdAt" to "",
                "transactionType" to "",
                "amount" to ""
            )
        ).enqueue(object : Callback<CalendarDataResponse>{
            override fun onResponse(
                call: Call<CalendarDataResponse>,
                response: Response<CalendarDataResponse>
            ) {
                var totalMonth = response.body()?.plusMinus
                txCalPlusResult.text = "수입 :   + "+ moneySplit.format(totalMonth?.get("plus"))
                txCalMinusResult.text = "지출 :   - "+ moneySplit.format(totalMonth?.get("minus"))
            }

            override fun onFailure(call: Call<CalendarDataResponse>, t: Throwable) {
                Log.d("오류",t.message.toString())
            }

        })

        setMonthView()
        btnCalLeft?.setOnClickListener {
            CalendarUtil.selectedDate.add(Calendar.MONTH, -1)
            setMonthView()
        }
        btnCalRight?.setOnClickListener {
            CalendarUtil.selectedDate.add(Calendar.MONTH, +1)
            setMonthView()
        }

    }
    private fun setMonthView(){
        val txHeader = view?.findViewById<TextView>(R.id.txCalHeader)
        val moneySplit = DecimalFormat("#,###")
        txHeader?.text = monthYearFromDate(CalendarUtil.selectedDate)

        var dateList = dateInMonthArray()
        val adapter = CalendarAdapterTest(dateList)
        var manager : RecyclerView.LayoutManager = GridLayoutManager(context,7)
        calendar_view.layoutManager = manager
        calendar_view.adapter = adapter

    }
    private fun monthYearFromDate(calendar: Calendar): String{
        var year = calendar.get(Calendar.YEAR)
        var month = calendar.get(Calendar.MONTH) + 1
        return "$year 년 $month 월"
    }
    private fun dateInMonthArray(): ArrayList<Date>{
        var dateList = ArrayList<Date>()
        var monthCalendar = CalendarUtil.selectedDate.clone() as Calendar

        monthCalendar[Calendar.DAY_OF_MONTH] = 1
        val firstDateOfMonth = monthCalendar[Calendar.DAY_OF_WEEK] -1
        monthCalendar.add(Calendar.DAY_OF_MONTH, -firstDateOfMonth)
        while (dateList.size < 42){
            dateList.add(monthCalendar.time)
            //1일씩 늘린다.
            monthCalendar.add(Calendar.DAY_OF_MONTH, 1)
        }

        return dateList
    }
}