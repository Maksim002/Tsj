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
import androidx.navigation.fragment.findNavController
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.service.Status
import com.timelysoft.tsjdomcom.service.model.expense.ChangeEditModel
import com.timelysoft.tsjdomcom.service.model.expense.ChangeListManagers
import com.timelysoft.tsjdomcom.service.model.expense.ChangeListType
import com.timelysoft.tsjdomcom.service.model.expense.SlipModel
import com.timelysoft.tsjdomcom.ui.main.MainActivity
import com.timelysoft.tsjdomcom.utils.MyUtils
import kotlinx.android.synthetic.main.fragment_change.*
import java.util.*
import kotlin.collections.ArrayList

class ChangeFragment : Fragment() {
    private var viewModel = ExpenseViewModel()
    private var mLastClickTime: Long = 0
    private var dataChange: String = ""
    private var model = ChangeEditModel()
    private var amountType: Int = 0
    private var managerId: Int = 0

    private var list: ArrayList<SlipModel> = arrayListOf()

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
        getChangeProvider()
        getAutoDatesFrom()
        initArgument()
    }

    override fun onStart() {
        super.onStart()
        change_sum.defaultHintTextColor =
            ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
        change_date.defaultHintTextColor =
            ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
        change_description.defaultHintTextColor =
            ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
    }

    private fun initArgument() {
        val comingsId = try {
            arguments!!.getInt("comingsId")
        } catch (e: Exception) {
            0
        }

        MainActivity.alert.show()
        viewModel.comingDocument(comingsId)
            .observe(viewLifecycleOwner, androidx.lifecycle.Observer { result ->
                val msg = result.msg
                val data = result.data
                when (result.status) {
                    Status.SUCCESS -> {
                        change_sum_out.setText(data!!.amount.toString())
                        change_date_out.setText(MyUtils.toMyDate(data.onDate))
                        change_description_out.setText(data.description)

                        model.amount = data.amount
                        model.onDate = data.onDate
                        model.description = data.description
                        model.id = data.id
                        MainActivity.alert.hide()
                    }
                    Status.ERROR, Status.NETWORK -> {
                        Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
                    }
                }
            })

        change_save.setOnClickListener {
            model.amountType = amountType
            model.managerId = managerId
            viewModel.userChangeEdit(model)
                .observe(viewLifecycleOwner, androidx.lifecycle.Observer { result ->
                    val msg = result.msg
                    when (result.status) {
                        Status.SUCCESS -> {
                            Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
                            findNavController().popBackStack()
                        }
                        Status.ERROR, Status.NETWORK -> {
                            Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
                        }
                    }
                })
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
                        change_type_out.setAdapter(adapterAddType)
                        list = data
                    }
                    Status.ERROR, Status.NETWORK -> {
                        Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
                    }
                }
            })
        change_type_out.keyListener = null
        ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
        change_type_out.onItemClickListener =
            AdapterView.OnItemClickListener { parent, _, position, _ ->
                change_type_out.showDropDown()
                parent.getItemAtPosition(position).toString()
                change_type_out.clearFocus()
                try {
                    amountType = list[position].id!!
                }catch (e: Exception){
                    e.printStackTrace()
                }
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


    private fun getChangeProvider() {
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
                        change_provider_out.setAdapter(adapterChangeProvider)
                        list = data
                    }
                    Status.ERROR, Status.NETWORK -> {
                        Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
                    }
                }
            })
        change_provider_out.keyListener = null
        ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
        change_provider_out.onItemClickListener =
            AdapterView.OnItemClickListener { parent, _, position, _ ->
                change_provider_out.showDropDown()
                parent.getItemAtPosition(position).toString()
                change_provider_out.clearFocus()
                try {
                    managerId = list[position].id!!
                }catch (e: Exception){
                    e.printStackTrace()
                }
            }
        change_provider_out.setOnClickListener {
            change_provider_out.showDropDown()
        }
        change_provider_out.onFocusChangeListener =
            View.OnFocusChangeListener { view, hasFocus ->
                try {
                    if (hasFocus) {
                        change_provider_out.showDropDown()
                    }
                    if (!hasFocus && change_provider_out.text!!.isNotEmpty()) {
                        change_provider.defaultHintTextColor =
                            ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
                        change_provider.isErrorEnabled = false
                    }
                } catch (e: Exception) {
                }
            }
        change_provider_out.clearFocus()
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
                    DatePickerDialog(
                        activity!!,
                        R.style.DatePicker, { _, year1, monthOfYear, dayOfMonth ->
                            change_date_out.setText(
                                MyUtils.convertDate(
                                    dayOfMonth,
                                    monthOfYear + 1,
                                    year1
                                )
                            )
                            dataChange =
                                (MyUtils.convertDateServer(year1, monthOfYear + 1, dayOfMonth))
                        }, year, month, day
                    )
                picker.show()
                change_date_out.clearFocus()
            }
        }
    }
}