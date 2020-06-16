package com.example.myapplication.pojo


data class Register (

    val message_code : Int,
    val message : String,
    val data : Data
)

data class Data (

    val name : String,
    val email : String,
    val password : String,
    val mob : String,
    val date_time : String,
    val user_id : Int
)