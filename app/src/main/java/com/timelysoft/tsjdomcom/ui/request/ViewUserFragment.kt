package com.timelysoft.tsjdomcom.ui.request

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
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.service.Status
import com.timelysoft.tsjdomcom.service.model.request.ListUserStatus
import com.timelysoft.tsjdomcom.service.model.request.UserRequestEdit
import com.timelysoft.tsjdomcom.utils.MyUtils
import kotlinx.android.synthetic.main.fragment_view_user.*
import java.lang.Exception
import java.util.*

class ViewUserFragment : Fragment() {
    private var viewModel = RequestViewModel()
    private lateinit var model: UserRequestEdit

    private var mLastClickTime: Long = 0
    private lateinit var data: String

    private var statusId: Int = 0
    private var viewUserId: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getViewUserData()
        getViewUser()
        initArgument()
    }

    private fun initArgument() {
        val id = try {
            arguments!!.getInt("userRequestId")
        }catch (e: Exception){
            0
        }

        viewModel.listUserView(id).observe(viewLifecycleOwner, Observer { result->
            val msg = result.msg
            val data = result.data
            when(result.status){
                Status.SUCCESS ->{
                    view_user_creature_out.setText(MyUtils.toMyDate(data!!.createdDate) )
                    view_user_question.setText(data.requestTypeName)
                    view_user_owner_out.setText(data.personName)
                    view_user_entrance_out.setText(data.entrance.toString())
                    view_user_floor_out.setText(data.floor.toString())
                    view_user_apartment_out.setText(data.placementNumber.toString())
                    view_user_home_out.setText(data.houseAddress)
                    view_user_description_out.setText(data.description)

                    viewUserId = data.id
                }
                Status.ERROR, Status.NETWORK ->{
                    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                }
            }
        })

        view_user_save.setOnClickListener { result->
            try {
                model = UserRequestEdit(viewUserId, statusId, data, view_user_decision_out.text.toString())
                viewModel.userRequestEdit(model).observe(viewLifecycleOwner, Observer { result->
                    val msg = result.msg
                    when(result.status){
                        Status.SUCCESS ->{
                            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                            findNavController().popBackStack()
                        }
                        Status.ERROR, Status.NETWORK ->{
                            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                        }
                    }
                })
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }

    private fun getViewUser() {
        var list: ArrayList<ListUserStatus> = arrayListOf()
        viewModel.listUserStatus().observe(viewLifecycleOwner, androidx.lifecycle.Observer { result ->
            val msg = result.msg
            val data = result.data
            when (result.status) {
                Status.SUCCESS -> {
                    val adapterViewUser = data?.let {
                        ArrayAdapter(context!!, android.R.layout.simple_dropdown_item_1line, it)
                    }
                    view_user_data_status_out.setAdapter(adapterViewUser)
                    list = data as ArrayList<ListUserStatus>
                }
                Status.NETWORK, Status.ERROR -> {
                    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                }
            }
        })
        view_user_data_status_out.keyListener = null
        ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
        view_user_data_status_out.onItemClickListener =
            AdapterView.OnItemClickListener { parent, _, position, _ ->
                view_user_data_status_out.showDropDown()
                parent.getItemAtPosition(position).toString()
                view_user_data_status_out.clearFocus()
                statusId = list[position].id
            }
        view_user_data_status_out.setOnClickListener {
            view_user_data_status_out.showDropDown()
        }
        view_user_data_status_out.onFocusChangeListener =
            View.OnFocusChangeListener { view, hasFocus ->
                try {
                    if (hasFocus) {
                        view_user_data_status_out.showDropDown()
                    }
                    if (!hasFocus && view_user_data_status_out.text!!.isNotEmpty()) {
                        view_user_data_status.defaultHintTextColor =
                            ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
                        view_user_data_status.isErrorEnabled = false
                    }
                } catch (e: Exception) {
                }
            }
        view_user_data_status_out.clearFocus()
    }

    private fun getViewUserData() {
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MARCH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        view_user_data_out.keyListener = null;
        view_user_data_out.setOnFocusChangeListener setOnClickListener@{ _, hasFocus ->
            if (hasFocus) {
                if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
                    return@setOnClickListener
                }
                mLastClickTime = SystemClock.elapsedRealtime();
                view_user_data.defaultHintTextColor =
                    ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
                val picker = DatePickerDialog(
                    activity!!, R.style.DatePicker, { _, year1, monthOfYear, dayOfMonth ->
                        view_user_data_out.setText(
                            MyUtils.convertDate(
                                dayOfMonth,
                                monthOfYear + 1,
                                year1
                            )
                        )
                        data = (MyUtils.convertDateServer(year1, monthOfYear + 1, dayOfMonth))
                    }, year, month, day
                )
                picker.show()
                view_user_data_out.clearFocus()
            }
        }
    }
}