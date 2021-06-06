package com.codingblocks.parkyourvehicle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ActionProvider
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_vehicle_list.*
import kotlinx.android.synthetic.main.employee_list_fragment.*
import kotlinx.android.synthetic.main.vehicle_data_fragment.*
import kotlinx.android.synthetic.main.vehicle_data_fragment.vehicle_recycler
import org.json.JSONException
import org.json.JSONObject

class vehicle_list : AppCompatActivity() {

    var vehicle_data = ArrayList<Vehicle>()
    lateinit var itemadapter:VehicleListAdapter
    val url = "https://parkyourvehicle.000webhostapp.com/retrieve_vehicle_data.php"   // Url is used to fetch data from mysql data
    //Search bar to filter data in recycler view
    var filter_vehicle_data = ArrayList<Vehicle>()











    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vehicle_list)

        //Adapter for recycler view is added
        itemadapter = VehicleListAdapter(vehicle_data,this)
        vehicle_recycler.layoutManager = LinearLayoutManager(this)
        vehicle_recycler.adapter = itemadapter


        retrieveData()

        swiperefresh.setOnRefreshListener {

            itemadapter.notifyDataSetChanged()
            swiperefresh.isRefreshing = false
            Toast.makeText(this,"Refresh complete",Toast.LENGTH_SHORT).show()
        }



    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {


    menuInflater.inflate(R.menu.menu_item,menu)
        val item = menu?.findItem(R.id.search_bar)
        val search:android.widget.SearchView = item?.actionView as android.widget.SearchView
        search.maxWidth = Int.MAX_VALUE



        return super.onCreateOptionsMenu(menu)
    }


























    //This function is to retrieve data from sql for the list of vehicles in jklu campus
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
                            for (x in 0..jsonArray.length()-1) {

                                val jsonobj2 = jsonArray.getJSONObject(x)

                                val vehicle_id = jsonobj2.getString("vehicle_id")

                                val name = jsonobj2.getString("name")
                                val vehicle_name = jsonobj2.getString("vehiclename")
                                val vehicle_enteredby =jsonobj2.getString("employee_id")
                                val vehicle_entered_date = jsonobj2.getString("date_entered")
                                val vehicle_entered_time = jsonobj2.getString("time_entered")

                                //Detail's to be dded into Vehicle object
                                vehicle_data.add(
                                        Vehicle( vehicle_id, name, vehicle_name, vehicle_enteredby, vehicle_entered_date, vehicle_entered_time))


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


                        return params
                    }
                }
        Volley.newRequestQueue(this).add(request)


    }



}