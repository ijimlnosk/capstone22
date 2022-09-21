package com.daelim.capstone22

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.daelim.capstone22.databinding.ActivityMainBinding
import com.daelim.capstone22.fragment.CalendarFragment
import com.daelim.capstone22.fragment.FirstFragment
import com.daelim.capstone22.fragment.HomeFragment
import com.daelim.capstone22.fragment.ListFragment
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import org.json.JSONObject

private const val TAG_List = "list_fragment"
private const val TAG_First = "first_fragment"
private const val TAG_Home = "home_fragment"

class MainActivity : AppCompatActivity() {

    val TAG : String = "MainActivity"

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 바인딩
        binding=ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setNaviFragment(TAG_List,ListFragment())

        // 하단 네비게이션 바인딩
        binding.mainNavi.setOnItemSelectedListener {
            when(it.itemId){
                R.id.BD -> setNaviFragment(TAG_List,ListFragment())
                R.id.cale -> setNaviFragment(TAG_First,FirstFragment())
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
        val firFt = manager.findFragmentByTag(TAG_First)
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
        }else if(tag == TAG_First){
            if (firFt!=null){
                ft.show(firFt)
            }
        }
        //end
        ft.commitAllowingStateLoss()

    }
}