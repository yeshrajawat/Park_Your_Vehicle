package com.codingblocks.parkyourvehicle

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.provider.UserDictionary
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.vehicle_data_single.view.*
import org.json.JSONObject

class VehicleListAdapter(val items: ArrayList<Vehicle>,val context: Context) :RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    lateinit var ids: String
    val filteritems= items
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val view: View = layoutInflater.inflate(R.layout.vehicle_data_single, null)

        return playerviewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val mitems: Vehicle = filteritems[position]

        holder.itemView.card_name.text = mitems.name.capitalize()
        holder.itemView.card_vehicle_id.text = mitems.vehicleid.capitalize()
        holder.itemView.card_employee_id.text = mitems.employeeid
        holder.itemView.card_vehicle_name.text = mitems.vehiclename.capitalize()
        holder.itemView.card_time.text = mitems.time_entered
        holder.itemView.card_date.text = mitems.date_entered


        val url = "https://parkyourvehicle.000webhostapp.com/delete_vehicle_data.php"
        holder.itemView.delete_vehicle.setOnClickListener {

            val id = filteritems[position].vehicleid
            Log.e("TAG", id)
            val alertDialog = AlertDialog.Builder(context)
            alertDialog.setTitle("Alter Dialog")
            alertDialog.setMessage("Confirm to delete the entry")
            Log.e("TAG", "inner class")
            alertDialog.setPositiveButton("Yes",
                { dialogInterface: DialogInterface, i: Int -> delete_data(url, id,position) })

            alertDialog.setNegativeButton("No", null)
            alertDialog.create().show()
            notifyDataSetChanged()


        }

    }

    fun delete_data(url: String, id: String,position:Int) {
        val progressdialog = ProgressDialog(context)
        progressdialog.setMessage("Please Wait..")
        Log.e("TAG", "inside delete data")


        val request =
            object : StringRequest(Request.Method.POST, url, Response.Listener { response ->
                Log.e("TAG", response.toString())
                if (response.equals("success")) {
                    Log.e("TAG", "inside response")
                    Toast.makeText(context, response.toString(), Toast.LENGTH_SHORT).show()
                    notifyDataSetChanged()
                    notifyItemRemoved(position)
                }

            }, Response.ErrorListener() {
                fun onErrorResponse(error: VolleyError) {
                    Toast.makeText(context, error.message.toString(), Toast.LENGTH_LONG).show()
                    notifyDataSetChanged()

                }
            }) {
                @Throws(AuthFailureError::class)
                override fun getParams(): Map<String, String> {
                    val params: MutableMap<String, String> = HashMap<String, String>()

                    params.put("id", id)

                    return params
                }
            }

        val requestqueue = Volley.newRequestQueue(context)
        requestqueue.add(request)
    }


    inner class playerviewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun getItemCount(): Int {
        return filteritems.size
    }


}


