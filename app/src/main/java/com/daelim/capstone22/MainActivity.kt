package com.daelim.capstone22

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.daelim.capstone22.databinding.ActivityMainBinding
import com.daelim.capstone22.fragments.CalendarFragment
import com.daelim.capstone22.fragments.ListFragment
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_list.*
import java.time.LocalDate
import java.util.*

private const val TAG_List = "list_fragment"
private const val TAG_Calender = "calender_fragment"

class MainActivity : AppCompatActivity() {

    val TAG : String = "MainActivity"

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnPre = findViewById<ImageButton>(R.id.img_previ)
        val btnNext = findViewById<ImageButton>(R.id.img_next)
        val tvMonthNum = findViewById<TextView>(R.id.tv_MonthNum)

        val calender:Calendar = Calendar.getInstance()
        val mon = calender.get(Calendar.MONTH)

        /*btnPre.setOnClickListener{
            tvMonthNum.setText(mon-1)
        }
        btnNext.setOnClickListener {
            tvMonthNum.setText(mon+1)
        }*/
        // listView
       /* val items = mutableListOf<ListViewItem>()

        items.add(ListViewItem("자판기","커피","-1,500"))
        items.add(ListViewItem("편의점","담배","-4,500"))
        items.add(ListViewItem("편의점","음료","-2,000"))

        val adapter = ListViewAdapter(items)
        listView.adapter = adapter

        listView.setOnItemClickListener { parent, view, position, l ->
            val item = parent.getItemAtPosition(position) as ListViewItem
            Toast.makeText(this, item.title, Toast.LENGTH_SHORT).show()
        }*/

        // binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setFragment(TAG_List,ListFragment())

        binding.bnvMain.setOnItemSelectedListener {
            when(it.itemId){
                R.id.list_Pay_Bottom -> setFragment(TAG_List,ListFragment())
                R.id.calendar_Pay_Bottom -> setFragment(TAG_Calender,CalendarFragment())
            }
            true
        }

        // 하단 버튼 바
        /*var bnv_main = findViewById<FrameLayout>(R.id.bnv_main) as BottomNavigationView

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
        }*/
    }
    private fun setFragment(tag: String, fragment: Fragment){
        val manager: FragmentManager = supportFragmentManager
        val fragTransaction = manager.beginTransaction()

        if(manager.findFragmentByTag(tag) == null){
            fragTransaction.add(R.id.fl_container,fragment,tag)
        }
        val listFragment = manager.findFragmentByTag(TAG_List)
        val calendarFragment = manager.findFragmentByTag(TAG_Calender)

        if(listFragment != null){
            fragTransaction.hide(listFragment)
        }
        if(calendarFragment != null){
            fragTransaction.hide(calendarFragment)
        }
        if(tag == TAG_List){
            if(listFragment != null){
                fragTransaction.show(listFragment)
                //fragTransaction.replace(R.id.fl_container,fragment)
            }
        }
        else if(tag == TAG_Calender){
            if(calendarFragment != null){
                fragTransaction.show(calendarFragment)
            }
        }
        fragTransaction.commitAllowingStateLoss()
    }
}