package com.daelim.capstone22

import android.content.Context
import android.graphics.Typeface
import android.icu.text.Transliterator
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil.inflate
import androidx.recyclerview.widget.RecyclerView
import com.daelim.capstone22.VO.CalendarVO
import com.daelim.capstone22.databinding.ActivityJoinBinding.inflate
import com.daelim.capstone22.databinding.FragmentHomeBinding.inflate
import com.daelim.capstone22.databinding.ItemCalendarListBinding
import com.daelim.capstone22.databinding.ItemCalendarListBinding.inflate
import com.daelim.capstone22.databinding.ListItemBinding.inflate
import kotlinx.android.synthetic.main.item_calendar_list.view.*
import kotlinx.android.synthetic.main.list_item_calendar.view.*
import org.w3c.dom.Text
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
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
        return dataList.size
    }

    inner class  CalendarItemHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!){

        var itemCalendarDateText: TextView = itemView!!.item_calendar_date_text
        var itemCalendarDotView: View = itemView!!.item_calendar_date_text

        fun bind(date: Int, position: Int, context: Context){

            val firstDateindex = furangCalendar.prevTail
            val lastDateIndex = dataList.size - furangCalendar.nextHead -1

            // 날짜 표시
            itemCalendarDateText.setText(date.toString())

            // 오늘 날짜 처리
            var dateString: String = SimpleDateFormat("dd", Locale.KOREA).format(date)
            var dateInt = dateString.toInt()
            if(dataList[position] == dateInt){
                itemCalendarDateText.setTypeface(itemCalendarDateText.typeface, Typeface.BOLD)
            }

            // 현재 월의 1일 이전, 현ㄹ재 월을 마지막일 이후 값의 텍스트 회색처리
            if (position < firstDateindex || position > lastDateIndex){
                itemCalendarDateText.setTextAppearance(R.color.teal_200)
                itemCalendarDotView.background = null
            }

        }

    }

    /*class CalendarViewHolder(private val binding: ItemCalendarListBinding) : RecyclerView.ViewHolder(binding.root){
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
    }*/
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