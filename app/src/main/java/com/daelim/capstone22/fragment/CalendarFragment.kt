package com.daelim.capstone22.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.daelim.capstone22.*
import com.daelim.capstone22.VO.CalendarVO
import com.daelim.capstone22.databinding.FragmentCalendarBinding
import kotlinx.android.synthetic.main.fragment_calendar.*
import kotlinx.android.synthetic.main.fragment_calendar.view.*
import java.time.DayOfWeek
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAdjusters
import java.util.*

class CalendarFragment : Fragment() {

    private var _binding: FragmentCalendarBinding? = null
    private val binding get() = _binding!!

    lateinit var calendarAdapter: CalendarAdapter
    private var calendarList = ArrayList<CalendarVO>()

    companion object {
        fun newInstance() = CalendarFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCalendarBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var week_day: Array<String> = resources.getStringArray(R.array.calendar_day)

        calendarAdapter = CalendarAdapter(calendarList)

        calendarList.apply {
            val dateFormat = DateTimeFormatter.ofPattern("dd").withLocale(Locale.forLanguageTag("ko"))
            val monthFormat = DateTimeFormatter.ofPattern("yyyy년 MM월").withLocale(Locale.forLanguageTag("ko"))

            val localDate = LocalDateTime.now().format(monthFormat)
            binding.textYearMonth.text = localDate

            var preSunday: LocalDateTime = LocalDateTime.now().with(TemporalAdjusters.previous(DayOfWeek.SUNDAY))

            for (i in 0..6) {
                Log.d("날짜만", week_day[i])

                calendarList.apply {
                    add(CalendarVO(preSunday.plusDays(i.toLong()).format(dateFormat), week_day[i]))
                }
                Log.d("저번 주 일요일 기준으로 시작!", preSunday.plusDays(i.toLong()).format(dateFormat))
            }
        }
        binding.weekRecycler.adapter = calendarAdapter
        binding.weekRecycler.layoutManager = GridLayoutManager(context, 7)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    /*private lateinit var binding: FragmentCalenderBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCalenderBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val year = requireArguments().getLong("year")
        val month = requireArguments().getLong("month")
        binding.calenderView.setYearMonth
    }
*/
    /*private val TAG = javaClass.simpleName
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
    }*/
}