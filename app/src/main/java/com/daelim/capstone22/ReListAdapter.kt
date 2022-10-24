package com.daelim.capstone22

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.daelim.capstone22.data.ListData
import com.daelim.capstone22.data.ListRequest
import com.daelim.capstone22.data.ListResponse
import kotlinx.android.synthetic.main.list_item.view.*
import org.w3c.dom.Text

class ReListAdapter(private val datas : ArrayList<ListRequest>) :
    RecyclerView.Adapter<ReListAdapter.Holder>() {
    inner class Holder(v : View):RecyclerView.ViewHolder(v){
        private var view : View = v
        fun bind(listener: View.OnClickListener, data: ListRequest){
            val tvName: TextView = itemView.findViewById(R.id.tvName)
            val tvAmount : TextView = itemView.findViewById(R.id.tvAmount)
            val tvCategory : TextView = itemView.findViewById(R.id.tvCategory)
            val tvTransaction : TextView = itemView.findViewById(R.id.tvTransaction)

            view.tvAmount.text = data.amount
            view.tvName.text = data.name
            view.tvCategory.text = data.categoryType
            view.tvTransaction.text = data.transactionType
            view.setOnClickListener(listener)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_list, parent,false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val data = datas[position]
        val listener = View.OnClickListener { it ->
            Toast.makeText(it.context, "클릭: ${data.name}", Toast.LENGTH_SHORT).show()
        }
            holder.apply {
                bind(listener,data)
                itemView.tag = data
            }
        }

    override fun getItemCount(): Int {
        Log.d("dataSize",datas.size.toString())
        return datas.size
    }
}