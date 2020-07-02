package com.timelysoft.tsjdomcom

import android.content.res.ColorStateList
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.timelysoft.tsjdomcom.adapters.house.TowardsHouseAdapter
import com.timelysoft.tsjdomcom.adapters.house.TowardsHouseModel
import com.timelysoft.tsjdomcom.service.Status
import com.timelysoft.tsjdomcom.service.model.AddressModel
import com.timelysoft.tsjdomcom.ui.main.MainActivity
import kotlinx.android.synthetic.main.fragment_history.*
import kotlinx.android.synthetic.main.fragment_towards_house.*

class TowardsHouseFragment : Fragment() {

    private var myAdapter = TowardsHouseAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_towards_house, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.show()
        getAutoAddress()
        initArguments()
    }

    private fun initArguments() {
        val list: ArrayList<TowardsHouseModel> = arrayListOf()
        list.add(TowardsHouseModel(""))
        list.add(TowardsHouseModel(""))
        list.add(TowardsHouseModel(""))
        list.add(TowardsHouseModel(""))
        list.add(TowardsHouseModel(""))

        myAdapter.update(list)
        towards_house_recycler.adapter = myAdapter
    }

    private fun getAutoAddress() {
        val list = arrayOf("зайчик", "вышел", "погулять","вода")

        val adapterAddress = ArrayAdapter(context!!, android.R.layout.simple_dropdown_item_1line, list)
        towards_house_service_out.setAdapter(adapterAddress)

        towards_house_service_out.keyListener = null
            ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
        towards_house_service_out.onItemClickListener =
            AdapterView.OnItemClickListener { parent, _, position, _ ->
                towards_house_service_out.showDropDown()
                parent.getItemAtPosition(position).toString()
                towards_house_service_out.clearFocus()
            }
        towards_house_service_out.setOnClickListener {
            towards_house_service_out.showDropDown()
        }
        towards_house_service_out.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            try {
                if (hasFocus) {
                    towards_house_service_out.showDropDown()
                }
                if (!hasFocus && towards_house_service_out.text!!.isNotEmpty()) {
                    towards_house_service.defaultHintTextColor =
                        ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
                    towards_house_service.isErrorEnabled = false
                }
            } catch (e: Exception) {
            }
        }
        towards_house_service_out.clearFocus()
    }

}