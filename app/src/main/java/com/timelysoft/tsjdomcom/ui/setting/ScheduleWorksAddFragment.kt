package com.timelysoft.tsjdomcom.ui.setting

import android.app.TimePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.TimePicker
import androidx.appcompat.app.AppCompatActivity
import com.timelysoft.tsjdomcom.R
import kotlinx.android.synthetic.main.fragment_schedule_works_add.*
import java.lang.Exception
import java.util.*

class ScheduleWorksAddFragment : Fragment() {
    private var update: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_schedule_works_add, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity?)!!.supportActionBar?.show()
        initArgument()
    }

    private fun initArgument() {
        update = try {
            arguments!!.getInt("zero")
        }catch (e: Exception){
            0
        }

        update = try {
            arguments!!.getInt("one")
        }catch (e: Exception){
            1
        }

        if (update == 0){
            (activity as AppCompatActivity?)!!.supportActionBar?.title = "Добавить"
        }else{
            (activity as AppCompatActivity?)!!.supportActionBar?.title = "Редактировать"
        }

        schedule_works_time_from_out.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                val dateAndTime = Calendar.getInstance()
                val t = TimePickerDialog.OnTimeSetListener { _: TimePicker?, hourOfDay: Int, minute: Int ->
                        dateAndTime[Calendar.HOUR_OF_DAY] = hourOfDay
                        dateAndTime[Calendar.MINUTE] = minute
                        if (minute < 10) {
                            if (hourOfDay < 10) {
                                schedule_works_time_from_out.setText("0$hourOfDay:0$minute")
                            } else {
                                schedule_works_time_from_out.setText("$hourOfDay:0$minute")
                            }
                        } else {
                            if (hourOfDay < 10) {
                                schedule_works_time_from_out.setText("0$hourOfDay:$minute")
                            } else {
                                schedule_works_time_from_out.setText("$hourOfDay:$minute")
                            }
                        }
                    }
                val timePickerDialog = TimePickerDialog(
                    activity, t,
                    dateAndTime[Calendar.HOUR_OF_DAY],
                    dateAndTime[Calendar.MINUTE], true
                )
                timePickerDialog.show()
            }
            false
        }

        schedule_works_time_time_to_out.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                val dateAndTime = Calendar.getInstance()
                val t = TimePickerDialog.OnTimeSetListener { _: TimePicker?, hourOfDay: Int, minute: Int ->
                    dateAndTime[Calendar.HOUR_OF_DAY] = hourOfDay
                    dateAndTime[Calendar.MINUTE] = minute
                    if (minute < 10) {
                        if (hourOfDay < 10) {
                            schedule_works_time_time_to_out.setText("0$hourOfDay:0$minute")
                        } else {
                            schedule_works_time_time_to_out.setText("$hourOfDay:0$minute")
                        }
                    } else {
                        if (hourOfDay < 10) {
                            schedule_works_time_time_to_out.setText("0$hourOfDay:$minute")
                        } else {
                            schedule_works_time_time_to_out.setText("$hourOfDay:$minute")
                        }
                    }
                }
                val timePickerDialog = TimePickerDialog(
                    activity, t,
                    dateAndTime[Calendar.HOUR_OF_DAY],
                    dateAndTime[Calendar.MINUTE], true
                )
                timePickerDialog.show()
            }
            false
        }
    }
}