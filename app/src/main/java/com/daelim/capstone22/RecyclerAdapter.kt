package com.daelim.capstone22

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.daelim.capstone22.data.ListRequest
import com.daelim.capstone22.databinding.ActivityJoinBinding.inflate
import com.daelim.capstone22.databinding.ActivityLodingBinding.inflate
import com.daelim.capstone22.fragment.ListFragment
import kotlinx.android.synthetic.main.list_item.*
import kotlinx.android.synthetic.main.list_item.view.*

class RecyclerAdapter(private val datas : ArrayList<ListRequest>) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>(){
    class ViewHolder(v : View) : RecyclerView.ViewHolder(v) {
        private var view : View = v
        fun bind(listener: View.OnClickListener ,item: ListRequest){
            view.tvAmount.text = item.amount
            view.tvName.text = item.name
            view.tvCategory.text = item.categoryType
            view.tvTransaction.text = item.transactionType
            view.setOnClickListener(listener)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
        val inflaterView = LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)
        return RecyclerAdapter.ViewHolder(inflaterView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = datas[position]
        val listener = View.OnClickListener { it ->
            Toast.makeText(it.context, "클릭"+data.copy(),Toast.LENGTH_SHORT).show()
        }
        holder.apply {
            bind(listener, data)
            itemView.tag = data
        }
    }
    override fun getItemCount(): Int {
        Log.d("dataSize",datas.size.toString())
        return datas.size
    }

}
