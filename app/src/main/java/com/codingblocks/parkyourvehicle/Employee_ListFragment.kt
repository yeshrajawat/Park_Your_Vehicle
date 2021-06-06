package com.codingblocks.parkyourvehicle

import android.content.Context
import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.employee_list_fragment.*
import kotlinx.android.synthetic.main.vehicle_data_fragment.*
import org.json.JSONException
import org.json.JSONObject


class Employee_ListFragment : Fragment() {
    var employee_data = ArrayList<Employee_List>()
    lateinit var itemadapter:EmployeeListAdapter
    val url = "https://parkyourvehicle.000webhostapp.com/retrieve.php"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retrieveData()

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.employee_list_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.e("TAG","ON CREATE")


        itemadapter = EmployeeListAdapter(employee_data)
        employee_recycler.layoutManager = LinearLayoutManager(context)
          employee_recycler.adapter = itemadapter


    }


    fun retrieveData( ) {

        Log.e("TAG","IN RETRIEVE DATA")
        val request =
            object : StringRequest(Request.Method.POST, url, Response.Listener { response ->
                try {



                    val jsonobject = JSONObject(response)
                    val success = jsonobject.getString("success")
                    val jsonArray = jsonobject.getJSONArray("data")

                    if (success.equals("1")) {
                        Log.e("TAG",success)
                        for (x in 0..jsonArray.length()) {
                            val jsonobj2 = jsonArray.getJSONObject(x)
                            val getusername = jsonobj2.getString("username")
                            val getname = jsonobj2.getString("name")
                            val getemail = jsonobj2.getString("email")
                            val getpassword = jsonobj2.getString("password")
                            val getphone = jsonobj2.getString("phone")
                            Log.e("TAG","$getusername")
                            employee_data.add(
                                Employee_List(getusername,getname,getemail,getphone))
                            itemadapter.notifyDataSetChanged()

                        }
                    }

                } catch (e: JSONException) {
                    Toast.makeText(context, e.message.toString(), Toast.LENGTH_SHORT).show()
                    Log.e("TAG", e.message.toString())
                }
            }, Response.ErrorListener() {
                fun onErrorResponse(error: VolleyError) {
                    Toast.makeText(this.context, error.message.toString(), Toast.LENGTH_LONG).show()

                }
            }){
                @Throws(AuthFailureError::class)
                override fun getParams(): Map<String, String> {
                    val params: MutableMap<String, String> = HashMap<String, String>()
//                    params["username"] = username
//                    params["name"] = name
//                    params["email"] = email
//                    params["password"] = password
//                    params["phone"] = number
                    params.put("username", "yesh")

                    Log.e("TAG","SENDING SUCCESS")

                    return params
                }
            }
        Volley.newRequestQueue(context).add(request)





}
}