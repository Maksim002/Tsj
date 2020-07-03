package com.timelysoft.tsjdomcom.ui.counter

import android.app.DatePickerDialog
import android.content.res.ColorStateList
import android.os.Bundle
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.adapters.changing.ChangingReadingAdapter
import com.timelysoft.tsjdomcom.adapters.changing.ChangingReadingModel
import com.timelysoft.tsjdomcom.utils.MyUtils
import kotlinx.android.synthetic.main.fragment_changing_reading.*
import java.util.*
import kotlin.collections.ArrayList

class ChangingReadingFragment : Fragment() {
    private var mLastClickTime: Long = 0

    private var myAdapter = ChangingReadingAdapter()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_changing_reading, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.show()
        getAutoDatesFrom()
        getAutoDatesTo()
        iniRecyclerView()

    }

    private fun iniRecyclerView() {
        val list: ArrayList<ChangingReadingModel> = arrayListOf()
        list.add(ChangingReadingModel(""))
        list.add(ChangingReadingModel(""))
        list.add(ChangingReadingModel(""))
        list.add(ChangingReadingModel(""))
        list.add(ChangingReadingModel(""))

        myAdapter.update(list)
        changing_reading_recycler.adapter = myAdapter
    }

    private fun getAutoDatesFrom() {
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MARCH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        changing_date_from_out.keyListener = null;
        changing_date_from_out.setOnFocusChangeListener setOnClickListener@{ _, hasFocus ->
            if (hasFocus) {
                if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
                    return@setOnClickListener
                }
                mLastClickTime = SystemClock.elapsedRealtime();
                changing_date_from.defaultHintTextColor =
                    ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
                val picker =
                    DatePickerDialog(activity!!,
                        R.style.DatePicker, { _, year, monthOfYear, dayOfMonth ->
                            changing_date_from_out.setText(MyUtils.convertDate(dayOfMonth, monthOfYear + 1, year))
                        }, year, month, day)

                picker.show()
                changing_date_from_out.clearFocus()
            }
        }
    }
    private fun getAutoDatesTo() {
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MARCH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        changing_date_date_to_out.keyListener = null;
        changing_date_date_to_out.setOnFocusChangeListener setOnClickListener@{ _, hasFocus ->
            if (hasFocus) {
                if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
                    return@setOnClickListener
                }
                mLastClickTime = SystemClock.elapsedRealtime();
                changing_date_date_to.defaultHintTextColor = ColorStateList.valueOf(resources.getColor(
                    R.color.colorAccent
                ))

                val picker =
                    DatePickerDialog(activity!!,
                        R.style.DatePicker, { _, year, monthOfYear, dayOfMonth ->
                            changing_date_date_to_out.setText(MyUtils.convertDate(dayOfMonth, monthOfYear + 1, year))
                        }, year, month, day
                    )
                picker.show()
                changing_date_date_to_out.clearFocus()
            }
        }
    }
}