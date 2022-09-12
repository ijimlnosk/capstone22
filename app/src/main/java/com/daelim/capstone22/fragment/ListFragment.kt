package com.daelim.capstone22.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import com.daelim.capstone22.ListAdapter
import com.daelim.capstone22.ListData
import com.daelim.capstone22.MainActivity
import com.daelim.capstone22.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class ListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val context = context as MainActivity
        val centerlist = resources.getStringArray(R.array.Region2)

        val lv = context.findViewById<ListView>(R.id.list)
        val adapter = ArrayAdapter(context, android.R.layout.simple_list_item_1, centerlist)
        lv.adapter = adapter


    }
}