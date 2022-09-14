package com.daelim.capstone22

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.daelim.capstone22.databinding.ActivityMainBinding
import com.daelim.capstone22.fragment.CalenderFragment
import com.daelim.capstone22.fragment.ListFragment

private const val TAG_List = "list_fragment"
private const val TAG_Calender = "calender_fragment"

class MainActivity : AppCompatActivity() {

    val TAG : String = "MainActivity"

    lateinit var binding: ActivityMainBinding

    /*val datas = mutableListOf<ListData>()*/


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //initRecycler()

        // 바인딩
        binding=ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setFragment(ListFragment())

       /* binding.btnList.setOnClickListener{
            switchFragment()
        }*/

        // 하단 네비게이션 바인딩
        binding.mainNavi.setOnItemSelectedListener {
            when(it.itemId){
                R.id.BD -> setNaviFragment(TAG_List,ListFragment())
                R.id.cale -> setNaviFragment(TAG_Calender,CalenderFragment())
            }
            true
        }

        // 기존 바인딩
        /*binding.btnList.setOnClickListener {
            setListFragment()
        }
        binding.btnCal.setOnClickListener {
            setCalFragment()
        }*/
    }
    // 리스트
   /* private fun setListFragment(){
        val transaction = supportFragmentManager.beginTransaction().add(R.id.frameLayout,ListFragment()).addToBackStack(null)
        transaction.commit()
    }
    private fun setCalFragment(){
        val transaction = supportFragmentManager.beginTransaction().add(R.id.frameLayout,CalenderFragment()).addToBackStack(null)
        transaction.commit()
    }*/
    // start fragment
    fun setFragment(f: ListFragment){
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.frameLayout,f)
        ft.commit()
    }
    // 네비 프래그먼트 컨트롤
    fun setNaviFragment(tag: String, fragment: Fragment){
        val manager: FragmentManager = supportFragmentManager
        val ft: FragmentTransaction = manager.beginTransaction()

        // transaction에 tag로 전달된 fragment가 없을 경우 add
        if(manager.findFragmentByTag(tag) == null){
            ft.add(R.id.frameLayout,fragment,tag)
        }

        // manager에 add되어있는 fragment들을 변수로 할당
        val listFt = manager.findFragmentByTag(TAG_List)
        val calFt = manager.findFragmentByTag(TAG_Calender)
        // 모든 fragment hide
        if(listFt!=null){
            ft.hide(listFt)
        }
        if(calFt!=null){
            ft.hide(calFt)
        }

        // 선택한 항목에 따라 맞는 fragment show
        if(tag == TAG_List){
            if(listFt!=null){
                ft.show(listFt)
            }
        }else if(tag == TAG_Calender){
            if (calFt!=null){
                ft.show(calFt)
            }
        }
        //end
        ft.commitAllowingStateLoss()

    }

    /*private fun switchFragment(){
       val transaction = supportFragmentManager.beginTransaction()
       when(flag){
           0->{
               transaction.add(R.id.frameLayout,ListFragment())
               flag=1
           }
           1->{
               transaction.add(R.id.frameLayout,CalenderFragment())
               flag=2
           }
           2->{
               transaction.add(R.id.frameLayout,ListFragment())
               flag=1
           }
       }
       transaction.addToBackStack(null)
       transaction.commit()
   }*/


    /*private fun initRecycler(){
        val listAdapter = ReListAdapter(this)
        relist.adapter = listAdapter

        datas.apply {
            add(ListData(breakdown = "김진솔", pay = "-5,800", name = "이체"))
            add(ListData(breakdown = "김진솔", pay = "-5,800", name = "이체"))
            add(ListData(breakdown = "김진솔", pay = "-5,800", name = "이체"))
            add(ListData(breakdown = "김진솔", pay = "-5,800", name = "이체"))
            add(ListData(breakdown = "김진솔", pay = "-5,800", name = "이체"))
            add(ListData(breakdown = "김진솔", pay = "-5,800", name = "이체"))

            listAdapter.datas = datas
            listAdapter.notifyDataSetChanged()
        }

    }*/

    /*lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnPre = findViewById<ImageButton>(R.id.img_previ)
        val btnNext = findViewById<ImageButton>(R.id.img_next)
        val tvMonthNum = findViewById<TextView>(R.id.tv_MonthNum)

        val calender:Calendar = Calendar.getInstance()
        val mon = calender.get(Calendar.MONTH)

        *//*btnPre.setOnClickListener{
            tvMonthNum.setText(mon-1)
        }
        btnNext.setOnClickListener {
            tvMonthNum.setText(mon+1)
        }*//*
        // listView
       *//* val items = mutableListOf<ListViewItem>()

        items.add(ListViewItem("자판기","커피","-1,500"))
        items.add(ListViewItem("편의점","담배","-4,500"))
        items.add(ListViewItem("편의점","음료","-2,000"))

        val adapter = ListViewAdapter(items)
        listView.adapter = adapter

        listView.setOnItemClickListener { parent, view, position, l ->
            val item = parent.getItemAtPosition(position) as ListViewItem
            Toast.makeText(this, item.title, Toast.LENGTH_SHORT).show()
        }*//*

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
        *//*var bnv_main = findViewById<FrameLayout>(R.id.bnv_main) as BottomNavigationView

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
        }*//*
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
                fragTransaction.replace(R.id.fl_container,fragment)
            }
        }
        else if(tag == TAG_Calender){
            if(calendarFragment != null){
                fragTransaction.show(calendarFragment)
                Toast.makeText(this,"캘린더",Toast.LENGTH_SHORT).show()
            }
        }
        fragTransaction.commitAllowingStateLoss()
    }*/
}