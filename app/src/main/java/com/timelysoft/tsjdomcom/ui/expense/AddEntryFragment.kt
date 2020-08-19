package com.timelysoft.tsjdomcom.ui.expense

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
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.service.Status
import com.timelysoft.tsjdomcom.service.model.expense.ChangeListManagers
import com.timelysoft.tsjdomcom.service.model.expense.ChangeListType
import com.timelysoft.tsjdomcom.service.model.expense.EntryAddModel
import com.timelysoft.tsjdomcom.ui.main.MainActivity
import com.timelysoft.tsjdomcom.utils.MyUtils
import kotlinx.android.synthetic.main.fragment_add_entry.*
import kotlinx.android.synthetic.main.fragment_add_invoice.*
import kotlinx.android.synthetic.main.fragment_change.*
import java.util.*
import kotlin.collections.ArrayList

class AddEntryFragment : Fragment() {
    private var viewModel = ExpenseViewModel()
    private var mLastClickTime: Long = 0
    private var typeId: Int = 0
    private var providerId: Int = 0
    private lateinit var model: EntryAddModel
    private var data: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_entry, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.show()
        getAddType()
        getAutoDatesFrom()
        getAddDescription()
        iniRequest()
        hintText()
    }

    private fun iniRequest() {
        expanse_receipts_add_entry.setOnClickListener {
            if (validate()) {
                MainActivity.alert.show()
                val amount = add_entry_sum_out.text.toString()
                model = EntryAddModel(
                    providerId,
                    amount.toInt(),
                    typeId,
                    data,
                    add_entry_description_out.text.toString()
                )
                viewModel.entryAdd(model)
                    .observe(viewLifecycleOwner, androidx.lifecycle.Observer { result ->
                        val msg = result.msg
                        when (result.status) {
                            Status.SUCCESS -> {
                                findNavController().popBackStack()
                                Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
                            }
                            Status.ERROR, Status.NETWORK -> {
                                Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
                            }
                        }
                        MainActivity.alert.hide()
                    })
            }
        }

    }

    private fun getAddType() {
        var list: ArrayList<ChangeListType> = arrayListOf()
        viewModel.changeListType()
            .observe(viewLifecycleOwner, androidx.lifecycle.Observer { result ->
                val msg = result.msg
                val data = result.data
                when (result.status) {
                    Status.SUCCESS -> {
                        val adapterAddType = ArrayAdapter(
                            context!!,
                            android.R.layout.simple_dropdown_item_1line,
                            data!!
                        )
                        add_entry_type_out.setAdapter(adapterAddType)
                        list = data
                    }
                    Status.ERROR, Status.NETWORK -> {
                        Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
                    }
                }
            })

        add_entry_type_out.keyListener = null
        ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
        add_entry_type_out.onItemClickListener =
            AdapterView.OnItemClickListener { parent, _, position, _ ->
                add_entry_type_out.showDropDown()
                parent.getItemAtPosition(position).toString()
                add_entry_type_out.clearFocus()
                typeId = list[position].id!!
            }
        add_entry_type_out.setOnClickListener {
            add_entry_type_out.showDropDown()
        }
        add_entry_type_out.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            try {
                if (hasFocus) {
                    add_entry_type_out.showDropDown()
                }
                if (!hasFocus && add_entry_type_out.text!!.isNotEmpty()) {
                    add_entry_type.defaultHintTextColor =
                        ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
                    add_entry_type.isErrorEnabled = false
                }
            } catch (e: Exception) {
            }
        }
        add_entry_type_out.clearFocus()
    }

    private fun getAddDescription() {
        var list: ArrayList<ChangeListManagers> = arrayListOf()
        viewModel.changeListManagers()
            .observe(viewLifecycleOwner, androidx.lifecycle.Observer { result ->
                val msg = result.msg
                val data = result.data
                when (result.status) {
                    Status.SUCCESS -> {
                        val adapterChangeProvider = ArrayAdapter(
                            context!!,
                            android.R.layout.simple_dropdown_item_1line,
                            data!!
                        )
                        add_entry_type_provider_out.setAdapter(adapterChangeProvider)
                        list = data
                    }
                    Status.ERROR, Status.NETWORK -> {
                        Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
                    }
                }
            })
        add_entry_type_provider_out.keyListener = null
        ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
        add_entry_type_provider_out.onItemClickListener =
            AdapterView.OnItemClickListener { parent, _, position, _ ->
                add_entry_type_provider_out.showDropDown()
                parent.getItemAtPosition(position).toString()
                add_entry_type_provider_out.clearFocus()
                providerId = list[position].id!!
            }
        add_entry_type_provider_out.setOnClickListener {
            add_entry_type_provider_out.showDropDown()
        }
        add_entry_type_provider_out.onFocusChangeListener =
            View.OnFocusChangeListener { view, hasFocus ->
                try {
                    if (hasFocus) {
                        add_entry_type_provider_out.showDropDown()
                    }
                    if (!hasFocus && add_entry_type_provider_out.text!!.isNotEmpty()) {
                        add_entry_type_provider.defaultHintTextColor =
                            ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
                        add_entry_type_provider.isErrorEnabled = false
                    }
                } catch (e: Exception) {
                }
            }
        add_entry_type_provider_out.clearFocus()
    }

    private fun getAutoDatesFrom() {
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MARCH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        add_entry_date_out.keyListener = null;
        add_entry_date_out.setOnFocusChangeListener setOnClickListener@{ _, hasFocus ->
            if (hasFocus) {
                if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
                    return@setOnClickListener
                }
                mLastClickTime = SystemClock.elapsedRealtime();
                add_entry_date.defaultHintTextColor =
                    ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
                val picker = DatePickerDialog(
                    activity!!,
                    R.style.DatePicker,
                    { _, year1, monthOfYear, dayOfMonth ->
                        add_entry_date_out.setText(
                            MyUtils.convertDate(
                                dayOfMonth,
                                monthOfYear + 1,
                                year1
                            )
                        )
                        data = (MyUtils.convertDateServer(year1, monthOfYear + 1, dayOfMonth))
                    },
                    year,
                    month,
                    day
                )
                picker.show()
                add_entry_date_out.clearFocus()
            }
        }
    }

    private fun hintText() {
        add_entry_sum_out.addTextChangedListener {
            add_entry_sum.isErrorEnabled = false
            add_entry_sum.defaultHintTextColor =
                ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
        }

        add_entry_date_out.addTextChangedListener {
            add_entry_date.isErrorEnabled = false
            add_entry_date.defaultHintTextColor =
                ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
        }

        add_entry_description_out.addTextChangedListener {
            add_entry_description.isErrorEnabled = false
            add_entry_description.defaultHintTextColor =
                ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
        }
    }

    private fun validate(): Boolean {
        var valid = true
        if (add_entry_type_provider_out.text!!.toString().isEmpty()) {
            add_entry_type_provider.error = "Поле не должно быть пустым"
            valid = false
        } else {
            add_entry_type_provider.isErrorEnabled = false
        }

        if (add_entry_sum_out.text!!.toString().isEmpty()) {
            add_entry_sum.error = "Поле не должно быть пустым"
            valid = false
        } else {
            add_entry_sum.isErrorEnabled = false
        }

        if (add_entry_date_out.text!!.toString().isEmpty()) {
            add_entry_date.error = "Поле не должно быть пустым"
            valid = false
        } else {
            add_entry_date.isErrorEnabled = false
        }

        if (add_entry_description_out.text!!.toString().isEmpty()) {
            add_entry_description.error = "Поле не должно быть пустым"
            valid = false
        } else {
            add_entry_description.isErrorEnabled = false
        }

        if (add_entry_type_out.text!!.toString().isEmpty()) {
            add_entry_type.error = "Поле не должно быть пустым"
            valid = false
        } else {
            add_entry_type.isErrorEnabled = false
        }
        return valid
    }
}