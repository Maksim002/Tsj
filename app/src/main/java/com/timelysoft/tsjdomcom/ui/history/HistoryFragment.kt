package com.timelysoft.tsjdomcom.ui.history


import android.app.DatePickerDialog
import android.content.res.ColorStateList
import android.os.Bundle
import android.os.SystemClock
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
import com.timelysoft.tsjdomcom.MainActivity
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.service.Status
import com.timelysoft.tsjdomcom.service.model.AddressModel
import com.timelysoft.tsjdomcom.service.model.OperationsModel
import com.timelysoft.tsjdomcom.service.model.ServicesModel
import com.timelysoft.tsjdomcom.utils.MyUtils
import kotlinx.android.synthetic.main.fragment_history.*
import kotlin.collections.ArrayList

class HistoryFragment : Fragment() {
    private lateinit var viewmodel: HistoryViewModel
    private var mLastClickTime: Long = 0

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
        if (history_address.text.toString().isEmpty()) {
            history_address_out.error = "Выберите адрес"
            valid = false
        } else {
            history_address_out.isErrorEnabled = false
        }

        if (history_service.text.toString().isEmpty()) {
            history_service_out.error = "Выберите услугу"
            valid = false
        } else {
            history_service_out.isErrorEnabled = false
        }

        if (history_operation.text.toString().isEmpty()) {
            history_operation_out.error = "Выберите операцию"
            valid = false
        } else {
            history_operation_out.isErrorEnabled = false
        }
        return valid
    }

    override fun onStart() {
        super.onStart()
        history_service.keyListener = null
        getAutoOperation()
        getAutoAddress()
        getAutoDatesFrom()
        getAutoDatesTo()
        getDate()
        hintText()
        if (history_address != null) {
            getAutoService()
        } else {
            getAutoAddress()
        }

        history_show.setOnClickListener {
            if (validate()) {
                val bundle = Bundle()
                bundle.putInt("res", licNumber)
                bundle.putString("serviceName", serviceName)
                bundle.putInt("servicesId", servicesId)

                bundle.putString("operationName", operationName)
                bundle.putInt("operationsId", operationsId)

                bundle.putString("address", address)
                bundle.putInt("placementId", placementId)

                bundle.putString("from", history_from_date.text.toString())
                bundle.putString("to", history_to_date.text.toString())
                Navigation.findNavController(it).navigate(R.id.navigation_personal, bundle)
            }
        }
    }

    private fun hintText() {
        if (history_address.text.isNotEmpty()) {
            history_address_out.defaultHintTextColor =
                ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
        }

        if (history_service.text.isNotEmpty()) {
            history_service_out.defaultHintTextColor =
                ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
        }
        if (history_operation.text.isNotEmpty()) {
            history_operation_out.defaultHintTextColor =
                ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
        }

        _history_goneL_owner.requestFocus()

    }

    private fun getDate() {
        MainActivity.alert.show()
        viewmodel.periods().observe(this, Observer { result ->
            val msg = result.msg
            val data = result.data
            MainActivity.alert.hide()
            when(result.status){
                Status.SUCCESS ->{
                    history_from_date.setText(MyUtils.toMyDate(data!!.from))
                    history_to_date.setText(MyUtils.toMyDate(data.to))
                    // Ковертация и присваевание
                    val (dayFrom, monthFrom, yearFrom) = MyUtils.dateConverting(data.from.toString())
                    dayStart = dayFrom
                    monthStart = monthFrom - 1
                    yearStart = yearFrom

                    val (dayTo, monthTo, yearTo) = MyUtils.dateConverting(data.to.toString())
                    dayEnd = dayTo
                    monthEnd = monthTo - 1
                    yearEnd = yearTo
                }
                Status.ERROR, Status.NETWORK ->{
                    Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun getAutoAddress() {
        var listAddress = ArrayList<AddressModel>()
        MainActivity.alert.show()
        viewmodel.addresses().observe(this, Observer { address ->
            val list = address.map {
                it.address
            }
            listAddress = address as ArrayList<AddressModel>

            val adapterAddress =
                ArrayAdapter<String>(context!!, android.R.layout.simple_dropdown_item_1line, list)
            history_address.setAdapter(adapterAddress)
            MainActivity.alert.hide()
        })
        history_address.keyListener = null
        history_from_out.defaultHintTextColor =
            ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
        history_do_out.defaultHintTextColor =
            ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
        history_address.onItemClickListener =
            AdapterView.OnItemClickListener { parent, _, position, _ ->
                history_address.showDropDown()
                history_address_out.defaultHintTextColor =
                    ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
                parent.getItemAtPosition(position).toString()
                placementId = listAddress[position].placementId
                licNumber = listAddress[position].licNumber
                address = listAddress[position].address
                MainActivity.alert.show()
                getAutoService()
                history_service.setAdapter(null)
                history_service.setText("")
                history_address.clearFocus()

            }
        history_address.setOnClickListener {
            history_address.showDropDown()
        }

        history_address.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            try {
                if (hasFocus) {
                    history_address.showDropDown()
                }

                if (!hasFocus && history_address.text!!.isNotEmpty()) {
                    history_service_out.defaultHintTextColor =
                        ColorStateList.valueOf(resources.getColor(R.color.itemIconTintF))

                    history_address_out.isErrorEnabled = false
                }

            } catch (e: Exception) {
            }
        }

        history_address.clearFocus()
    }

    private fun getAutoService() {
        var listServices = ArrayList<ServicesModel>()
        MainActivity.alert.show()
        viewmodel.services(placementId).observe(this, Observer { result ->
            val msg = result.msg
            val data = result.data
            MainActivity.alert.hide()
            when(result.status){
                Status.SUCCESS ->{
                    val list = data!!.map {
                        it.serviceName
                    }
                    listServices = data as ArrayList<ServicesModel>
                    val adapterServices = ArrayAdapter<String>(context!!, android.R.layout.simple_dropdown_item_1line, list)
                    adapterServices.notifyDataSetChanged()
                    history_service.setAdapter(adapterServices)
                }
                Status.ERROR, Status.NETWORK ->{
                    Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
                }
            }
        })

        history_service.onItemClickListener =
            AdapterView.OnItemClickListener { parent, _, position, _ ->
                history_service.showDropDown()
                parent.getItemAtPosition(position).toString()
                servicesId = listServices[position].serviceId
                serviceName = listServices[position].serviceName
                history_service.clearFocus()
            }
        history_service.setOnClickListener {
            history_service.showDropDown()
        }

        history_service.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            try {
                history_service.showDropDown()
                if (!hasFocus && history_address.text.isNotEmpty()) {
                    history_service_out.defaultHintTextColor =
                        ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
                    history_service_out.isErrorEnabled = false
                }

                if (history_address.text.isEmpty()) {
                    Toast.makeText(context, "Сначало выберте адрес", Toast.LENGTH_LONG).show()
                }

            } catch (e: Exception) {
            }
        }
        history_service.clearFocus()
    }

    private fun getAutoOperation() {
        var listOperations = ArrayList<OperationsModel>()
        MainActivity.alert.show()
        viewmodel.operations().observe(this, Observer { result->
            val msg = result.msg
            val data = result.data
            MainActivity.alert.hide()
            when(result.status){
                Status.SUCCESS ->{
                    val list = data!!.map {
                        it.operationName
                    }
                    listOperations = data as ArrayList<OperationsModel>
                    val adapterOperations =
                        ArrayAdapter<String>(context!!, android.R.layout.simple_dropdown_item_1line, list)
                    history_operation.setAdapter(adapterOperations)
                }
                Status.ERROR, Status.NETWORK ->{
                    Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
                }
            }
        })

        history_operation.setKeyListener(null)

        history_operation.onItemClickListener =
            AdapterView.OnItemClickListener { parent, _, position, _ ->
                history_operation.showDropDown()
                parent.getItemAtPosition(position).toString()
                operationsId = listOperations[position].operationId
                operationName = listOperations[position].operationName
                history_operation.clearFocus()
            }
        history_operation.setOnClickListener {
            history_operation.showDropDown()
        }
        history_operation.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            try {
                history_operation.showDropDown()
                if (!hasFocus && history_operation.text.isNotEmpty()) {
                    history_operation_out.defaultHintTextColor =
                        ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
                    history_operation_out.isErrorEnabled = false
                }
            } catch (e: Exception) {

            }

        }
        history_operation.clearFocus()
    }

    private fun getAutoDatesFrom() {
        history_from_date.keyListener = null;
        history_from_date.setOnFocusChangeListener setOnClickListener@{ _, hasFocus ->
            if (hasFocus) {
                if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
                    return@setOnClickListener
                }
                mLastClickTime = SystemClock.elapsedRealtime();
                history_from_out.defaultHintTextColor =
                    ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
                val picker =
                    DatePickerDialog(activity!!, { _, year1, monthOfYear, dayOfMonth ->
                        history_from_date.setText(
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
                history_from_date.clearFocus()
            }
        }
    }

    private fun getAutoDatesTo() {
        history_to_date.keyListener = null;
        history_to_date.setOnFocusChangeListener setOnClickListener@{ _, hasFocus ->
            if (hasFocus) {
                if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
                    return@setOnClickListener
                }
                mLastClickTime = SystemClock.elapsedRealtime();
                val col =
                    ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
                history_do_out.defaultHintTextColor = col

                val picker =
                    DatePickerDialog(
                        activity!!,
                        { _, year1, monthOfYear, dayOfMonth ->
                            history_to_date.setText(
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
                history_to_date.clearFocus()
            }
        }
    }
}
