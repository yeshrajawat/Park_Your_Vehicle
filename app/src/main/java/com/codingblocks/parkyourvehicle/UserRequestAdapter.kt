package com.codingblocks.parkyourvehicle

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Context
import android.content.DialogInterface
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_sign_in.*
import kotlinx.android.synthetic.main.employee_list_single.view.*
import kotlinx.android.synthetic.main.user_request_single.view.*
import kotlinx.android.synthetic.main.vehicle_data_single.view.*
import kotlinx.android.synthetic.main.vehicle_data_single.view.card_name
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class UserRequestAdapter(val items:ArrayList<Requests>):RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val view: View = layoutInflater.inflate(R.layout.user_request_single,parent,false)
        return playerviewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var mitems: Requests = items[position]

        holder.itemView.request_card_name.text = mitems.name.capitalize()
        holder.itemView.request_card_email.text = mitems.email
        holder.itemView.request_card_empid.text = mitems.username
        holder.itemView.request_card_phone.text = mitems.phone



        holder.itemView.requestbtn.setOnClickListener {


            val alertDialog = AlertDialog.Builder(it.context)
            alertDialog.setTitle("Alter Dialog")
            alertDialog.setMessage("Confirm to delete the entry")

            alertDialog.setPositiveButton("Yes",
                    { dialogInterface: DialogInterface, i: Int -> insert_user_request(items[position],it.context)


                    })
            Log.e("TAG", "after set positive button")
            alertDialog.setNegativeButton("No", null)
            alertDialog.create().show()


        }
    }

    override fun getItemCount(): Int {
    return items.size
    }


    fun insert_user_request(items:Requests,context: Context) {
        Log.e("TAG","Just below insert request")
        val url : String = "https://parkyourvehicle.000webhostapp.com/insert_user_requests.php"
        val progressdialog = ProgressDialog(context)
        progressdialog.setMessage("Please Wait..")


        val request = object : StringRequest(Request.Method.POST,url,Response.Listener {response ->
            Log.e("TAG","above if")
            if(response.toString().equals("success"))

                {
                    Log.e("TAG","INSIDE IF")

                    Toast.makeText(context,"Successfully added a user",Toast.LENGTH_SHORT).show()
                    notifyDataSetChanged()
                }
            else
            {
                Log.e("TAG",response.toString())
            }
        },  Response.ErrorListener() {
            fun onErrorResponse(error: VolleyError) {
                Toast.makeText(context, error.message.toString(), Toast.LENGTH_LONG).show()
                Log.e("TAG",error.message.toString())
                notifyDataSetChanged()
            }
        })
        {
            @Throws(AuthFailureError::class)
            override fun getParams(): Map<String, String> {
                //Calculating time and date and converting it into String and passing it into map
                val sdf : SimpleDateFormat = SimpleDateFormat("YYYY-MM-dd")
                val datel = java.util.Date()
                val date = sdf.format(datel).toString()
                val calendar: Calendar = Calendar.getInstance()
                val simpleDateFormat = SimpleDateFormat("hh:mm:ss")
                val time = simpleDateFormat.format(calendar.time)
                //Session manager is called to retrieve current user who is adding the user from request slot to main slot
                val sessionManager = SessionManager(context)
                val userdetails = sessionManager.getUserDetailFromSession()
                val active_emp: String? = userdetails.get(sessionManager.key_username)

                val params: MutableMap<String, String> = HashMap<String, String>()
                params.put("username", items.username)
                params.put("name", items.name)
                params.put("email", items.email)
                params.put("password", items.password)
                params.put("phone", items.phone)
                params.put("date_admitted",date.toString())
                params.put("time_admitted",time.toString())
                if (active_emp != null) {
                    Log.e("TAG","ACTIVE EMP")
                    params.put("admittedby", active_emp)
                }



                return params
            }
        }


        val requestqueue = Volley.newRequestQueue(context)
        requestqueue.add(request)


    }













    inner class playerviewHolder(itemView: View):RecyclerView.ViewHolder(itemView)

}