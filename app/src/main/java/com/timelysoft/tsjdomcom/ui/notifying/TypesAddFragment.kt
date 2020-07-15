package com.timelysoft.tsjdomcom.ui.notifying

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
import kotlinx.android.synthetic.main.fragment_types_add.*
import java.util.*

class TypesAddFragment : Fragment() {

    private var mLastClickTime: Long = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_types_add, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.show()
        getTypesType()
        getAutoDatesFrom()
    }

    private fun getAutoDatesFrom() {
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MARCH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        types_date_out.keyListener = null;
        types_date_out.setOnFocusChangeListener setOnClickListener@{ _, hasFocus ->
            if (hasFocus) {
                if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
                    return@setOnClickListener
                }
                mLastClickTime = SystemClock.elapsedRealtime();
                types_date.defaultHintTextColor =
                    ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
                val picker =
                    DatePickerDialog(activity!!,
                        R.style.DatePicker, { _, year1, monthOfYear, dayOfMonth ->
                            types_date_out.setText(MyUtils.convertDate(dayOfMonth, monthOfYear + 1, year1))
                        }, year, month, day)
                picker.show()
                types_date_out.clearFocus()
            }
        }
    }

    private fun getTypesType() {
        val list = arrayOf("зайчик", "вышел", "погулять","водаyyy")

        val adapterTypesType = ArrayAdapter(context!!, android.R.layout.simple_dropdown_item_1line, list)
        types_type_out.setAdapter(adapterTypesType)

        types_type_out.keyListener = null
        ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
        types_type_out.onItemClickListener =
            AdapterView.OnItemClickListener { parent, _, position, _ ->
                types_type_out.showDropDown()
                parent.getItemAtPosition(position).toString()
                types_type_out.clearFocus()
            }
        types_type_out.setOnClickListener {
            types_type_out.showDropDown()
        }
        types_type_out.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            try {
                if (hasFocus) {
                    types_type_out.showDropDown()
                }
                if (!hasFocus && types_type_out.text!!.isNotEmpty()) {
                    types_type.defaultHintTextColor =
                        ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
                    types_type.isErrorEnabled = false
                }
            } catch (e: Exception) {
            }
        }
        types_type_out.clearFocus()
    }
}