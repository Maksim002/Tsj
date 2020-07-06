package com.timelysoft.tsjdomcom.ui.counter

import android.app.DatePickerDialog
import android.content.res.ColorStateList
import android.os.Bundle
import android.os.SystemClock
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.adapters.explanation.ExplanationHistoryAdapter
import com.timelysoft.tsjdomcom.adapters.explanation.ExplanationHistoryModel
import com.timelysoft.tsjdomcom.utils.MyUtils
import kotlinx.android.synthetic.main.fragment_explanation_history.*
import java.util.*
import kotlin.collections.ArrayList

class ExplanationHistoryFragment : Fragment() {

    private var mLastClickTime: Long = 0

    private var myAdapter = ExplanationHistoryAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_explanation_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        getAutoDatesFrom()
        getAutoDatesTo()
    }

    private fun initRecyclerView() {

        val list:ArrayList<ExplanationHistoryModel> = arrayListOf()

        list.add(ExplanationHistoryModel(""))
        list.add(ExplanationHistoryModel(""))
        list.add(ExplanationHistoryModel(""))
        list.add(ExplanationHistoryModel(""))
        list.add(ExplanationHistoryModel(""))

        myAdapter.update(list)
        explanation_recycler.adapter = myAdapter
    }

    private fun getAutoDatesFrom() {
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MARCH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        explanation_date_from_out.keyListener = null;
        explanation_date_from_out.setOnFocusChangeListener setOnClickListener@{ _, hasFocus ->
            if (hasFocus) {
                if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
                    return@setOnClickListener
                }
                mLastClickTime = SystemClock.elapsedRealtime();
                explanation_date_from.defaultHintTextColor =
                    ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
                val picker =
                    DatePickerDialog(activity!!,
                        R.style.DatePicker, { _, year, monthOfYear, dayOfMonth ->
                            explanation_date_from_out.setText(MyUtils.convertDate(dayOfMonth, monthOfYear + 1, year))
                        }, year, month, day)

                picker.show()
                explanation_date_from_out.clearFocus()
            }
        }
    }
    private fun getAutoDatesTo() {
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MARCH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        explanation_date_date_to_out.keyListener = null;
        explanation_date_date_to_out.setOnFocusChangeListener setOnClickListener@{ _, hasFocus ->
            if (hasFocus) {
                if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
                    return@setOnClickListener
                }
                mLastClickTime = SystemClock.elapsedRealtime();
                explanation_date_date_to.defaultHintTextColor = ColorStateList.valueOf(resources.getColor(
                    R.color.colorAccent
                ))

                val picker =
                    DatePickerDialog(activity!!,
                        R.style.DatePicker, { _, year, monthOfYear, dayOfMonth ->
                            explanation_date_date_to_out.setText(MyUtils.convertDate(dayOfMonth, monthOfYear + 1, year))
                        }, year, month, day
                    )
                picker.show()
                explanation_date_date_to_out.clearFocus()
            }
        }
    }
}