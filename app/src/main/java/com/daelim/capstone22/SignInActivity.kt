package com.daelim.capstone22

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.daelim.capstone22.`object`.SignInAPI
import com.daelim.capstone22.data.SignInRequestBodyDTO
import com.daelim.capstone22.databinding.ActivityLoginBinding
import com.daelim.capstone22.service.SignInService
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.sign

class SignInActivity : AppCompatActivity() {
    val TAG: String = "LoginActivity"
    private lateinit var binding: ActivityLoginBinding
    var signIn:SignInRequestBodyDTO? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.123.137:8080")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        var signInService: SignInService = retrofit.create(SignInService::class.java)

        btnUserLogin.setOnClickListener {
            var txtEmail = edt_InputEmail.text.toString()
            var txtPassword = edt_InputPw.text.toString()

            signInService.requestSignIn(txtEmail,txtPassword).enqueue(object : Callback<SignInRequestBodyDTO> {
                override fun onFailure(call: Call<SignInRequestBodyDTO>, t: Throwable) {
                    t.message?.let { it1 -> Log.e("LOGIN", it1) }
                    var dialog = AlertDialog.Builder(this@SignInActivity)
                    dialog.setTitle("에러")
                    dialog.setMessage("호출실패")
                    dialog.show()
                }

                override fun onResponse(
                    call: Call<SignInRequestBodyDTO>,
                    response: Response<SignInRequestBodyDTO>
                ) {
                    signIn = response.body()
                    Log.d("LOGIN","email : "+signIn?.email)
                    Log.d("LOGIN","password : "+signIn?.password)
                    var dialog = AlertDialog.Builder(this@SignInActivity)
                    dialog.setTitle(signIn?.email)
                    dialog.setMessage(signIn?.password)
                    dialog.show()
                    intent = Intent(this@SignInActivity,MainActivity::class.java)
                    startActivity(intent)
                }
            })
        }

        /*binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val userData = SignInRequestBodyDTO(
            binding.edtInputEmail.edt_InputEmail?.text.toString(),
            binding.edtInputPw.edt_InputPw?.text.toString()
        )

        binding.btnUserLogin.setOnClickListener{
            val signInWork = SignInWork(userData)
            signInWork.sign()
        }*/

        //post
       /* val JSON = "application/json; charset=utf-8".toMediaTypeOrNull()

        var url = "localhost:8080/signup"
        val client = OkHttpClient

        val json = JSONObject()
        json.put("email","String")
        json.put("password","String")

        val body = RequestBody.create(JSON, json.toString())*/


        //로그인
        /*btn_userLogin.setOnClickListener {

            //edt에 입력된 값 받아오기
            val email = edt_InputEmail.text.toString()
            val pw = edt_InputPw.text.toString()

            // 쉐어드에 저장된 값 가져오기
            val sharedPreferences = getSharedPreferences("userInfor", Context.MODE_PRIVATE)
            val saveEmail = sharedPreferences.getString("email","")
            val savePw = sharedPreferences.getString("pw","")

            // 입력값과 저장된 값 비교
            if(email==saveEmail&&pw==savePw){
                // 성공
                dialog("success")
                val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
            }
            else if(email.isEmpty()||pw.isEmpty()){
                dialog("isBlank")
            }
            else{
                dialog("fail")
            }
        }

        // 가입 버튼
        btn_userjoin.setOnClickListener {
            val intent = Intent(this,JoinActivity::class.java)
            startActivity(intent)
        }
    }

    fun dialog(type: String){
        var dialog = AlertDialog.Builder(this)

        if(type.equals("success")){
            dialog.setTitle("SUCCESS LOGIN")
            dialog.setMessage("로그인 성공")
        }
        if(type.equals("isBlank")){
            dialog.setTitle("Blank")
            dialog.setMessage("아이디 또는 비밀번호를 입력해주세요")
        }
        if(type.equals("fail")){
            dialog.setTitle("FAIL LOGIN")
            dialog.setMessage("아이디와 비밀번호를 확인해주세요")
         }

        var dialog_listener = object : DialogInterface.OnClickListener{
            override fun onClick(dialog: DialogInterface?, which: Int) {
                when(which){
                    DialogInterface.BUTTON_POSITIVE -> Log.e(TAG,"")
                }
            }
        }

        dialog.setPositiveButton("확인",dialog_listener)
        dialog.show()*/
    }
}