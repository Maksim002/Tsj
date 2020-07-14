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
import androidx.appcompat.app.AppCompatActivity
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.utils.MyUtils
import kotlinx.android.synthetic.main.fragment_add_entry.*
import java.util.*

class AddEntryFragment : Fragment() {

    private var mLastClickTime: Long = 0

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
    }

    private fun getAddType() {
        val list = arrayOf("зайчик", "вышел", "погулять","вода")

        val adapterAddType = ArrayAdapter(context!!, android.R.layout.simple_dropdown_item_1line, list)
        add_entry_type_out.setAdapter(adapterAddType)

        add_entry_type_out.keyListener = null
        ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
        add_entry_type_out.onItemClickListener =
            AdapterView.OnItemClickListener { parent, _, position, _ ->
                add_entry_type_out.showDropDown()
                parent.getItemAtPosition(position).toString()
                add_entry_type_out.clearFocus()
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
        val list = arrayOf("зайчик", "вышел", "погулять","вода")

        val adapterAddDescription = ArrayAdapter(context!!, android.R.layout.simple_dropdown_item_1line, list)
        add_entry_type_description_out.setAdapter(adapterAddDescription)

        add_entry_type_description_out.keyListener = null
        ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
        add_entry_type_description_out.onItemClickListener =
            AdapterView.OnItemClickListener { parent, _, position, _ ->
                add_entry_type_description_out.showDropDown()
                parent.getItemAtPosition(position).toString()
                add_entry_type_description_out.clearFocus()
            }
        add_entry_type_description_out.setOnClickListener {
            add_entry_type_description_out.showDropDown()
        }
        add_entry_type_description_out.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            try {
                if (hasFocus) {
                    add_entry_type_description_out.showDropDown()
                }
                if (!hasFocus && add_entry_type_description_out.text!!.isNotEmpty()) {
                    add_entry_type_description.defaultHintTextColor =
                        ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
                    add_entry_type_description.isErrorEnabled = false
                }
            } catch (e: Exception) {
            }
        }
        add_entry_type_description_out.clearFocus()
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
                val picker =
                    DatePickerDialog(activity!!,
                        R.style.DatePicker, { _, year1, monthOfYear, dayOfMonth ->
                            add_entry_date_out.setText(MyUtils.convertDate(dayOfMonth, monthOfYear + 1, year1))
                        }, year, month, day)
                picker.show()
                add_entry_date_out.clearFocus()
            }
        }
    }
}