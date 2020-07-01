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

class UserRequestFragment : Fragment() {
    private var mLastClickTime: Long = 0

    private var myAdapter =  UserRequestAdapter()

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
                    }, yearStart, monthStart, dayStart)
                picker.show()
                user_request_date_from_out.clearFocus()
            }
        }
    }
    private fun getAutoDatesTo() {
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
                        }, yearEnd, monthEnd, dayEnd
                    )
                picker.show()
                user_request_date_before_out.clearFocus()
            }
        }
    }
}