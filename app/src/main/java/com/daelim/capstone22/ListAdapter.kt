package com.daelim.capstone22

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.daelim.capstone22.data.ListData


class ListAdapter(val context: Context, val listData: ArrayList<ListData>) : BaseAdapter() {
    override fun getCount(): Int {
        return listData.size
    }

    override fun getItem(position: Int): Any {
        return listData[position]
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getView(position: Int, converView: View, parent: ViewGroup?): View {

        val view: View = LayoutInflater.from(context).inflate(androidx.appcompat.R.id.list_item,null)

        val tvBreakD = view.findViewById<TextView>(R.id.tvBD)
        val tvMoney  = view.findViewById<TextView>(R.id.tvPay)
        val tvcateG = view.findViewById<TextView>(R.id.tvCate)

        val initData = listData[position]

        tvBreakD.text = initData.breakdown
        tvMoney.text = initData.pay
        tvcateG.text = initData.name

        return view
    }


}