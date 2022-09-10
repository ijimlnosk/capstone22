package com.daelim.capstone22.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.RelativeLayout
import androidx.fragment.app.ListFragment
import com.daelim.capstone22.MainActivity
import com.daelim.capstone22.R

class ListFragment : ListFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_list,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val context = context as MainActivity
        val centerlist = resources.getStringArray(R.array.region1)

        val lv = context.findViewById<ListView>(R.id.list)
        val adapter = ArrayAdapter(context,android.R.layout.simple_list_item_1, centerlist)
        lv.adapter = adapter

    }
}