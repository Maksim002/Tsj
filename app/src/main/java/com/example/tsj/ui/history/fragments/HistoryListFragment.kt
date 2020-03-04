package com.example.tsj.ui.history.fragments


import android.app.DatePickerDialog
import android.content.res.ColorStateList
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.example.tsj.R
import com.example.tsj.service.model.AddressModel
import com.example.tsj.service.model.OperationsModel
import com.example.tsj.service.model.PeriodsModel
import com.example.tsj.service.model.ServicesModel
import com.example.tsj.utils.MyUtils
import kotlinx.android.synthetic.main.fragment_new_llistener.*
import kotlinx.android.synthetic.main.item_references.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class HistoryListFragment : Fragment() {
    private lateinit var viewModelHistory: HistoryViewModel
    private lateinit var model: ServicesModel
    private lateinit var to_showB: Button

    private var placementId: Int = 0
    private var servicesId: Int = 0
    private var operationsId: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var root = inflater.inflate(R.layout.fragment_new_llistener, container, false)
        viewModelHistory = ViewModelProviders.of(this).get(HistoryViewModel::class.java)

        to_showB = root.findViewById(R.id.show)
        to_showB.setOnClickListener { v->
            Navigation.findNavController(root).navigate(R.id.navigation_personal)
        }

        return root
    }

    override fun onStart() {
        super.onStart()
        autoService.setKeyListener(null)
        getAutoAddress()
        getAutoOperation()
        getAutoDatesS()
        getAutoDatesDo()
        getDate()

        show.setOnClickListener {
            val a = placementId
            val b = servicesId
            val c = operationsId

            val to = MyUtils.toServerDate(autoDatesS.text.toString())
            val from= MyUtils.toServerDate(autoDatesDo.text.toString())
            println()
        }
    }

    private fun getDate() {
        viewModelHistory.periods().observe(this,androidx.lifecycle.Observer { periods ->
            autoDatesS.setText(MyUtils.toMyDate(periods.from!!))
            autoDatesDo.setText(MyUtils.toMyDate(periods.to!!))
        })
    }

    private fun getAutoAddress(){
        var listAddress = ArrayList<AddressModel>()
        viewModelHistory.addresses().observe(this, androidx.lifecycle.Observer { address ->
            val list = address.map {
                it.address
            }
            listAddress = address as ArrayList<AddressModel>

        val adapterP = ArrayAdapter<String>(context!!, android.R.layout.simple_dropdown_item_1line, list)
        autoAddress.setAdapter(adapterP)})
        autoAddress.setKeyListener(null)

        autoAddress.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                autoAddress.showDropDown()
                Address.defaultHintTextColor = ColorStateList.valueOf(getResources().getColor(R.color.colorAccent))
                parent.getItemAtPosition(position).toString()
                placementId = listAddress.get(position).placementId!!
                viewModelHistory.servicesB(placementId).observe(this,androidx.lifecycle.Observer {
                   getAutoService()
                })
            }
        autoAddress.setOnClickListener {
            autoAddress.showDropDown()
        }
        autoAddress.onFocusChangeListener = View.OnFocusChangeListener { view, b ->
            if (b) {
                try {
                    autoAddress.showDropDown()
                } catch (e: Exception) {
                    println()
                }
            }
        }
    }

    private fun getAutoService(){
        var listServices = ArrayList<ServicesModel>()
            viewModelHistory.servicesB(placementId).observe(this, androidx.lifecycle.Observer { services ->
                val list = services.map {
                    it.serviceName
                }
                listServices = services as ArrayList<ServicesModel>
                val adapterO = ArrayAdapter<String>(context!!, android.R.layout.simple_dropdown_item_1line, list)
                adapterO.notifyDataSetChanged()
                autoService.setAdapter(adapterO)})

        autoService.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                autoService.showDropDown()
                Service.defaultHintTextColor = ColorStateList.valueOf(getResources().getColor(R.color.colorAccent))
                servicesId = listServices.get(position).serviceId!!
            }
        autoService.setOnClickListener {
            autoService.showDropDown()
        }
        autoService.onFocusChangeListener = View.OnFocusChangeListener { view, b ->
            if (b) {
                try {
                    autoService.showDropDown()
                } catch (e: Exception) {
                    println()
                }
            }
        }
    }

    private fun getAutoOperation(){
        var listOperations = ArrayList<OperationsModel>()
        viewModelHistory.operations().observe(this, androidx.lifecycle.Observer { operations ->
            val list = operations.map {
                it.operationName
            }
            listOperations = operations as ArrayList<OperationsModel>
            val adapterP = ArrayAdapter<String>(context!!, android.R.layout.simple_dropdown_item_1line, list)
            autoOperation.setAdapter(adapterP)})
            autoOperation.setKeyListener(null);

        autoOperation.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                autoOperation.showDropDown()
                Operation.defaultHintTextColor = ColorStateList.valueOf(getResources().getColor(R.color.colorAccent))

                operationsId =  listOperations.get(position).operationId!!

            }
        autoOperation.setOnClickListener {
            autoOperation.showDropDown()
        }
        autoOperation.onFocusChangeListener = View.OnFocusChangeListener { view, b ->
            if (b) {
                try {
                    autoOperation.showDropDown()
                } catch (e: Exception) {
                    println()
                }
            }
        }
    }

    private fun getAutoDatesS(){
        autoDatesS.setKeyListener(null);
        autoDatesS.setOnFocusChangeListener { view, b ->
            if (b) {
                val cldr = Calendar.getInstance()
                val day = cldr.get(Calendar.DAY_OF_MONTH)
                val month = cldr.get(Calendar.MONTH)
                val year = cldr.get(Calendar.YEAR)
                val picker: DatePickerDialog
                val col = ColorStateList.valueOf(getResources().getColor(R.color.colorAccent))
                DatesS.defaultHintTextColor = col
                picker =
                    DatePickerDialog(activity!!, { datePicker, year1, monthOfYear, dayOfMonth ->
                        if (monthOfYear + 1 < 10) {
                            autoDatesS.setText(dayOfMonth.toString() + "." + "0" + (monthOfYear + 1) + "." + year1)
                        } else {
                            autoDatesS.setText(dayOfMonth.toString() + "." + "0" + (monthOfYear + 1) + "." + year1)
                        }
                    }, year, month, day)
                picker.show()
                goneL.requestFocus()
            }
        }
    }

    private fun getAutoDatesDo(){
        autoDatesDo.setKeyListener(null);
        autoDatesDo.setOnFocusChangeListener { view, b ->
            if (b){
                if (autoDatesS.text.length == 0) {
                    Toast.makeText(context, "Error", Toast.LENGTH_LONG).show()
                } else {
                    if (b) {
                        val cldr = Calendar.getInstance()
                        val day = cldr.get(Calendar.DAY_OF_MONTH)
                        val month = cldr.get(Calendar.MONTH)
                        val year = cldr.get(Calendar.YEAR)
                        val col = ColorStateList.valueOf(getResources().getColor(R.color.colorAccent))
                        DatesDo.defaultHintTextColor = col
                        val picker: DatePickerDialog
                        picker =
                            DatePickerDialog(activity!!, { datePicker, year1, monthOfYear, dayOfMonth ->
                                if (monthOfYear + 1 < 10) {
                                    autoDatesDo.setText(dayOfMonth.toString() + "." + "0" + (monthOfYear + 1) + "." + year1)
                                } else {
                                    autoDatesDo.setText(dayOfMonth.toString() + "." + "0" + (monthOfYear + 1) + "." + year1)
                                }

                            }, year, month, day)
                        try {
                            val timeS = SimpleDateFormat("dd/MM/yyyy").parse(autoDatesS.text.toString()).getTime()
                            picker.datePicker.minDate = timeS + 1000

                        }catch (e:Exception){
                            picker.datePicker.minDate = System.currentTimeMillis() - 1000
                        }

                        picker.show()
                        goneL.requestFocus()
                    }
                }
            }
        }
    }
}
