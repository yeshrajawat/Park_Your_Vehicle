package com.codingblocks.parkyourvehicle

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.*
import kotlinx.android.synthetic.main.activity_dashboard.*
import org.json.JSONException
import org.json.JSONObject
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class Dashboard : AppCompatActivity() {
    val url = "https://parkyourvehicle.000webhostapp.com/retrieve.php";
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setContentView(R.layout.activity_dashboard)
        val sessionManager = SessionManager(this)
        val userdetails = sessionManager.getUserDetailFromSession()
        val dashboard_username = userdetails.get(sessionManager.key_username)
        val dashboard_name = userdetails.get(sessionManager.key_name)
        val dashboard_email = userdetails.get(sessionManager.key_email)
        val dashboard_password = userdetails.get(sessionManager.key_password)
        val dashboard_phone = userdetails.get(sessionManager.key_phone)



//        var list = ArrayList<String>()
//        var request = Volley.newRequestQueue(this)
//        val username = intent.getStringExtra("Username")
//        Log.e("TAG", "$username")
//        if (username != null) {
//            retrieveData(username, this)
//        }

    }

    fun add_btn_activity(view: View) {


        val intent  = Intent (this,add_vehicle::class.java)
        startActivity(intent)
        finish()



    }

    fun listofuser(view: View) {
        val intent = Intent(this,Empl::class.java)
        startActivity(intent)
        finish()
    }


//    fun retrieveData(username: String, context: Context) {
//
//
//        val request =
//            object : StringRequest(Request.Method.POST, url, Response.Listener { response ->
//                try {
//
//
//                    Log.e("TAG",response.toString())
//                    val jsonobject = JSONObject(response)
//                    val success = jsonobject.getString("success")
//                    val jsonArray = jsonobject.getJSONArray("data")
//                    Log.e("TAG", "$success")
//                    if (success.equals("1")) {
//                        for (x in 0..jsonArray.length()-1) {
//                            val jsonobj2 = jsonArray.getJSONObject(x)
//                            val getusername = jsonobj2.getString("username")
//                            val getname = jsonobj2.getString("name")
//                            val getemail = jsonobj2.getString("email")
//                            val getpassword = jsonobj2.getString("password")
//                            val getphone = jsonobj2.getString("phone")
//
//                            Log.e("TAG", "$getusername $getname $getpassword $getemail $getphone")
//                            dashboard_username.text = getusername
//                            dashboard_name.text = getname
//                            dashboard_email.text = getemail
//                            dashboard_password.text = getpassword
//                            dashboard_phone.text = getphone
//                        }
//                    }
//
//                }
//                catch (e:JSONException)
//                {
//                    Toast.makeText(this,e.message.toString(),Toast.LENGTH_SHORT).show()
//                    Log.e("TAG",e.message.toString())
//                }
//            }, Response.ErrorListener() {
//                fun onErrorResponse(error: VolleyError) {
//                    Toast.makeText(context, error.message.toString(), Toast.LENGTH_LONG).show()
//
//                }
//            }) {
//                @Throws(AuthFailureError::class)
//                override fun getParams(): Map<String, String> {
//                    val params: MutableMap<String, String> = HashMap<String, String>()
////                    params["username"] = username
////                    params["name"] = name
////                    params["email"] = email
////                    params["password"] = password
////                    params["phone"] = number
//                    params.put("username", username)
//
//
//
//                    return params
//                }
//            }
//        val requestQueue = Volley.newRequestQueue(context)
//        requestQueue.add(request)
//    }
}

