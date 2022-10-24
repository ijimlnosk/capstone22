package com.daelim.capstone22

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.daelim.capstone22.App.Companion.prefs
import com.daelim.capstone22.`object`.ApiObject.listService
import com.daelim.capstone22.`object`.ApiObject.signInService
import com.daelim.capstone22.data.*
import com.daelim.capstone22.fragment.ListFragment
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.list_item.*
import kotlinx.coroutines.CoroutineExceptionHandler
import org.w3c.dom.Text
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.sign


class SignInActivity : AppCompatActivity() {
    val TAG: String = "LoginActivity"
    //private lateinit var binding: ActivityLoginBinding
    var signIn:SigninResponse? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btn_userjoin.setOnClickListener {
           val intent = Intent(this,JoinActivity::class.java)
            startActivity(intent)
        }

        btnUserLogin.setOnClickListener {
            var txtEmail = edt_InputEmail.text.toString()
            var txtPassword = edt_InputPw.text.toString()

            // 로그인
            signInService.requestSignIn(SignInRequestBodyDTO(email = txtEmail,
                password = txtPassword)).enqueue(object : Callback<SigninResponse>{
                override fun onFailure(call: Call<SigninResponse>, t: Throwable) {
                    Log.e("LOGIN", t.message.toString())
                    var dialog = AlertDialog.Builder(this@SignInActivity)
                    dialog.setTitle("에러")
                    dialog.setMessage("호출실패")
                    dialog.show()
                }
                override fun onResponse(
                    call: Call<SigninResponse>,
                    response: Response<SigninResponse>
                ) {
                    signIn = response.body()
                    /*val intent = Intent(this@SignInActivity,MainActivity::class.java)
                    startActivity(intent)*/
                    if (signIn?.result.equals("성공")){
                        Log.d("LOGIN","result : "+signIn?.result)
                       /* val sharedPreferences = getSharedPreferences("jwt", MODE_PRIVATE)
                        val editor : SharedPreferences.Editor = sharedPreferences.edit()
                        editor.putString("token",signIn?.token.toString())
                        editor.apply()*/
                        val token = signIn?.token.toString()
                        App.prefs.token = token
                        Log.d("LOGIN","token : "+signIn?.token)
                        intent = Intent(this@SignInActivity,MainActivity::class.java)
                        startActivity(intent)
                    }
                    else if (signIn?.result.equals("실패")){
                        Toast.makeText(this@SignInActivity,"오류",Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }
    }
}