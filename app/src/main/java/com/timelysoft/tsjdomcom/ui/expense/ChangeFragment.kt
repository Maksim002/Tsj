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
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.utils.MyUtils
import kotlinx.android.synthetic.main.fragment_change.*
import java.util.*

class ChangeFragment : Fragment() {

    private var mLastClickTime: Long = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_change, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getAddType()
        initArgument()
        getAddDescription()
        getAutoDatesFrom()

    }

    private fun initArgument() {
        val list = try {
            arguments!!.getSerializable("comings")
        } catch (e: java.lang.Exception) {
            ""
        }
    }

    private fun getAddType() {
        val list = arrayOf("зайчик", "вышел", "погулять","вода")

        val adapterAddType = ArrayAdapter(context!!, android.R.layout.simple_dropdown_item_1line, list)
        change_type_out.setAdapter(adapterAddType)

        change_type_out.keyListener = null
        ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
        change_type_out.onItemClickListener =
            AdapterView.OnItemClickListener { parent, _, position, _ ->
                change_type_out.showDropDown()
                parent.getItemAtPosition(position).toString()
                change_type_out.clearFocus()
            }
        change_type_out.setOnClickListener {
            change_type_out.showDropDown()
        }
        change_type_out.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            try {
                if (hasFocus) {
                    change_type_out.showDropDown()
                }
                if (!hasFocus && change_type_out.text!!.isNotEmpty()) {
                    change_type.defaultHintTextColor =
                        ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
                    change_type.isErrorEnabled = false
                }
            } catch (e: Exception) {
            }
        }
        change_type_out.clearFocus()
    }

    private fun getAddDescription() {
        val list = arrayOf("зайчик", "вышел", "погулять","вода")

        val adapterAddDescription = ArrayAdapter(context!!, android.R.layout.simple_dropdown_item_1line, list)
        change_description_out.setAdapter(adapterAddDescription)

        change_description_out.keyListener = null
        ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
        change_description_out.onItemClickListener =
            AdapterView.OnItemClickListener { parent, _, position, _ ->
                change_description_out.showDropDown()
                parent.getItemAtPosition(position).toString()
                change_description_out.clearFocus()
            }
        change_description_out.setOnClickListener {
            change_description_out.showDropDown()
        }
        change_description_out.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            try {
                if (hasFocus) {
                    change_description_out.showDropDown()
                }
                if (!hasFocus && change_description_out.text!!.isNotEmpty()) {
                    change_description.defaultHintTextColor =
                        ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
                    change_description.isErrorEnabled = false
                }
            } catch (e: Exception) {
            }
        }
        change_description_out.clearFocus()
    }

    private fun getAutoDatesFrom() {
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MARCH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        change_date_out.keyListener = null;
        change_date_out.setOnFocusChangeListener setOnClickListener@{ _, hasFocus ->
            if (hasFocus) {
                if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
                    return@setOnClickListener
                }
                mLastClickTime = SystemClock.elapsedRealtime();
                change_date.defaultHintTextColor =
                    ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
                val picker =
                    DatePickerDialog(activity!!,
                        R.style.DatePicker, { _, year1, monthOfYear, dayOfMonth ->
                            change_date_out.setText(MyUtils.convertDate(dayOfMonth, monthOfYear + 1, year1))
                        }, year, month, day)
                picker.show()
                change_date_out.clearFocus()
            }
        }
    }
}