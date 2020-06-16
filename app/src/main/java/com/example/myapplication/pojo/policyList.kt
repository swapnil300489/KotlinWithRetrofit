package com.example.myapplication.pojo

data class policyList (

    val message_code : Int,
    val message : String,
    val data : List<DataList>
)

data class DataList (

    val id : Int,
    val client : String,
    val policy_no : String,
    val dtc1 : String,
    val dtc2 : String,
    val premium : String,
    val mode : String,
    val no : String,
    val tp : String,
    val commission : String,
    val bonus : String,
    val total : String,
    val status : String,
    val created_at : String,
    val user_id : String,
    val plan : String,
    val term : String,
    val img : String
)