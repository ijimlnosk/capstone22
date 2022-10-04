package com.daelim.capstone22

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.daelim.capstone22.`object`.ApiObject.signUpService
import com.daelim.capstone22.data.SignUpRequest
import com.daelim.capstone22.data.SignUpResponse
import kotlinx.android.synthetic.main.activity_join.*
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDateTime

val TAG : String = "JoinActivity"
var isBlank = false
var isPwEquals = false
var isEmailEquals = false

class JoinActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join)
        var signUp:SignUpResponse?=null

        // 이메일 전송
        // btn_emailSend.setOnClickListener {  }

        // btn_emailOk.setOnClickListener{
        // if ( email 인증 절차 ) {
        // 구글 이메일로 보낸 키 값과 edt_emailOk의 값이 같은지 비교
        // if (key !== edt_email) 일 경우 false
        // if (key == edt_email) 일 경우 true
        //  }
        //}
        //가입 버튼 클릭
        btn_joinOk.setOnClickListener {
            Log.d(TAG, "가입 버튼 클릭")

            val jName = edt_name.text.toString()
            val jEmail = edt_email.text.toString()
            val jPw = edt_PW.text.toString()
            val jPwOk = edt_PWok.text.toString()

            val jDate = LocalDateTime.now()

            btn_joinOk.setOnClickListener {
                signUpService.requestSignUp(SignUpRequest(email = jEmail, name = jName, password = jPw))
                    .enqueue(object : Callback<SignUpResponse>{
                        override fun onResponse(
                            call: Call<SignUpResponse>,
                            response: Response<SignUpResponse>
                        ) {
                            signUp = response.body()
                            //Log.d("LOGIN","name : " + signIn?.name)
                            Log.d("JOIN", "email : " + signUp?.email)
                            //Log.d("JOIN","create_at : " + signUp?.create_at)
                            Log.d("JOIN","name : "+signUp?.name)
                            Log.d("JOIN", "password : " + signUp?.password)
                            var dialog = AlertDialog.Builder(this@JoinActivity)
                            dialog.setTitle(signUp?.email)
                            //dialog.setMessage(signUp?.create_at.toString())
                            dialog.setMessage(signUp?.name)
                            dialog.setMessage(signUp?.password)
                            dialog.show()
                        }

                        override fun onFailure(call: Call<SignUpResponse>, t: Throwable) {
                            Log.e("JOIN", t.message.toString())
                            var dialog = AlertDialog.Builder(this@JoinActivity)
                            dialog.setTitle("에러")
                            dialog.setMessage("호출실패")
                            dialog.show()
                        }

                    })
            }

            /*// 빈 항목
            if(email.isEmpty()||pw.isEmpty()||pwOk.isEmpty()){
                isBlank = true
            }
            else{
                if(pw == pwOk){
                    isPwEquals = true
                }
            }
            if(!isBlank&& isPwEquals){
                Toast.makeText(this,"가입 완료",Toast.LENGTH_SHORT).show()

                // 쉐어드에 저장
                val sharedPreferences = getSharedPreferences("userInfor", Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.putString("email",email)
                editor.putString("pw",pw)
                editor.apply()

                val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
            }
            else{

                // 작성 하지 않은 항목 존재
                if(isBlank){
                    dialog("blink")
                }
                else if(!isPwEquals){
                    dialog("not Equals")
                 }
            }

        }
    }
    // 가입 실패 메소드
    fun dialog(type: String){
        val dialog = AlertDialog.Builder(this)

        // 빈 항목 존재시
        if(type.equals("blink")){
            dialog.setTitle("가입 실패")
            dialog.setMessage("빈 항목이 존재합니다.")
        }
        else if(type.equals("not Equals")){
            dialog.setTitle("가입 실패")
            dialog.setMessage("비밀번호가 다릅니다")
        }
        val dialog_listener = object : DialogInterface.OnClickListener{
            override fun onClick(dialog: DialogInterface?, which: Int) {
                when(which){
                    DialogInterface.BUTTON_POSITIVE -> Log.d(TAG, "다이얼로그")
                }
            }
        }

        dialog.setPositiveButton("확인",dialog_listener)
        dialog.show() */
        }
    }
}