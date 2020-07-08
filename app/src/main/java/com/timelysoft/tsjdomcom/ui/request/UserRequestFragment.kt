package com.timelysoft.tsjdomcom.ui.request

import android.app.DatePickerDialog
import android.content.res.ColorStateList
import android.os.Bundle
import android.os.SystemClock
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.adapters.request.UserRequestAdapter
import com.timelysoft.tsjdomcom.adapters.request.UserRequestModel
import com.timelysoft.tsjdomcom.utils.MyUtils
import kotlinx.android.synthetic.main.fragment_user_request.*
import java.util.*
import kotlin.collections.ArrayList

class UserRequestFragment : Fragment() {
    private var mLastClickTime: Long = 0

    private var myAdapter =  UserRequestAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_request, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.show()
        getAutoDatesFrom()
        getAutoDatesTo()
        initRecycler()
    }

    private fun initRecycler() {
        val list: ArrayList<UserRequestModel> = arrayListOf()
        list.add(UserRequestModel(""))
        list.add(UserRequestModel(""))
        list.add(UserRequestModel(""))
        list.add(UserRequestModel(""))

        myAdapter.update(list)
        user_request_recycler.adapter = myAdapter
    }

    private fun getAutoDatesFrom() {
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MARCH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        user_request_date_from_out.keyListener = null;
        user_request_date_from_out.setOnFocusChangeListener setOnClickListener@{ _, hasFocus ->
            if (hasFocus) {
                if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
                    return@setOnClickListener
                }
                mLastClickTime = SystemClock.elapsedRealtime();
                user_request_date_from.defaultHintTextColor =
                    ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
                val picker =
                    DatePickerDialog(activity!!,
                        R.style.DatePicker, { _, year1, monthOfYear, dayOfMonth ->
                        user_request_date_from_out.setText(MyUtils.convertDate(dayOfMonth, monthOfYear + 1, year1))
                    }, year, month, day)
                picker.show()
                user_request_date_from_out.clearFocus()
            }
        }
    }
    private fun getAutoDatesTo() {
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MARCH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        user_request_date_before_out.keyListener = null;
        user_request_date_before_out.setOnFocusChangeListener setOnClickListener@{ _, hasFocus ->
            if (hasFocus) {
                if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
                    return@setOnClickListener
                }
                mLastClickTime = SystemClock.elapsedRealtime();
                val col =
                    ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
                user_request_date_before.defaultHintTextColor = col

                val picker =
                    DatePickerDialog(activity!!,
                        R.style.DatePicker, { _, year1, monthOfYear, dayOfMonth ->
                        user_request_date_before_out.setText(MyUtils.convertDate(dayOfMonth, monthOfYear + 1, year1))
                        }, year, month, day
                    )
                picker.show()
                user_request_date_before_out.clearFocus()
            }
        }
    }
}