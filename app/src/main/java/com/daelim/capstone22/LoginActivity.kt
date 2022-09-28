package com.daelim.capstone22

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import com.daelim.capstone22.data.SignUpRequestBodyDTO
import com.daelim.capstone22.databinding.ActivityLoginBinding
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.view.*

class LoginActivity : AppCompatActivity() {
    val TAG: String = "LoginActivity"
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val userData = SignUpRequestBodyDTO(
            binding.edtInputEmail.edt_InputEmail?.text.toString(),
            binding.edtInputPw.edt_InputPw?.text.toString()
        )

        binding.btnUserLogin.setOnClickListener{
            val signInWork = SignInWork(userData)
            signInWork.sign()
        }

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