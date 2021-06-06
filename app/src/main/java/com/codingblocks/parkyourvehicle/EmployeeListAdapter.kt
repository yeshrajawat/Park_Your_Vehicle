package com.codingblocks.parkyourvehicle

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.employee_list_single.view.*
import kotlinx.android.synthetic.main.vehicle_data_single.view.*
import kotlinx.android.synthetic.main.vehicle_data_single.view.card_name

class EmployeeListAdapter(val items: ArrayList<Employee_List>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val view: View = layoutInflater.inflate(R.layout.employee_list_single,parent,false)
        return playerviewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var mitems: Employee_List = items[position]

        holder.itemView.card_name.text = mitems.name.capitalize()
        holder.itemView.card_email.text = mitems.email
        holder.itemView.card_empid.text = mitems.username
        holder.itemView.card_phone.text = mitems.phone

      }

    override fun getItemCount(): Int {
        return items.size
    }
    inner class playerviewHolder( itemView: View): RecyclerView.ViewHolder(itemView)



}
