package com.example.cuboidassigment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.example.cuboidassigment.Api.Api
import com.example.cuboidassigment.model.LoginUserData
import com.example.cuboidassigment.model.UserRequest
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginActivity : AppCompatActivity() {

    private lateinit var loginbtn:Button
    private lateinit var loginEmail:EditText
    private lateinit var loginPassword:EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
       loginEmail = findViewById(R.id.edittext_login_email)
        loginPassword = findViewById(R.id.login_password)
        loginbtn = findViewById(R.id.login_btn)

        loginbtn.setOnClickListener(View.OnClickListener {
          saveUser(allowUserTologin())
        })
    }

    fun allowUserTologin(): UserRequest {

    val userRequest = UserRequest()
         val email = loginEmail.text
         val password = loginPassword.text
        userRequest.setEmail_address(email.toString())
        userRequest.setPassword(password.toString())

        return userRequest
    }

    fun saveUser(userRequest: UserRequest){
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        //  val okHttpClient = OkHttpClient().newBuilder().addInterceptor(httpLoggingInterceptor)
        val okHttpClient1 = OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://blaklif.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient1.build())
            .build()

        val api = retrofit.create(Api::class.java)
        api.loginUser(userRequest).enqueue(object : Callback<LoginUserData> {
            override fun onResponse(call: Call<LoginUserData>, response: Response<LoginUserData>) {
                Log.d("Response ->",response.code().toString())
                if (response.isSuccessful){
                    Log.d("UserLoggedIn","User LoggedIn")
                    var data1 = response.body()
                      var dataX  = data1?.data
                     Log.d("DataX ->",dataX?.android_token.toString())
                }


                val intent = Intent(this@LoginActivity,DashBoardActivity::class.java)
                startActivity(intent)
            }
            override fun onFailure(call: Call<LoginUserData>, t: Throwable) {
                Log.d("UserLoggedIn","User Failed")
            }
        })

    }

}