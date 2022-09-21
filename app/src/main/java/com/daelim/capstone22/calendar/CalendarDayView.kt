package com.daelim.capstone22.calendar

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import java.util.*
import kotlin.random.Random
import kotlin.random.nextInt

class CalendarDayView : View {

    private fun dayRects() = List<Rect>(42) {_ -> Rect()}
    private fun dayPains() = List<Paint>(42) { _ -> Paint().apply {
        color = when(Random.nextInt(0 until 3)){
            0 -> Color.RED
            1 -> Color.BLUE
            else -> Color.GREEN
        }
        textSize - 30f
    }}
    private val dates = MutableList<Int>(32){ i -> -1}

    private var year = Calendar.getInstance().get(Calendar.YEAR)
    private var month = Calendar.getInstance().get(Calendar.MONTH) +1

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        val widthAspect = w / 7
        val heightAspect = h / 6

        var stackWidth = 0
        var stackHeight = 0
        for(i in 0 until 7){
            stackWidth = 0
            for(j in 0 until 7){
                dayRects()[i * 7 + j].set(stackWidth, stackHeight, stackWidth + widthAspect, stackHeight + heightAspect)
                stackWidth += widthAspect
            }
            stackHeight += heightAspect
        }
    }
    fun setYearMonth(year: Int, month: Int){
        this.year = year
        this.month = month

        val cal = Calendar.getInstance()
        cal.set(Calendar.YEAR, year)
        cal.set(Calendar.MONTH,month)
        cal.set(Calendar.DATE, 0)
        val dayOfMonth = cal.get(Calendar.DAY_OF_MONTH)
        cal.set(Calendar.DATE,1)
        val dayOfWeek = cal.get(Calendar.DAY_OF_WEEK)

        cal.set(Calendar.MONTH, month -1)
        cal.set(Calendar.DATE,0)
        var prevMonthLastDate = cal.get(Calendar.DAY_OF_MONTH)

        for(i in dayOfWeek-2 downTo 0){
            dates[i]= prevMonthLastDate--;
            dayPains()[i].color = Color.GRAY
        }

        var date = 1
        for(i in dayOfWeek-1 until dayOfWeek+dayOfMonth-1){
            dates[i] = date++
            dayPains()[i].color = when{
                (i % 7 == 0) -> Color.RED
                (i % 7 == 6) -> Color.BLUE
                else -> Color.BLACK
            }
        }

        var nextMonthDate = 1
        for(i in dayOfWeek+dayOfMonth-1 until 42){
            dates[i] = nextMonthDate++
            dayPains()[i].color = Color.GRAY
        }

        invalidate()
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.let {
            for(i in 0 until 6 ){
              for(j in 0 until 7){
                it.drawText(
                    dates[i * 7 + j].toString(),
                    dayRects()[i * 7 + j].left.toFloat(),
                    dayRects()[i * 7 +j].bottom.toFloat(),
                    dayPains()[i * 7 + j])
              }
            }
        }
    }

}