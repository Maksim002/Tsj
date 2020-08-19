package com.timelysoft.tsjdomcom.ui.request

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.service.Status
import com.timelysoft.tsjdomcom.service.model.provider.ProviderInvoices
import com.timelysoft.tsjdomcom.ui.main.MainActivity
import kotlinx.android.synthetic.main.fragment_add_invoice.*
import kotlinx.android.synthetic.main.fragment_user_bottom_sheet.*
import java.util.ArrayList

class UserBottomSheet(private var userid: Int) : BottomSheetDialogFragment() {
    private lateinit var viewModel: RequestViewModel
    private var typeId: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this).get(RequestViewModel::class.java)
        return inflater.inflate(R.layout.fragment_user_bottom_sheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initArgument()
        getUserRequestBottomSheet()
    }

    private fun initArgument() {
        user_request_bottom_save.setOnClickListener {
            if (validate()) {
                MainActivity.alert.show()
                viewModel.linkSupplier(userid, typeId)
                    .observe(viewLifecycleOwner, Observer { result ->
                        val msg = result.msg
                        when (result.status) {
                            Status.SUCCESS -> {
                                dismiss()
                                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                                findNavController().popBackStack()
                            }
                            Status.NETWORK, Status.ERROR -> {
                                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                            }
                        }
                    })
                MainActivity.alert.hide()
            }
        }
    }

    private fun getUserRequestBottomSheet() {
        var list: ArrayList<ProviderInvoices> = arrayListOf()
        viewModel.userRequestProvider()
            .observe(viewLifecycleOwner, androidx.lifecycle.Observer { result ->
                val msg = result.msg
                val data = result.data
                when (result.status) {
                    Status.SUCCESS -> {
                        val adapterUserBottomSheet = data?.let {
                            ArrayAdapter(context!!, android.R.layout.simple_dropdown_item_1line, it)
                        }
                        user_request_bottom_type_out.setAdapter(adapterUserBottomSheet)

                        list = data as ArrayList<ProviderInvoices>
                    }
                    Status.NETWORK, Status.ERROR -> {
                        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                    }
                }
            })
        user_request_bottom_type_out.keyListener = null
        ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
        user_request_bottom_type_out.onItemClickListener =
            AdapterView.OnItemClickListener { parent, _, position, _ ->
                user_request_bottom_type_out.showDropDown()
                parent.getItemAtPosition(position).toString()
                user_request_bottom_type_out.clearFocus()
                typeId = list[position].id
            }
        user_request_bottom_type_out.setOnClickListener {
            user_request_bottom_type_out.showDropDown()
        }
        user_request_bottom_type_out.onFocusChangeListener =
            View.OnFocusChangeListener { view, hasFocus ->
                try {
                    if (hasFocus) {
                        user_request_bottom_type_out.showDropDown()
                    }
                    if (!hasFocus && user_request_bottom_type_out.text!!.isNotEmpty()) {
                        user_request_bottom_type.defaultHintTextColor =
                            ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
                        user_request_bottom_type.isErrorEnabled = false
                    }
                } catch (e: Exception) {
                }
            }
        user_request_bottom_type_out.clearFocus()
    }

    private fun validate(): Boolean {
        var valid = true
        if (user_request_bottom_type_out.text!!.toString().isEmpty()) {
            user_request_bottom_type.error = "Поле не должно быть пустым"
            valid = false
        } else {
            user_request_bottom_type.isErrorEnabled = false
        }

        return valid
    }
}