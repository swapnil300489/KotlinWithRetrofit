package com.example.myapplication.retrofit

import com.example.myapplication.pojo.Register
import com.example.myapplication.pojo.policyList
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiInterface {

    @POST("create")
    @FormUrlEncoded
    fun register(@Field("name")     name: String,
                 @Field("mob")      mob: String,
                 @Field("email")    email: String,
                 @Field("password") password: String): Call<Register>

    @POST("login")
    @FormUrlEncoded
    fun login(@Field("email") email:String,
              @Field("password") password: String):Call<Register>


    @POST("get_past_policy")
    @FormUrlEncoded
    fun getPolicy(@Field("user_id") userID:String):Call<policyList>
}