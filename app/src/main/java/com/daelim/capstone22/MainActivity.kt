package com.daelim.capstone22

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.daelim.capstone22.fragments.CalendarFragment
import com.daelim.capstone22.fragments.ListFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import java.time.LocalDate
import java.util.*

class MainActivity : AppCompatActivity() {

    val TAG : String = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnPre = findViewById<ImageButton>(R.id.img_previ)
        val btnNext = findViewById<ImageButton>(R.id.img_next)
        val tvMonthNum = findViewById<TextView>(R.id.tv_MonthNum)

        val calender:Calendar = Calendar.getInstance()
        val mon = calender.get(Calendar.MONTH)

        btnPre.setOnClickListener{
            tvMonthNum.setText(mon-1)
        }
        btnNext.setOnClickListener {
            tvMonthNum.setText(mon+1)
        }


        val items = mutableListOf<ListViewItem>()

        items.add(ListViewItem("1,000","1,500"))
        items.add(ListViewItem("1,000","1,500"))
        items.add(ListViewItem("1,000","1,500"))

        val adapter = ListViewAdapter(items)
       /* listview.adapter = adapter

        listview.setOnItemClickListener { parent, view, position, l ->
            val item = parent.getItemAtPosition(position) as ListViewItem
            Toast.makeText(this, item.title, Toast.LENGTH_SHORT).show()
        }*/
        var bnv_main = findViewById<FrameLayout>(R.id.bnv_main) as BottomNavigationView

        bnv_main.run {
             setOnItemSelectedListener{
                when(it.itemId){
                    R.id.list_Pay_Bottom -> {
                        val listFragment = ListFragment()
                        supportFragmentManager.beginTransaction().replace(R.id.fl_container,listFragment).commit()
                    }
                    R.id.calendar_Pay_Bottom -> {
                        val calendarFragment = CalendarFragment()
                        supportFragmentManager.beginTransaction().replace(R.id.fl_container,calendarFragment).commit()
                    }
                }
                true
            }
            selectedItemId = R.id.list_Pay_Bottom
        }


    }
}