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
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.example.tsj.R
import com.example.tsj.service.model.AddressModel
import com.example.tsj.service.model.OperationsModel
import com.example.tsj.service.model.ServicesModel
import com.example.tsj.utils.MyUtils
import kotlinx.android.synthetic.main.fragment_new_llistener.*
import java.text.SimpleDateFormat
import java.util.Calendar
import kotlin.collections.ArrayList

class HistoryListFragment : Fragment() {
    private lateinit var viewmodel: HistoryViewModel

    private var operationName: String? = null
    private var operationsId: Int = 0

    private var serviceName: String? = null
    private var servicesId: Int = 0

    private var address: String? = null
    private var placementId: Int = 0

    private var licNumber: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var root = inflater.inflate(R.layout.fragment_new_llistener, container, false)
        viewmodel = ViewModelProviders.of(this).get(HistoryViewModel::class.java)

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

        if (autoAddress != null){
            getAutoService()
        }else{
            getAutoAddress()
        }

        show.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("res", licNumber)
            bundle.putString("serviceName", serviceName)
            bundle.putInt("servicesId", servicesId)

            bundle.putString("operationName", operationName)
            bundle.putInt("operationsId", operationsId)

            bundle.putString("address", address)
            bundle.putInt("placementId",placementId)

            bundle.putString("to", autoDatesS.text.toString())
            bundle.putString("from", autoDatesDo.text.toString())
            Navigation.findNavController(it).navigate(R.id.navigation_personal, bundle)
        }
    }

    private fun getDate() {
        viewmodel.periods().observe(this, Observer { periods ->
            autoDatesS.setText(MyUtils.toMyDate(periods.from!!))
            autoDatesDo.setText(MyUtils.toMyDate(periods.to!!))
        })
    }

    private fun getAutoAddress() {
        var listAddress = ArrayList<AddressModel>()
        viewmodel.addresses().observe(this, Observer { address ->
            val list = address.map {
                it.address
            }
            listAddress = address as ArrayList<AddressModel>

            val adapterAddress =
                ArrayAdapter<String>(context!!, android.R.layout.simple_dropdown_item_1line, list)
            autoAddress.setAdapter(adapterAddress)
        })
        autoAddress.setKeyListener(null)

        autoAddress.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                autoAddress.showDropDown()
                Address.defaultHintTextColor =
                    ColorStateList.valueOf(getResources().getColor(R.color.colorAccent))
                parent.getItemAtPosition(position).toString()
                placementId = listAddress.get(position).placementId!!
                licNumber = listAddress.get(position).licNumber!!
                address = listAddress.get(position).address!!
                viewmodel.services(placementId).observe(this, Observer {
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
        })


        autoService.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                autoService.showDropDown()
                Service.defaultHintTextColor =
                    ColorStateList.valueOf(getResources().getColor(R.color.colorAccent))
                parent.getItemAtPosition(position).toString()
                servicesId = listServices.get(position).serviceId!!
                serviceName = listServices.get(position).serviceName!!
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

    private fun getAutoOperation() {
        var listOperations = ArrayList<OperationsModel>()
        viewmodel.operations().observe(this, Observer { operations ->
            val list = operations.map {
                it.operationName
            }
            listOperations = operations as ArrayList<OperationsModel>
            val adapterOperations =
                ArrayAdapter<String>(context!!, android.R.layout.simple_dropdown_item_1line, list)
            autoOperation.setAdapter(adapterOperations)
        })
        autoOperation.setKeyListener(null);

        autoOperation.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                autoOperation.showDropDown()
                Operation.defaultHintTextColor =
                    ColorStateList.valueOf(getResources().getColor(R.color.colorAccent))
                parent.getItemAtPosition(position).toString()
                operationsId = listOperations.get(position).operationId!!
                operationName = listOperations.get(position).operationName!!

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

    private fun getAutoDatesS() {
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

    private fun getAutoDatesDo() {
        autoDatesDo.setKeyListener(null);
        autoDatesDo.setOnFocusChangeListener { view, b ->
            if (b) {
                if (autoDatesS.text.length == 0) {
                    Toast.makeText(context, "Error", Toast.LENGTH_LONG).show()
                } else {
                    if (b) {
                        val cldr = Calendar.getInstance()
                        val day = cldr.get(Calendar.DAY_OF_MONTH)
                        val month = cldr.get(Calendar.MONTH)
                        val year = cldr.get(Calendar.YEAR)
                        val col =
                            ColorStateList.valueOf(getResources().getColor(R.color.colorAccent))
                        DatesDo.defaultHintTextColor = col
                        val picker: DatePickerDialog
                        picker =
                            DatePickerDialog(
                                activity!!,
                                { datePicker, year1, monthOfYear, dayOfMonth ->
                                    if (monthOfYear + 1 < 10) {
                                        autoDatesDo.setText(dayOfMonth.toString() + "." + "0" + (monthOfYear + 1) + "." + year1)
                                    } else {
                                        autoDatesDo.setText(dayOfMonth.toString() + "." + "0" + (monthOfYear + 1) + "." + year1)
                                    }

                                },
                                year,
                                month,
                                day
                            )
                        try {
                            val timeS =
                                SimpleDateFormat("dd/MM/yyyy").parse(autoDatesS.text.toString())
                                    .getTime()
                            picker.datePicker.minDate = timeS + 1000

                        } catch (e: Exception) {
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
