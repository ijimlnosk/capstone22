package com.daelim.capstone22.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.daelim.capstone22.*
import com.daelim.capstone22.calendar.CalendarAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_calendar.*
import kotlinx.android.synthetic.main.fragment_calendar.view.*
import java.text.SimpleDateFormat
import java.util.*


class CalendarFragment(index: Int) : Fragment() {
    private val TAG = javaClass.simpleName
    lateinit var mContext: Context
    lateinit var mActivity: MainActivity

    var pageIndex = index
    lateinit var currentDate: Date

    lateinit var calender_year_month_text: TextView
    lateinit var calender_layout: LinearLayout
    lateinit var calender_view: RecyclerView
    lateinit var calendarAdapter: CalendarAdapter

    companion object {
        var instance: CalendarFragment? = null
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is MainActivity){
            mContext = context
            mActivity = activity as MainActivity
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        instance = this
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_calendar, container, false)
        initView(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initCalendar()
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    fun initView(view: View){

        pageIndex -= (Int.MAX_VALUE/2)
        Log.e(TAG,"CalenderIndex: $pageIndex")
        calender_year_month_text = view.calendar_year_month_text
        calender_layout = view.calendar_layout
        calender_view = view.calendar_view
        // 날짜
        val date = Calendar.getInstance().run{
            add(Calendar.MONTH, pageIndex)
            time
        }
        currentDate = date
        Log.e(TAG,"$date")
        // 포맷
        var datetime: String = SimpleDateFormat(mContext.getString(R.string.calendar_year_month_format),
            Locale.KOREA).format(date.time)
        calender_year_month_text.setText(datetime)
    }

    fun initCalendar(){
        calendarAdapter = CalendarAdapter(mContext, calendar_layout, currentDate)
        calendar_view.adapter = calendarAdapter
        calender_view.layoutManager = GridLayoutManager(mContext, 7, GridLayoutManager.VERTICAL, false)
        calendar_view.setHasFixedSize(true)
        calendarAdapter.itemClick = object :
        CalendarAdapter.ItemClick{
            override fun onClick(view: View, position: Int){
                val firstDateIndex = calendarAdapter.dataList.indexOf(1)
                val lastDateIndex = calendarAdapter.dataList.lastIndexOf(calendarAdapter.furangCalendar.currentMaxDate)
                if(position < firstDateIndex || position > lastDateIndex){
                    return
                }
                val day = calendarAdapter.dataList[position].toString()
                val date = "${calendar_year_month_text.text}${day}일"
                Log.d(TAG, "$date")
                val mainTab = mActivity.mainNavi
                //mainTab.setScrollPosition(1, 0f, true)
                val mainViewPager = mActivity.main_pager
                mainViewPager.currentItem = 1
                RoutineDateLiveData.getInstance().getLiveProgress().value = date
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        instance = null
    }
}