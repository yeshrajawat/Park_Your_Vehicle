package com.codingblocks.parkyourvehicle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_user__requests.*
import kotlinx.android.synthetic.main.employee_list_fragment.*
import org.json.JSONException
import org.json.JSONObject

class User_Requests : AppCompatActivity() {
    var user_request_data = ArrayList<Requests>()
    lateinit var itemadapter:UserRequestAdapter
    val url = "https://parkyourvehicle.000webhostapp.com/user_requests.php"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user__requests)
        itemadapter = UserRequestAdapter(user_request_data)
        user_request_recycler.layoutManager = LinearLayoutManager(this)
        user_request_recycler.adapter = itemadapter
        Log.e("TAG","Yesh")
        retrieve_user_request()

    }

    private fun retrieve_user_request() {


        val request =
                object : StringRequest(Request.Method.POST, url, Response.Listener { response ->
                    try {
                            Log.e("TAF","after request")
                        val jsonobject = JSONObject(response)
                        val success = jsonobject.getString("success")
                        val jsonArray = jsonobject.getJSONArray("data")

                        if (success.equals("1")) {
                            Log.e("TAG",success)
                            for (x in 0..jsonArray.length()-1) {
                                val jsonobj2 = jsonArray.getJSONObject(x)
                                val getusername = jsonobj2.getString("username")
                                val getname = jsonobj2.getString("name")
                                val getemail = jsonobj2.getString("email")
                                val getphone = jsonobj2.getString("phone")
                                val getpassword = jsonobj2.getString("password")

                                user_request_data.add(
                                        Requests(getusername,getname,getemail,getphone,getpassword))
                                itemadapter.notifyDataSetChanged()

                            }
                        }

                    } catch (e: JSONException) {
                        Toast.makeText(this, e.message.toString(), Toast.LENGTH_SHORT).show()
                        Log.e("TAG", e.message.toString())
                    }
                }, Response.ErrorListener() {
                    fun onErrorResponse(error: VolleyError) {
                        Toast.makeText(this, error.message.toString(), Toast.LENGTH_LONG).show()

                    }
                }){
                    @Throws(AuthFailureError::class)
                    override fun getParams(): Map<String, String> {
                        val params: MutableMap<String, String> = HashMap<String, String>()

                        params.put("username", "yesh")

                        Log.e("TAG","SENDING SUCCESS")

                        return params
                    }
                }
        Volley.newRequestQueue(this).add(request)




    }
}