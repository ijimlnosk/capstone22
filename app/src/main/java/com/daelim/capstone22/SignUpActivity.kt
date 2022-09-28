package com.daelim.capstone22

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_join.*

val TAG : String = "JoinActivity"
var isBlank = false
var isPwEquals = false
var isEmailEquals = false

class JoinActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join)

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

            val email = edt_email.text.toString()
            val pw = edt_PW.text.toString()
            val pwOk = edt_PWok.text.toString()

            // 빈 항목
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
        dialog.show()
    }
}