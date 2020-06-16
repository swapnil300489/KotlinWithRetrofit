package com.example.myapplication.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import com.example.myapplication.R
import com.example.myapplication.pojo.Register
import com.example.myapplication.retrofit.APIClient
import com.example.myapplication.retrofit.ApiInterface
import kotlinx.android.synthetic.main.activity_register.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {

    var userName : String? = null
    var userEmail : String? = null
    var userID : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)


        id_registerBtn.setOnClickListener {

            val name     = id_name_ed.text.toString().trim()
            val contact  = id_contact_ed.text.toString().trim()
            val email    = id_email_ed.text.toString().trim()
            val password = id_password_ed.text.toString().trim()

            if (name.isEmpty()) {
                id_name_ed.requestFocus()
                id_name_ed.error = "Enter Name"
            }else if(contact.isEmpty()){
                id_contact_ed.requestFocus()
                id_contact_ed.error = "Enter contact number"
            }else if(email.isEmpty()){
                id_email_ed.requestFocus()
                id_email_ed.error = "Enter email id"
            }else if(!isEmailValid(email)){
                id_email_ed.requestFocus()
                id_email_ed.error = "Enter valid email id"
            }else if(password.isEmpty()){
                id_password_ed.requestFocus()
                id_password_ed.error = "Enter Password"
            }else{

                register(name, contact, email, password)

            }




        }


        id_already_txt.setOnClickListener {


            startActivity(Intent(this, MainActivity::class.java))

        }


    }

    private fun register(name: String, contact: String, email: String, password: String) {

        APIClient.client?.create(ApiInterface::class.java)?.register(name, contact, email, password)
            ?.enqueue(object: Callback<Register>{
                override fun onFailure(call: Call<Register>, t: Throwable) {

                    Toast.makeText(this@RegisterActivity, "Server Error"+t.message, Toast.LENGTH_LONG).show()

                }

                override fun onResponse(call: Call<Register>, response: Response<Register>) {

                    if(response.body()!!.message_code == 1)
                    {

                        userName  = response.body()!!.data.name
                        userEmail = response.body()!!.data.email
                        userID    = response.body()!!.data.user_id.toString()


                        Log.e("Register_UserName", userName)
                        Log.e("Register_userEmail", userEmail)
                        Log.e("Register_userID", userID)

                        val intent = Intent(this@RegisterActivity, MainActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                        startActivity(intent)


                    }else{

                        Log.e("Response", "Error : "+response.body()!!.message)

                    }
                }
            })

    }

    fun isEmailValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}
