package com.example.myapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.adapter.ListAdapter
import com.example.myapplication.pojo.policyList
import com.example.myapplication.retrofit.APIClient
import com.example.myapplication.retrofit.ApiInterface
import kotlinx.android.synthetic.main.activity_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListActivity : AppCompatActivity() {

    var adapter : ListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        init()

    }

    private fun init() {
       val userID = intent.getStringExtra("userID")

        Log.e("userID", userID)
        id_list_rc.layoutManager = LinearLayoutManager(this)


        getList(userID)
    }

    private fun getList(userID: String) {

        APIClient.client?.create(ApiInterface::class.java)?.getPolicy(userID)?.
        enqueue(object :Callback<policyList>{
            override fun onFailure(call: Call<policyList>, t: Throwable) {

                Toast.makeText(applicationContext,t.message,Toast.LENGTH_LONG).show()


                Log.e("Error",t.message)
            }

            override fun onResponse(call: Call<policyList>, response: Response<policyList>) {

                if(response.body()!!.message_code == 1)
                {
                    Toast.makeText(applicationContext,response.body()!!.message,Toast.LENGTH_LONG).show()

                    if (response.body()!!.data.size != 0){
                        id_list_rc.visibility = View.VISIBLE
                        id_nodatafound_txt.visibility = View.GONE

                        adapter = ListAdapter(this@ListActivity, response.body()!!.data)
                        id_list_rc.adapter = adapter

                    }else{
                        id_list_rc.visibility = View.GONE
                        id_nodatafound_txt.visibility = View.VISIBLE
                    }


                }else{

                    id_list_rc.visibility = View.GONE
                    id_nodatafound_txt.visibility = View.VISIBLE
                }


            }
        })

    }
}
