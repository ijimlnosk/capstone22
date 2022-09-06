package com.daelim.capstone22

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter

class ListViewAdapter(private val items: MutableList<ListViewItem>): BaseAdapter() {
    override fun getCount(): Int = items.size

    override fun getItem(position: Int): ListViewItem = items[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, view: View?, parent: ViewGroup?): View {
        var convertView = view
        if (convertView == null) convertView = LayoutInflater.from(parent?.context).inflate(R.layout.custom_list_view, parent, false)

        val item: ListViewItem = items[position]
        convertView.custom_text1.text
        convertView.text_sub_title.text = item.subTitle

        return convertView //
    }
}