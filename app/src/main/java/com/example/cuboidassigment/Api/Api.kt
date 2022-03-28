package com.example.cuboidassigment.Api

import com.example.cuboidassigment.model.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface Api {

    @POST("https://blaklif.com/v1/")
    fun regsiterUser(@Body userRequest: UserRequest):Call<ResiteredData>

    @POST("https://blaklif.com/v1/")
    fun loginUser(@Body userRequest: UserRequest):Call<LoginUserData>

    @POST("https://blaklif.com/v1/")
    fun getUPostList(@Body paramsUserList: ParamsUserList):Call<ResponseList>
}