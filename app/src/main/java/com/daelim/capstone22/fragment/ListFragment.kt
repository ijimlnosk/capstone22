package com.daelim.capstone22.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.daelim.capstone22.*
import com.daelim.capstone22.`object`.ApiObject
import com.daelim.capstone22.data.ListData
import com.daelim.capstone22.data.ListDataResponse
import kotlinx.android.synthetic.main.fragment_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatter.*
import java.time.temporal.ChronoField
import java.util.*

class ListFragment : Fragment(R.layout.fragment_list) {
    val TAG = "ListFragment"

    private lateinit var listFragRecylcerAdapter: ListFragRecylcerAdapter
    private val datas = mutableListOf<ListData>()
    var listIn : ListDataResponse? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list,container,false)
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val txAmount = view?.findViewById<TextView>(R.id.tvAmount)
        val txName = view?.findViewById<TextView>(R.id.tvName)
        val txCategoryName = view?.findViewById<TextView>(R.id.tvCategory)
        val txTransactionType = view?.findViewById<TextView>(R.id.tvTransaction)
        val txHeader = view?.findViewById<TextView>(R.id.txHeader)
        val btnLeft = view?.findViewById<Button>(R.id.btn_left)
        val btnRight = view?.findViewById<Button>(R.id.btn_right)

        val now : LocalDate = LocalDate.now()
        var nYear = now.year
        Log.d("year",nYear.toString())
        var sMonth = now.month
        var nMonth = sMonth.value
        Log.d("month",nMonth.toString())
        btnLeft?.setOnClickListener {
            Log.d("left","클릭")
            nMonth = nMonth.minus(1)
            Log.d("left",nMonth.toString())
            if (nMonth < 0){
                nMonth = 12
                nYear = nYear.minus(1)
            }
            txHeader?.text = nYear.toString() + "년 " + nMonth.toString() + "월"
        }
        btnRight?.setOnClickListener {
            Log.d("right","클릭")
            nMonth = nMonth.plus(1)
            Log.d("left",nMonth.toString())
            if (nMonth > 12){
                nMonth = 1
                nYear = nYear.plus(1)
            }
            txHeader?.text = nYear.toString() + "년 " + nMonth.toString() + "월"
        }
        ApiObject.listService().getTran(
            year = nYear.toString(),
            month = nMonth.toString(),
            par = mapOf(
                "name" to "",
                "amount" to "",
                "categoryName" to "",
                "transactionType" to ""
            )).enqueue(object : Callback<ListDataResponse>{
            override fun onResponse(
                call: Call<ListDataResponse>,
                response: Response<ListDataResponse>
            ) {
                Log.d("성공",response.message())
                listIn = response.body()
                Log.d("response",response.toString())
                Log.d("response", listIn.toString())
                if (response.isSuccessful) {
                    val mList = listIn?.result?.map {
                        val tvtName : String = it.name
                        Log.d("name",it.name)
                        Log.d("amount",it.amount)
                        Log.d("category",it.categoryName)
                        Log.d("transaction",it.transactionType)
                        datas.apply {
                            add(ListData(it.name,it.amount,it.categoryName,it.transactionType))
                        }
                        listFragRecylcerAdapter = ListFragRecylcerAdapter(datas)
                        recycler_view.layoutManager = LinearLayoutManager(activity,RecyclerView.VERTICAL,false)
                        recycler_view.setHasFixedSize(true)
                        recycler_view.addItemDecoration(VerticalItemDecorator(5))
                        recycler_view.adapter = listFragRecylcerAdapter
                        listFragRecylcerAdapter.datas = datas
                        listFragRecylcerAdapter.notifyDataSetChanged()
                    }
                }
            }
            override fun onFailure(call: Call<ListDataResponse>, t: Throwable) {
                Log.d("오류",t.message.toString())
            }

        })
    }

   /* private lateinit var binding : FragmentListBinding
    private val reListAdapter = ReListAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fragmentListBinding = FragmentListBinding.bind(view)
        binding = fragmentListBinding

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = reListAdapter(context)
    }*/

    /*override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val intent = Intent(context, ListActivity::class.java)
        startActivity(intent)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list,container,false)
    }*/
    /*lateinit var reListAdapter: ReListAdapter
    private val datas = mutableListOf<ListRequest>()
    var listIn:ListResponse? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentListBinding.inflate(inflater, container, false)
        reListAdapter = ReListAdapter()
        binding.recyclerView.adapter = reListAdapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initList()
    }

    private fun initList() {
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

                    datas.clear()
                    datas.apply {
                        add(ListRequest(name = "", amount = "", categoryType = "", transactionType = ""))

                        reListAdapter.datas = datas
                        reListAdapter.notifyDataSetChanged()
                    }

                }
            }

            override fun onFailure(call: Call<ListResponse>, t: Throwable) {
                Log.e("body", t.message.toString())
            }

        })
    }*/
    /*private var recyclerAdapter: RecyclerView.Adapter<ReListAdapter.Holder> ?= null
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
*/
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