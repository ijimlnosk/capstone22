package com.daelim.capstone22

import android.view.LayoutInflater
import android.view.View.inflate
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil.inflate
import androidx.recyclerview.widget.RecyclerView
import com.daelim.capstone22.VO.CalendarVO
import com.daelim.capstone22.databinding.ActivityJoinBinding.inflate
import com.daelim.capstone22.databinding.FragmentHomeBinding.inflate
import com.daelim.capstone22.databinding.ItemCalendarListBinding
import com.daelim.capstone22.databinding.ItemCalendarListBinding.inflate
import com.daelim.capstone22.databinding.ListItemBinding.inflate
import kotlinx.android.synthetic.main.item_calendar_list.view.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

//FragmentStateAdapter
class CalendarAdapter(val cList: List<CalendarVO>): RecyclerView.Adapter<CalendarAdapter.CalendarViewHolder>(){

    class CalendarViewHolder(private val binding: ItemCalendarListBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: CalendarVO){
            binding.date.text = item.cl_date
            binding.day.text = item.cl_day

            var today = binding.date.text

            // 오늘 날짜
            val now = LocalDate.now().format(DateTimeFormatter.ofPattern("dd").withLocale(Locale.forLanguageTag("ko")))
            // 오늘 날짜와 캘린더의 오늘 날짜가 같으면 background_color 적용
            if(today == now){
                binding.weekCardview.setBackgroundResource(R.drawable.background_color)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarViewHolder {
        val binding = ItemCalendarListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CalendarViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CalendarViewHolder, position: Int){
        holder.bind(cList[position])
    }
    override fun getItemCount() : Int {
        return cList.size
    }
    /*companion object {
        const val START_POSITION = Int.MAX_VALUE / 2
    }

    constructor(fragmentActivity: MainActivity): super(fragmentActivity)
    constructor(fragment:Fragment):super(fragment)
    constructor(fragmentManager: FragmentManager, lifecycle: Lifecycle):super(fragmentManager,lifecycle)

    override fun getItemCount(): Int {
        return Int.MAX_VALUE
    }

    override fun getItemId(position: Int): Long {
        val cal = Calendar.getInstance()
        var currentYear = cal.get(Calendar.YEAR)
        var currentMonth = cal.get(Calendar.MONTH) + 1

        val move = position - START_POSITION
        val bias = if(move < 0) -1 else 1

        val moveYear = abs(move) / 12 * bias
        val moveMonth = abs(move) % 12 * bias

        currentYear += moveYear

        when {
            (currentMonth + moveMonth) < 1 ->{
                currentMonth = 12 + (currentMonth + moveMonth)
                currentYear--
            }
            (currentMonth + moveMonth) > 12 ->{
                currentMonth = (currentMonth+moveMonth) - 12
                currentYear++
            }
            else ->{
                currentMonth = (currentMonth+moveMonth)
            }
        }
        return (currentYear+100 + currentMonth).toLong()
    }

    override fun createFragment(position: Int): Fragment {
        val itemId = getItemId(position)
        return CalenderFragment().apply {
            arguments = Bundle().apply {
                putLong("year", itemId / 100L)
                putLong("month",itemId % 100L)
            }
        }
    }*/

}