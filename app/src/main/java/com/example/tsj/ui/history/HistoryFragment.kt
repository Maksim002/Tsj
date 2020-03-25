package com.example.tsj.ui.history


import android.app.DatePickerDialog
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
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.example.tsj.MainActivity
import com.example.tsj.R
import com.example.tsj.service.model.AddressModel
import com.example.tsj.service.model.OperationsModel
import com.example.tsj.service.model.ServicesModel
import com.example.tsj.utils.MyUtils
import kotlinx.android.synthetic.main.fragment_history.*
import kotlin.collections.ArrayList

class HistoryFragment : Fragment() {
    private lateinit var viewmodel: HistoryViewModel

    private var operationName: String? = null
    private var operationsId: Int = 0

    private var serviceName: String? = null
    private var servicesId: Int = 0

    private var address: String? = null
    private var placementId: Int = 0

    private var licNumber: Int = 0

    private var dayStart: Int = 0
    private var monthStart: Int = 0
    private var yearStart: Int = 0

    private var dayEnd: Int = 0
    private var monthEnd: Int = 0
    private var yearEnd: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_history, container, false)
        viewmodel = ViewModelProviders.of(this).get(HistoryViewModel::class.java)

        (activity as AppCompatActivity).supportActionBar?.show()
        return root
    }

    private fun validate(): Boolean {
        var valid = true
        if (autoAddress.text.toString().isEmpty()) {
            Address.error = "Выберите адрес"
            valid = false
        } else {
            Address.isErrorEnabled = false
        }

        if (autoService.text.toString().isEmpty()) {
            Service.error = "Выберите услугу"
            valid = false
        } else {
            Service.isErrorEnabled = false
        }

        if (autoOperation.text.toString().isEmpty()) {
            Operation.error = "Выберите операцию"
            valid = false
        } else {
            Operation.isErrorEnabled = false
        }

        return valid
    }

    override fun onStart() {
        super.onStart()
        autoService.keyListener = null
        getAutoOperation()
        getAutoAddress()
        getAutoDatesFrom()
        getAutoDatesTo()
        getDate()
        hintText()
        if (autoAddress != null) {
            getAutoService()
        } else {
            getAutoAddress()
        }

        show.setOnClickListener {
            if (validate()){
                    val bundle = Bundle()
                    bundle.putInt("res", licNumber)
                    bundle.putString("serviceName", serviceName)
                    bundle.putInt("servicesId", servicesId)

                    bundle.putString("operationName", operationName)
                    bundle.putInt("operationsId", operationsId)

                    bundle.putString("address", address)
                    bundle.putInt("placementId", placementId)

                    bundle.putString("to", autoDateFrom.text.toString())
                    bundle.putString("from", autoDateTo.text.toString())
                    Navigation.findNavController(it).navigate(R.id.navigation_personal, bundle)
            }
        }
    }

    private fun hintText() {
        if (autoAddress.text.isNotEmpty()) {
            Address.defaultHintTextColor =
                ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
            Service.defaultHintTextColor =
                ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
            Operation.defaultHintTextColor =
                ColorStateList.valueOf(resources.getColor(R.color.colorAccent))

        }

    }

    private fun getDate() {
        viewmodel.periods().observe(this, Observer { periods ->
            autoDateFrom.setText(MyUtils.toMyDate(periods.from!!))
            autoDateTo.setText(MyUtils.toMyDate(periods.to!!))
            // Ковертация и присваевание
            val (dayFrom, monthFrom, yearFrom) = MyUtils.dateConverting(periods.from.toString())
            dayStart = dayFrom
            monthStart = monthFrom - 1
            yearStart = yearFrom

            val (dayTo, monthTo, yearTo) = MyUtils.dateConverting(periods.to.toString())
            dayEnd = dayTo
            monthEnd = monthTo - 1
            yearEnd = yearTo
        })
    }

    private fun getAutoAddress() {
        var listAddress = ArrayList<AddressModel>()
        viewmodel.addresses().observe(this, Observer { address ->
            val list = address.map {
                it.address
            }
            listAddress = address as ArrayList<AddressModel>

            val adapterAddress = ArrayAdapter<String>(context!!, android.R.layout.simple_dropdown_item_1line, list)
            autoAddress.setAdapter(adapterAddress)
            MainActivity.alert.hide()
        })
        autoAddress.keyListener = null
        DatesS.defaultHintTextColor =
            ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
        DatesDo.defaultHintTextColor =
            ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
        autoAddress.onItemClickListener =
            AdapterView.OnItemClickListener { parent, _, position, _ -> autoAddress.showDropDown()
                Address.defaultHintTextColor = ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
                parent.getItemAtPosition(position).toString()
                placementId = listAddress[position].placementId!!
                licNumber = listAddress[position].licNumber!!
                address = listAddress[position].address!!
                MainActivity.alert.show()
                getAutoService()
                autoService.setAdapter(null)
                autoService.setText("")

            }
        autoAddress.setOnClickListener {
            autoAddress.showDropDown()
        }
        autoAddress.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
                try {
                    autoAddress.showDropDown()

                    if (hasFocus || autoAddress.text!!.isNotEmpty()) {
                        Service.defaultHintTextColor =
                            ColorStateList.valueOf(resources.getColor(R.color.itemIconTintF))
                    }

                } catch (e: Exception) {
            }
        }
    }

    private fun getAutoService() {
        var listServices = ArrayList<ServicesModel>()
        viewmodel.services(placementId).observe(this, Observer { services ->

            val list = services.map {
                it.serviceName
            }
            listServices = services as ArrayList<ServicesModel>
            val adapterServices =
                ArrayAdapter<String>(context!!, android.R.layout.simple_dropdown_item_1line, list)
            adapterServices.notifyDataSetChanged()
            autoService.setAdapter(adapterServices)
            MainActivity.alert.hide()
        })


        autoService.onItemClickListener =
            AdapterView.OnItemClickListener { parent, _, position, _ ->
                autoService.showDropDown()
                parent.getItemAtPosition(position).toString()
                servicesId = listServices[position].serviceId!!
                serviceName = listServices[position].serviceName!!
            }
        autoService.setOnClickListener {
            autoService.showDropDown()

        }
        autoService.onFocusChangeListener = View.OnFocusChangeListener { _, _ ->
                try {
                    autoService.showDropDown()
                    Service.defaultHintTextColor =
                        ColorStateList.valueOf(resources.getColor(R.color.colorAccent))

                    if (autoAddress.text.isEmpty()){
                        Toast.makeText(context, "Сначало выберте адрес", Toast.LENGTH_LONG).show()
                    }

                } catch (e: Exception) {
            }
        }
    }

    private fun getAutoOperation() {
        var listOperations = ArrayList<OperationsModel>()
        viewmodel.operations().observe(this, Observer { operations ->
            val list = operations.map {
                it.operationName
            }
            listOperations = operations as ArrayList<OperationsModel>
            val adapterOperations = ArrayAdapter<String>(context!!, android.R.layout.simple_dropdown_item_1line, list)
            autoOperation.setAdapter(adapterOperations)
        })
        autoOperation.setKeyListener(null);

        autoOperation.onItemClickListener =
            AdapterView.OnItemClickListener { parent, _, position, _ ->
                autoOperation.showDropDown()
                Operation.defaultHintTextColor =
                    ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
                parent.getItemAtPosition(position).toString()
                operationsId = listOperations[position].operationId!!
                operationName = listOperations[position].operationName!!

            }
        autoOperation.setOnClickListener {
            autoOperation.showDropDown()
        }
        autoOperation.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
                try {
                    autoOperation.showDropDown()
                } catch (e: Exception) {
            }
        }
    }

    private fun getAutoDatesFrom() {
        autoDateFrom.keyListener = null;
        autoDateFrom.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                DatesS.defaultHintTextColor =
                    ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
                val picker =
                    DatePickerDialog(activity!!, { _, year1, monthOfYear, dayOfMonth ->
                        autoDateFrom.setText(
                            MyUtils.convertDate(
                                dayOfMonth,
                                monthOfYear + 1,
                                year1
                            )
                        )
                        dayStart = dayOfMonth
                        monthStart = monthOfYear
                        yearStart = year1

                    }, yearStart, monthStart, dayStart)
                picker.show()
                goneL.requestFocus()
            }
        }
    }

    private fun getAutoDatesTo() {
        autoDateTo.keyListener = null;
        autoDateTo.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                val col =
                    ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
                DatesDo.defaultHintTextColor = col

                val picker =
                    DatePickerDialog(
                        activity!!,
                        { _, year1, monthOfYear, dayOfMonth ->
                            autoDateTo.setText(
                                MyUtils.convertDate(
                                    dayOfMonth,
                                    monthOfYear + 1,
                                    year1
                                )
                            )
                            dayEnd = dayOfMonth
                            monthEnd = monthOfYear
                            yearEnd = year1

                        }, yearEnd, monthEnd, dayEnd
                    )
                picker.show()
                goneL.requestFocus()
            }
        }
    }
}
