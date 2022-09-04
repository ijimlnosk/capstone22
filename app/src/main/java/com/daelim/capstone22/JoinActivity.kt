package com.daelim.capstone22

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_join.*

class JoinActivity : AppCompatActivity() {

    val TAG: String = "JoinActivity"
    var isBlank = false
    var isPwSame = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join)
        /*val edtName = findViewById<EditText>(R.id.edt_name)
        val edtEmail = findViewById<EditText>(R.id.edt_email)
        val edtPw = findViewById<EditText>(R.id.edt_PW)
        val edtPwOk = findViewById<EditText>(R.id.edt_PWok)
        val btnJoinOk = findViewById<Button>(R.id.btn_JoinOk)*/

        btn_JoinOk.setOnClickListener {
            Log.d(TAG,"확인 버튼 클릭")
            
            val name = edt_name.text.toString()
            val email = edt_email.text.toString()
            val password = edt_PW.text.toString()
            val passwordOk = edt_PWok.text.toString()

            // 빈 항목이 있을 때
            if(name.isEmpty() || email.isEmpty() || password.isEmpty() || passwordOk.isEmpty()) {
                isBlank = true
            }
            else{
                if(password == passwordOk){
                    isPwSame = true
                }
            }
            if(!isBlank && isPwSame){   // 가입 성공시
                // 가입 완료 토스트 띄우기ㅁㄴ
                Toast.makeText(this,"가입 완료",Toast.LENGTH_SHORT).show()

                // 쉐어드에 저장
                val SharedPreferences = getSharedPreferences("userInformation",Context.MODE_PRIVATE)
                val editor = SharedPreferences.edit()
                editor.putString("name",name)
                editor.putString("email",email)
                editor.putString("Pw",password)
                editor.apply()

                // 메인 이동
                val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
            }
            else{
                //작성하지 않은 항목이 있을 경우
                if(isBlank){
                    dialog("blank")
                }
                // 비밀번호가 다를 경우
                else if(!isPwSame){
                    dialog("not same")
                 }
            }
        }
    }
    // 가입 실패시 다이얼로그를 띄워주는 메소드ㅁ
    fun dialog(type: String){
        val dialog = AlertDialog.Builder(this)
        // 빈칸
        if(type.equals("blank")){
            dialog.setTitle("회원가입 실패")
            dialog.setMessage("비어있는 입력란이 있습니다")
        }
        // 비밀번호 다름
        else if(type.equals("not same")){
            dialog.setTitle("회원가입 실패")
            dialog.setMessage("비밀번호가 다릅니다")
         }

        val dialog_list = DialogInterface.OnClickListener { p0, which ->
            when(which){
                DialogInterface.BUTTON_POSITIVE ->
                    Log.d(TAG,"다이얼로그")
            }
        }
        dialog.setPositiveButton("확인",dialog_list)
        dialog.show()
    }
}