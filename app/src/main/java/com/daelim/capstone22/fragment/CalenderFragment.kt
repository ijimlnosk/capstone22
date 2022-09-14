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
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.daelim.capstone22.CalenderAdapter
import com.daelim.capstone22.FirstFragmentStateAdapter
import com.daelim.capstone22.MainActivity
import com.daelim.capstone22.R
import kotlinx.android.synthetic.main.fragment_calender.view.*
import java.text.SimpleDateFormat
import java.util.*

class CalenderFragment : Fragment() {

    private val TAG = javaClass.simpleName
    lateinit var mContext: Context

    var pageIndex = 0
    lateinit var currentDate: Date

    lateinit var calender_year_month_text: TextView
    lateinit var calender_layout: LinearLayout
    lateinit var calender_view: RecyclerView
    lateinit var calenderAdapter: CalenderAdapter

    companion object {
        var instance: CalenderFragment? = null
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is MainActivity){
            mContext = context
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
        val view = inflater.inflate(R.layout.fragment_calender, container, false)
        initView(view)
        return view
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
        Log.e(TAG,"%date")
        // 포맷
        var dateTime: String = SimpleDateFormat(
            mContext.getString(R.string.calender_year_month_format),Locale.KOREA).format(date.time)
        calender_year_month_text.setText(dateTime)
    }

    override fun onDestroy() {
        super.onDestroy()
        instance = null
    }
}