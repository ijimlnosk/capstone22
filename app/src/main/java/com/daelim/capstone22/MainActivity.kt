package com.daelim.capstone22

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.daelim.capstone22.adapter.CalendarDataAdapter
import com.daelim.capstone22.data.CalendarData
import com.daelim.capstone22.data.CalendarDataResponse
import com.daelim.capstone22.databinding.ActivityMainBinding
import com.daelim.capstone22.fragment.CalendarFragment
import com.daelim.capstone22.fragment.ListFragment
import com.daelim.capstone22.service.ListService

private const val TAG_List = "list_fragment"
private const val TAG_Cal = "calendar_fragment"
private const val TAG_Home = "home_fragment"

class MainActivity : AppCompatActivity() {

    val TAG : String = "MainActivity"

    lateinit var binding: ActivityMainBinding

    var listIn: ListService? = null

    lateinit var calendarDataAdapter: CalendarDataAdapter
    var resultIn : CalendarDataResponse? = null
    var datas = mutableListOf<CalendarData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 바인딩
        binding=ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        /*var year = LocalDate.now().year
        Log.d("year",year.toString())

        var month = LocalDate.now().monthValue

        ApiObject.listService().getTran(
            year.toString(),
            month.toString(),
            par = mapOf(
                "createdAt" to "",
                "name" to "",
                "amount" to "",
                "categoryName" to "",
                "transactionType" to ""
            )).enqueue(object : Callback<ListDataResponse> {
            override fun onResponse(
                call: Call<ListDataResponse>,
                response: Response<ListDataResponse>
            ) {
                datas.clear()
                var monthData = response.body()?.plusMinus
                var txPlusResult = findViewById<TextView>(R.id.tvCalPlusResult)
                var txMinusResult = findViewById<TextView>(R.id.tvCalMinusResult)
                val moneySplit = DecimalFormat("#,###")
                binding.tvCalPlusResult.text = moneySplit.format(monthData?.get("plus"))
                binding.tvCalMinusResult.text = moneySplit.format(monthData?.get("minus"))
            }
            override fun onFailure(call: Call<ListDataResponse>, t: Throwable) {
                Log.d("오류",t.message.toString())
            }
        })*/

        setNaviFragment(TAG_List,ListFragment())

        // 하단 네비게이션 바인딩
        binding.mainNavi.setOnItemSelectedListener {
            when(it.itemId){
                R.id.BD -> setNaviFragment(TAG_List,ListFragment())
                R.id.cale -> setNaviFragment(TAG_Cal,CalendarFragment())
            }
            true
        }
    }
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
        val firFt = manager.findFragmentByTag(TAG_Cal)
        // 모든 fragment hide
        if(listFt!=null){
            ft.hide(listFt)
        }
        if(firFt!=null){
            ft.hide(firFt)
        }

        // 선택한 항목에 따라 맞는 fragment show
        if(tag == TAG_List){
            if(listFt!=null){
                ft.show(listFt)
            }
        }else if(tag == TAG_Cal){
            if (firFt!=null){
                ft.show(firFt)
            }
        }
        //end
        ft.commitAllowingStateLoss()

    }
}