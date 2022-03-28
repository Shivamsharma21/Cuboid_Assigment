package com.example.cuboidassigment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.cuboidassigment.Api.Api
import com.example.cuboidassigment.model.LoginUserData
import com.example.cuboidassigment.model.ParamsUserList
import com.example.cuboidassigment.model.UserRequest
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FeedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)
            saveUser(getUserInfo())
    }

    fun getUserInfo(): ParamsUserList{
        val userParams = ParamsUserList()
          userParams.token =
        return userParams
    }

    fun saveUser(userRequest: ParamsUserList){
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
    ///  / api.getUPostList()

    }



}
