package com.daelim.capstone22.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.daelim.capstone22.FirstFragmentStateAdapter
import com.daelim.capstone22.MainActivity
import com.daelim.capstone22.R
import kotlinx.android.synthetic.main.fragment_first.*


class FirstFragment : Fragment() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initView()
    }
    fun initView(){
        val firstFragmentStateAdapter = FirstFragmentStateAdapter(MainActivity())
        calender_ViewPage_Adapter.adapter = firstFragmentStateAdapter
        calender_ViewPage_Adapter.orientation = ViewPager2.ORIENTATION_VERTICAL

    }
}