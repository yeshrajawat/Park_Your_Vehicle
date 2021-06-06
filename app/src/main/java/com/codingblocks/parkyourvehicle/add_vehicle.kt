package com.codingblocks.parkyourvehicle

import android.app.ProgressDialog
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.timepicker.TimeFormat
import kotlinx.android.synthetic.main.activity_add_vehicle.*
import kotlinx.android.synthetic.main.activity_sign_in.*
import java.sql.Date
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.HashMap

class add_vehicle : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_add_vehicle)
        val sessionManager = SessionManager(this)
        val userdetails = sessionManager.getUserDetailFromSession()
        val username: String? = userdetails.get(sessionManager.key_username)
        add_employeeid.editText?.setText("$username")





    }

    fun add_vehicle(view: View) {
        val url = "https://parkyourvehicle.000webhostapp.com/insert_vehicle_data.php"
        val progressdialog = ProgressDialog(view.context)
        progressdialog.setMessage("Please Wait..")
        if (add_vehicleid.editText?.text.toString().trim().equals("")) {
            Toast.makeText(view.context, "Enter you Name", Toast.LENGTH_SHORT).show()
        } else if (add_name.editText?.text.toString().trim().equals("")) {
            Toast.makeText(view.context, "Enter you UserName", Toast.LENGTH_SHORT).show()
        } else if (add_vehiclename.editText?.text.toString().trim().equals("")) {
            Toast.makeText(view.context, "Enter you Email Address", Toast.LENGTH_SHORT).show()
        } else if (add_employeeid.editText?.text.toString().trim().equals("")) {
            Toast.makeText(view.context, "Enter you Password", Toast.LENGTH_SHORT).show()
        }
         else {

            val vehicleid = add_vehicleid.editText?.text.toString().trim()
            val name = add_name.editText?.text.toString().trim()
            val vehiclename = add_vehiclename.editText?.text.toString().trim()
            val employeeid = add_employeeid.editText?.text.toString().trim()



            val request = object : StringRequest(Request.Method.POST, url, Response.Listener<String?>() {

                    response ->
                    if(response.toString().equals("success"))
                    {

                        Toast.makeText(view.context,response, Toast.LENGTH_SHORT).show()
                        progressdialog.dismiss()
                        add_vehicleid.editText?.setText("")
                        add_name.editText?.setText("")
                        add_vehiclename.editText?.setText("")

                    }
                else
                    {

                        Toast.makeText(view.context,"Wrong information entered", Toast.LENGTH_SHORT).show()
                        progressdialog.dismiss()
                    }



            }, Response.ErrorListener() {
                fun onErrorResponse(error: VolleyError) {
                    Toast.makeText(view.context, error.message.toString(), Toast.LENGTH_LONG).show()
                }
            }) {
                @RequiresApi(Build.VERSION_CODES.O)
                @Throws(AuthFailureError::class)
                override fun getParams(): Map<String, String> {
                    val params: MutableMap<String, String> = HashMap<String, String>()

                    params.put("vehicleid",vehicleid )
                    params.put("name", name)
                    params.put("vehiclename", vehiclename)
                    params.put("employee_id", employeeid)
                    val sdf :SimpleDateFormat = SimpleDateFormat("YYYY-MM-dd")
                    val datel = java.util.Date()
                    val date = sdf.format(datel).toString()
                    val calendar:Calendar = Calendar.getInstance()
                    val simpleDateFormat = SimpleDateFormat("hh:mm:ss")
                    val time = simpleDateFormat.format(calendar.time)


                    params.put("date_entered", date.toString())
                    params.put("time_entered",time.toString())

                    return params
                }
            }
            val requestqueue = Volley.newRequestQueue(view.context)
            requestqueue.add(request)



        }



    }

    fun back_btn(view: View) {

        val intent = Intent(this,Dashboard::class.java)
        startActivity(intent)
        finish()
    }
}