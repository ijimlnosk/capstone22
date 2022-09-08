package com.daelim.capstone22

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class ListViewAdapter(private val items: MutableList<ListViewItem>): BaseAdapter() {

    override fun getCount(): Int = items.size

    override fun getItem(position: Int): ListViewItem = items[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, view: View?, parent: ViewGroup?): View {
        var convertView = view
        if (convertView == null) {
            convertView = LayoutInflater.from(parent?.context)
                .inflate(R.layout.custom_list_view, parent, false)

        }

        val item: ListViewItem = items[position]

        convertView!!.findViewById<TextView>(R.id.custom_text1).text = item.title
        convertView!!.findViewById<TextView>(R.id.custom_text2).text = item.subTitle
        convertView!!.findViewById<TextView>(R.id.money_text1).text = item.moneyTitle


        return convertView //
    }
}