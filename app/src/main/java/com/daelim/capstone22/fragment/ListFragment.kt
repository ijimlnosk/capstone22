package com.daelim.capstone22.fragment

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.daelim.capstone22.*
import com.daelim.capstone22.`object`.ApiObject
import com.daelim.capstone22.adapter.ListFragRecylcerAdapter
import com.daelim.capstone22.data.ListData
import com.daelim.capstone22.data.ListDataResponse
import kotlinx.android.synthetic.main.fragment_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.DecimalFormat
import java.time.LocalDate

class ListFragment : Fragment(R.layout.fragment_list) {
    val TAG = "ListFragment"

    private lateinit var listFragRecylcerAdapter: ListFragRecylcerAdapter
    private val datas = mutableListOf<ListData>()
    var listIn : ListDataResponse? = null
    val img : ImageView? = null

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
        val txDate = view?.findViewById<TextView>(R.id.tvDate)
        val txHeader = view?.findViewById<TextView>(R.id.txHeader)
        val btnLeft = view?.findViewById<Button>(R.id.btn_left)
        val btnRight = view?.findViewById<Button>(R.id.btn_right)

        val now : LocalDate = LocalDate.now()
        var nYear = now.year
        //Log.d("year",nYear.toString())
        var sMonth = now.month
        var nMonth = sMonth.value
        //Log.d("month",nMonth.toString())

        getAPIObject(nYear,nMonth)

        //이전 달
        btnLeft?.setOnClickListener {
            //Log.d("left","클릭")
            nMonth = nMonth.minus(1)
            //Log.d("left",nMonth.toString())
            if (nMonth < 0){
                nMonth = 12
                nYear = nYear.minus(1)
            }
            txHeader?.text = nYear.toString() + "년 " + nMonth.toString() + "월"
            getAPIObject(nYear,nMonth)
        }
        //다음 달
        btnRight?.setOnClickListener {
            //Log.d("right","클릭")
            nMonth = nMonth.plus(1)
            //Log.d("left",nMonth.toString())
            if (nMonth > 12){
                nMonth = 1
                nYear = nYear.plus(1)
            }
            if(nMonth > LocalDate.now().month.value){
                nMonth = LocalDate.now().month.value
                var toastView = layoutInflater.inflate(R.layout.toast_board,null)
                toastView.setBackgroundResource(R.drawable.abclogo1_small)
                var txToast = toastView.findViewById<TextView>(R.id.tvToastText)
                txToast.text = "마지막 페이지입니다."
                var t = Toast(context)
                t.setGravity(Gravity.CENTER,0,400)
                t.view = toastView
                t.show()
            }
            txHeader?.text = nYear.toString() + " 년 " + nMonth.toString() + " 월"
            getAPIObject(nYear,nMonth)
        }
    }
    private fun getAPIObject(nYear: Int, nMonth: Int){
        ApiObject.listService().getTran(
            year = nYear.toString(),
            month = nMonth.toString(),
            par = mapOf(
                "createdAt" to "",
                "name" to "",
                "amount" to "",
                "categoryName" to "",
                "transactionType" to ""
            )).enqueue(object : Callback<ListDataResponse>{
            override fun onResponse(
                call: Call<ListDataResponse>,
                response: Response<ListDataResponse>
            ) {
                datas.clear()
                //Log.d("성공",response.message())
                listIn = response.body()
                //Log.d("response",response.toString())
                //Log.d("response", listIn.toString())
                if (response.body()?.result?.isEmpty() == true) { //response.body 에 데이터가 없을 경우
                    var toastView = layoutInflater.inflate(R.layout.toast_board,null)
                    val moneySplit = DecimalFormat("#,###")
                    toastView.setBackgroundResource(R.drawable.abclogo1_small)
                    var txToast = toastView.findViewById<TextView>(R.id.tvToastText)
                    txToast.text = "데이터가 없습니다."
                    var t = Toast(context)
                    t.setGravity(Gravity.CENTER,0,400)
                    t.view = toastView
                    t.show()
                    datas.clear() // 데이터 클리어
                    val txResultPlus = view?.findViewById<TextView>(R.id.tvPlusResult)
                    val txResultMinus = view?.findViewById<TextView>(R.id.tvMinusResult)

                    val monthData = response?.body()?.plusMinus
                    txResultPlus?.text = monthData?.get("plus").toString()
                    txResultMinus?.text = monthData?.get("minus").toString()
                    // 어댑터 붙여주기
                    listFragRecylcerAdapter = ListFragRecylcerAdapter(datas)
                    recycler_view.layoutManager = LinearLayoutManager(activity,RecyclerView.VERTICAL,false)
                    recycler_view.setHasFixedSize(true)
                    recycler_view.adapter = listFragRecylcerAdapter
                    listFragRecylcerAdapter.datas = datas
                    listFragRecylcerAdapter.notifyDataSetChanged() // 데이터 전체 갱신
                }else{
                    val mList = listIn?.result?.map {
                        val date = it.createdAt.substring(8 until 10)
                        //Log.d("date",date)
                        val tvtName : String = it.name
                        val moneySplit = DecimalFormat("#,###")
                        val strMoneyAmount = moneySplit.format(it.amount.toInt())
                        //Log.d("name",it.name)
                        //Log.d("amount",it.amount)
                        //Log.d("category",it.categoryName)
                        //Log.d("transaction",it.transactionType)
                        datas.apply {
                            add(ListData(date+"일",it.name,strMoneyAmount+"원",it.categoryName,it.transactionType))
                        }
                        val txResultPlus = view?.findViewById<TextView>(R.id.tvPlusResult)
                        val txResultMinus = view?.findViewById<TextView>(R.id.tvMinusResult)

                        val monthData = response?.body()?.plusMinus
                        txResultPlus?.text = "수입 :   + "+ moneySplit.format(monthData?.get("plus"))
                        txResultMinus?.text = "지출 :   - "+ moneySplit.format(monthData?.get("minus"))

                        listFragRecylcerAdapter = ListFragRecylcerAdapter(datas)
                        recycler_view.layoutManager = LinearLayoutManager(activity,RecyclerView.VERTICAL,false)
                        recycler_view.setHasFixedSize(true)
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
}
