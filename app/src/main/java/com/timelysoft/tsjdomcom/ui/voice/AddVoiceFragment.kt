package com.timelysoft.tsjdomcom.ui.voice

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
import com.timelysoft.tsjdomcom.adapters.voice.AnswerChoiceAndroid
import com.timelysoft.tsjdomcom.adapters.voice.AnswerChoiceModel
import com.timelysoft.tsjdomcom.utils.MyUtils
import kotlinx.android.synthetic.main.fragment_add_voice.*
import java.util.*
import kotlin.collections.ArrayList

class AddVoiceFragment : Fragment(){
    private var mLastClickTime: Long = 0
    var list: ArrayList<AnswerChoiceModel> = arrayListOf()

    private var myAdapter = AnswerChoiceAndroid()

    private var position: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_voice, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.show()
        getAddVoiceDateFrom()
        getAddVoiceDateTo()
        initRecycler()
        initArgument()
    }

    private fun initArgument() {
        add_voice_options.setOnClickListener {
            list.add(AnswerChoiceModel(0, ""))
            myAdapter.update(list)
            myAdapter.notifyItemRangeChanged(list.size, list.size)
        }
    }

    private fun initRecycler() {
        add_voice_recycler.adapter = myAdapter
    }

    private fun getAddVoiceDateFrom() {
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MARCH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        add_voice_date_from_out.keyListener = null;
        add_voice_date_from_out.setOnFocusChangeListener setOnClickListener@{ _, hasFocus ->
            if (hasFocus) {
                if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
                    return@setOnClickListener
                }
                mLastClickTime = SystemClock.elapsedRealtime();
                add_voice_date_from.defaultHintTextColor =
                    ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
                val picker =
                    DatePickerDialog(activity!!,
                        R.style.DatePicker, { _, year1, monthOfYear, dayOfMonth ->
                            add_voice_date_from_out.setText(
                                MyUtils.convertDate(
                                    dayOfMonth,
                                    monthOfYear + 1,
                                    year1
                                )
                            )
                        }, year, month, day
                    )
                picker.show()
                add_voice_date_from_out.clearFocus()
            }
        }
    }

    private fun getAddVoiceDateTo() {
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MARCH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        add_voice_date_to_out.keyListener = null;
        add_voice_date_to_out.setOnFocusChangeListener setOnClickListener@{ _, hasFocus ->
            if (hasFocus) {
                if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
                    return@setOnClickListener
                }
                mLastClickTime = SystemClock.elapsedRealtime();
                add_voice_date_to.defaultHintTextColor =
                    ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
                val picker =
                    DatePickerDialog(activity!!,
                        R.style.DatePicker, { _, year1, monthOfYear, dayOfMonth ->
                            add_voice_date_to_out.setText(
                                MyUtils.convertDate(
                                    dayOfMonth,
                                    monthOfYear + 1,
                                    year1
                                )
                            )
                        }, year, month, day
                    )
                picker.show()
                add_voice_date_to_out.clearFocus()
            }
        }
    }
}