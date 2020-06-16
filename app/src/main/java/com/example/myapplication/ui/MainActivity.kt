package com.example.myapplication.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.myapplication.R
import com.example.myapplication.pojo.Register
import com.example.myapplication.retrofit.APIClient
import com.example.myapplication.retrofit.ApiInterface

import kotlinx.android.synthetic.main.logig_demo.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    var array = arrayOf("Melbourne", "Vienna", "Vancouver", "Toronto", "Calgary", "Adelaide", "Perth",
        "Auckland", "Helsinki", "Hamburg", "Munich", "New York", "Sydney", "Paris", "Cape Town",
        "Barcelona", "London", "Bangkok")

    var userName : String? = null
    var userEmail : String? = null
    var userID : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.logig_demo)


        id_loginBtn.setOnClickListener {

            val name    = id_name_ed_text2.text.toString().trim()
            val score   = id_name_ed_text3.text.toString().trim()


            if(name.isEmpty()){
                id_name_ed_text2.requestFocus()
                id_name_ed_text2.error = "Enter Email id"

            }else if(score.isEmpty()){
                id_name_ed_text3.requestFocus()
                id_name_ed_text3.error = "Enter Password"
            }else{

                addData(name, score)

            }

        }


        id_gotoRegister.setOnClickListener {

            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)

        }
    }

    private fun addData(email: String, password: String) {

        APIClient.client!!.
        create(ApiInterface::class.java).
        login(email, password).enqueue(object :Callback<Register>{
            override fun onFailure(call: Call<Register>, t: Throwable) {
                Toast.makeText(applicationContext, "Error :"+t.message,Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<Register>, response: Response<Register>) {

                if (response.body()!!.message_code == 1){

                    Toast.makeText(applicationContext, "Response :"+ response.body()!!.message,Toast.LENGTH_LONG).show()

                    userName  = response.body()!!.data.name
                    userEmail = response.body()!!.data.email
                    userID    = response.body()!!.data.user_id.toString()


                    Log.e("Login_UserName", userName)
                    Log.e("Login_userEmail", userEmail)
                    Log.e("Login_userID", userID)

                    val intent  = Intent(this@MainActivity, ListActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    intent.putExtra("userID", userID)
                    startActivity(intent)

                }else{

                    Toast.makeText(applicationContext, "Response :"+ response.body()!!.message,Toast.LENGTH_LONG).show()

                }


            }
        })



        Toast.makeText(applicationContext, "Add record into DB", Toast.LENGTH_LONG).show()

    }
}
