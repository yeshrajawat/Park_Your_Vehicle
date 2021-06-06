package com.codingblocks.parkyourvehicle

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.vehicle_data_fragment.*

class VehicleListFragment: Fragment() {
    var vehicle_data = ArrayList<Vehicle>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =inflater.inflate(R.layout.vehicle_data_fragment,container,false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        var itemadapter = VehicleListAdapter(vehicle_data,context)
//        vehicle_recycler.layoutManager = LinearLayoutManager(context)
//        vehicle_recycler.adapter = itemadapter


    }
}