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
import androidx.navigation.Navigation
import com.example.tsj.R
import com.example.tsj.model.BookingRequest
import kotlinx.android.synthetic.main.fragment_new_llistener.*
import java.text.SimpleDateFormat
import java.util.*

class HistoryListFragment : Fragment() {

    private lateinit var to_showB: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var root = inflater.inflate(R.layout.fragment_new_llistener, container, false)

        to_showB = root.findViewById(R.id.show)

        to_showB.setOnClickListener { v->
            Navigation.findNavController(root).navigate(R.id.navigation_personal)
        }

        return root
    }

    override fun onStart() {
        super.onStart()

        getAutoAddress()
        getAutoService()
        getAutoOperation()

        getAutoDatesS()
        getAutoDatesDo()

    }
    private fun getAutoAddress(){
        val address = arrayOf("7 небо, Токомбаева, д.53/2 кв 11")
        val adapterP =
            ArrayAdapter<String>(context!!, android.R.layout.simple_dropdown_item_1line, address)
        autoAddress.setAdapter(adapterP)
        autoAddress.setKeyListener(null);

        autoAddress.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                autoAddress.showDropDown()
                val col = ColorStateList.valueOf(getResources().getColor(R.color.colorAccent))
                Address.defaultHintTextColor = col
                val selectedItem = parent.getItemAtPosition(position).toString()

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
        val service = arrayOf("Техобслуживание")
        val adapterP =
            ArrayAdapter<String>(context!!, android.R.layout.simple_dropdown_item_1line, service)
        autoService.setAdapter(adapterP)
        autoService.setKeyListener(null);

        autoService.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                autoService.showDropDown()
                val col = ColorStateList.valueOf(getResources().getColor(R.color.colorAccent))
                Service.defaultHintTextColor = col
                val selectedItem = parent.getItemAtPosition(position).toString()

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
        val operation = arrayOf("Платежи")
        val adapterO =
            ArrayAdapter<String>(context!!, android.R.layout.simple_dropdown_item_1line, operation)
        autoOperation.setAdapter(adapterO)
        autoOperation.setKeyListener(null);

        autoOperation.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                autoOperation.showDropDown()
                val col = ColorStateList.valueOf(getResources().getColor(R.color.colorAccent))
                Operation.defaultHintTextColor = col
                val selectedItem = parent.getItemAtPosition(position).toString()

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
