package com.daelim.capstone22.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.daelim.capstone22.*
import com.daelim.capstone22.`object`.ApiObject
import com.daelim.capstone22.`object`.ApiObject.listService
import com.daelim.capstone22.data.ListData
import com.daelim.capstone22.data.ListResponse
import com.daelim.capstone22.data.ListRequest
import com.daelim.capstone22.data.SigninResponse
import com.daelim.capstone22.databinding.FragmentHomeBinding
import com.daelim.capstone22.databinding.FragmentListBinding
import com.daelim.capstone22.service.ListService
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.android.synthetic.main.fragment_list.view.*
import kotlinx.android.synthetic.main.list_item.*
import kotlinx.android.synthetic.main.list_item.view.*
import org.w3c.dom.Text
import retrofit2.*

class ListFragment : Fragment() {

    private lateinit var recyclerAdapter: RecyclerView.Adapter<ReListAdapter.Holder>? = null
    var listIn:ListResponse? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater!!.inflate(R.layout.fragment_list,container,false)
        val recyclerView: RecyclerView = view.findViewById(R.id.recycler_view)

        recyclerAdapter = ReListAdapter()

        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val txAmount = view?.findViewById<TextView>(R.id.tvAmount)
        val txName = view?.findViewById<TextView>(R.id.tvName)
        val txCate = view?.findViewById<TextView>(R.id.tvCategory)
        val txTra = view?.findViewById<TextView>(R.id.tvTransaction)

        var txtAmount = txAmount?.text.toString()
        var txtName = txName?.text.toString()
        var txtCategory = txCate?.text.toString()
        var txtTransaction = txTra?.text.toString()

        listService.requestBodyList(ListRequest(name = txtName,
            amount = txtAmount,
            categoryType = txtCategory,
            transactionType = txtTransaction)).enqueue(object : Callback<ListResponse>{
            override fun onResponse(
                call: Call<ListResponse>,
                response: Response<ListResponse>
            ) {
                listIn = response.body()
                if (listIn?.result.equals("성공")) {
                    Log.d("리스트 데이터", "result : " + listIn?.result)

                }
            }

            override fun onFailure(call: Call<ListResponse>, t: Throwable) {
                Log.e("body", t.message.toString())
            }

        })
    }

    /*private lateinit var binding: FragmentListBinding
    private lateinit var reListAdapter: ReListAdapter

    var listIn:ListResponse? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fragmentListBinding = FragmentListBinding.bind(view)
        binding = fragmentListBinding
        binding.recyclerView.adapter = reListAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.setHasFixedSize(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val txAmount = view?.findViewById<TextView>(R.id.tvAmount)
        val txName = view?.findViewById<TextView>(R.id.tvName)
        val txCate = view?.findViewById<TextView>(R.id.tvCategory)
        val txTra = view?.findViewById<TextView>(R.id.tvTransaction)

        var txtAmount = txAmount?.text.toString()
        var txtName = txName?.text.toString()
        var txtCategory = txCate?.text.toString()
        var txtTransaction = txTra?.text.toString()

        listService.requestBodyList(ListRequest(name =txtName,
            amount = txtAmount,
            categoryType = txtCategory,
            transactionType = txtTransaction))
            .enqueue(object : Callback<ListResponse>{
            override fun onResponse(call: Call<ListResponse>, response: Response<ListResponse>) {
                listIn = response.body()
                if (listIn?.result.equals("성공")){
                    Log.d("리스트 내역",listIn!!.message.toString())
                }
            }
            override fun onFailure(call: Call<ListResponse>, t: Throwable) {
                Log.e("LOGIN", t.message.toString())
            }
        })
        return view
    }*/

}