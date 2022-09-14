package com.daelim.capstone22

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.daelim.capstone22.fragment.CalenderFragment

class FirstFragmentStateAdapter(fragmentActivity: FragmentActivity):FragmentStateAdapter(fragmentActivity) {

    private val pageCount = Int.MAX_VALUE
    val firstFragmentPostion = Int.MAX_VALUE/2

    override fun getItemCount(): Int {
        return Int.MAX_VALUE
    }

    override fun createFragment(position: Int): Fragment {
        val calenderFragment = CalenderFragment()
        calenderFragment.pageIndex = position
        return calenderFragment
    }

}