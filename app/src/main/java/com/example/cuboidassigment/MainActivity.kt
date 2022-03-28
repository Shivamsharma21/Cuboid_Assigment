package com.example.cuboidassigment

import android.content.ContentValues.TAG
import android.content.Intent

import android.os.Bundle

import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.cuboidassigment.Api.Api
import com.example.cuboidassigment.model.LoginUserData
import com.example.cuboidassigment.model.ResiteredData
import com.example.cuboidassigment.model.UserRequest
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

     lateinit var signINbtn:TextView
     lateinit var fullname:EditText
     lateinit var password:EditText
     lateinit var conPassword:EditText
     lateinit var email:EditText
     lateinit var signUpButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

           signUpButton = findViewById(R.id.signup_btn)
           signINbtn = findViewById(R.id.SigninBtn)
           fullname = findViewById(R.id.edittextFull_name)
           password = findViewById(R.id.edittext_password)
           conPassword = findViewById(R.id.conform_password)
           email = findViewById(R.id.edittext_email)

        signINbtn.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@MainActivity,LoginActivity::class.java)
            startActivity(intent)
        })
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w(TAG, "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result

            // Log and toast
            Log.d("This is token ->",token)
        })
        signUpButton.setOnClickListener(View.OnClickListener {
            saveUser(fetch_data())

        })
    }

    fun fetch_data() : UserRequest {
          val userRequest: UserRequest =
              UserRequest()
          val confirmpassword = conPassword.text
          val email = email.text
          val password = password.text
          val fullname = fullname.text

        userRequest.setUser_name(fullname.toString())
        userRequest.setPassword(password.toString())
        userRequest.setEmail_address(email.toString())
        userRequest.setAndroid_token("APA91bEAfoucYYgXO_" +
                "t1ENfJvY2hRXdH5SUFjFDAZL2kgjes_jYyo15tPPI0fYHkL" +
                "TmuagQbHAth5dmA6uPdpB_dhJhcwt47t9vR2ChldINDmJ5vGmc" +
                "WcOyUg5Uqc0cR0lflqD3oaoRs")
        userRequest.setDevice_type(0)

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
             api.regsiterUser(userRequest).enqueue(object : Callback<ResiteredData> {
                 override fun onResponse(
                     call: Call<ResiteredData>,
                     response: Response<ResiteredData>,
                 ) {
                    Log.d("UserRegistered","Registered")
                     val intent = Intent(this@MainActivity,DashBoardActivity::class.java)
                     startActivity(intent)

                 }

                 override fun onFailure(call: Call<ResiteredData>, t: Throwable) {
                     Log.d("UserFailed ->","Failed")
                 }
             })

   api.loginUser(userRequest).enqueue(object : Callback<LoginUserData>{
       override fun onResponse(call: Call<LoginUserData>, response: Response<LoginUserData>) {
             Log.d("UserLoggedIn","User LoggedIn")
       }

       override fun onFailure(call: Call<LoginUserData>, t: Throwable) {
         Log.d("UserLoggedIn","User Failed")
       }

   })

         }


}