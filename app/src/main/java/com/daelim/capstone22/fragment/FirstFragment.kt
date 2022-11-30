/*
package com.daelim.capstone22.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.viewpager2.widget.ViewPager2
import com.daelim.capstone22.calendar.FirstFragmentStateAdapter
import com.daelim.capstone22.MainActivity
import com.daelim.capstone22.R
import com.daelim.capstone22.`object`.ApiObject
import com.daelim.capstone22.calendar.CalendarAdapter
import com.daelim.capstone22.calendar.CalendarDataAdapter
import com.daelim.capstone22.data.CalendarData
import com.daelim.capstone22.data.CalendarDataResponse
import kotlinx.android.synthetic.main.fragment_calendar.*
import kotlinx.android.synthetic.main.fragment_first.*
import kotlinx.android.synthetic.main.fragment_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate


class FirstFragment : Fragment() {

    var datas = mutableListOf<CalendarData>()
    var calendarIn : CalendarDataResponse ?= null
    var itemCalendarPlusText = view?.findViewById<TextView>(R.id.item_calendar_plus_text)
    var itemCalendarMinusText = view?.findViewById<TextView>(R.id.item_calendar_minus_text)
    private lateinit var calendarDataAdapter: CalendarDataAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    fun initView() {
        val firstFragmentStateAdapter
                = FirstFragmentStateAdapter(activity as MainActivity)
        calendarViewPager.adapter = firstFragmentStateAdapter
        //calendarViewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
            firstFragmentStateAdapter.apply {
                calendarViewPager.setCurrentItem(this.firstFragmentPosition, false)
            }
        }
    }
*/
