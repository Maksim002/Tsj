package com.timelysoft.tsjdomcom.ui.servise

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.service.Status
import com.timelysoft.tsjdomcom.service.model.provider.ProviderInvoices
import com.timelysoft.tsjdomcom.service.model.service.CreateServiceModel
import com.timelysoft.tsjdomcom.service.model.service.CreateTypeService
import com.timelysoft.tsjdomcom.service.model.service.PeriodServiceModel
import com.timelysoft.tsjdomcom.ui.main.MainActivity
import kotlinx.android.synthetic.main.fragment_add_invoice.*
import kotlinx.android.synthetic.main.fragment_create_service.*
import kotlinx.android.synthetic.main.fragment_supplier_accounts.*

class CreateServiceFragment : Fragment() {
    private var viewModel = ServiceViewModel()
    private var model = CreateServiceModel()
    private var periodId: Int = 0
    private var typeId: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_service, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initArgument()
        getCreateServiceCompany()
        getCreateServiceType()
        hintText()
    }

    private fun initArgument() {
        create_service_dependence.setOnCheckedChangeListener { buttonView, isChecked ->
            model.isDependent = isChecked
        }
        create_service_plus_rate.setOnCheckedChangeListener { buttonView, isChecked ->
            model.isVolumeMultiplier = isChecked
        }
        create_service_counter.setOnCheckedChangeListener { buttonView, isChecked ->
            model.isCounter = isChecked
        }
        create_service_save.setOnClickListener {
            if (validate()) {
                MainActivity.alert.show()
                model.name = create_service_name_out.text.toString()

                try {
                    val rate: Int = Integer.valueOf(create_service_rate_out.text.toString())
                    model.tariff = rate
                } catch (e: Exception) {
                    e.printStackTrace()
                }

                model.periodId = periodId
                model.servicePaidId = typeId
                viewModel.createService(model).observe(viewLifecycleOwner, Observer { result ->
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
                    MainActivity.alert.hide()
                })
            }
        }
    }

    private fun getCreateServiceCompany() {
        var list: ArrayList<CreateTypeService> = arrayListOf()
        viewModel.createTypeService()
            .observe(viewLifecycleOwner, androidx.lifecycle.Observer { result ->
                val msg = result.msg
                val data = result.data
                when (result.status) {
                    Status.SUCCESS -> {
                        val adapterCreateServiceCompany = data?.let {
                            ArrayAdapter(context!!, android.R.layout.simple_dropdown_item_1line, it)
                        }
                        create_service_company_out.setAdapter(adapterCreateServiceCompany)

                        list = data as ArrayList<CreateTypeService>
                    }
                    Status.NETWORK, Status.ERROR -> {
                        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                    }
                }
            })
        create_service_company_out.keyListener = null
        ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
        create_service_company_out.onItemClickListener =
            AdapterView.OnItemClickListener { parent, _, position, _ ->
                create_service_company_out.showDropDown()
                parent.getItemAtPosition(position).toString()
                create_service_company_out.clearFocus()
                typeId = list[position].id
            }
        create_service_company_out.setOnClickListener {
            create_service_company_out.showDropDown()
        }
        create_service_company_out.onFocusChangeListener =
            View.OnFocusChangeListener { view, hasFocus ->
                try {
                    if (hasFocus) {
                        create_service_company_out.showDropDown()
                    }
                    if (!hasFocus && create_service_company_out.text!!.isNotEmpty()) {
                        create_service_company.defaultHintTextColor =
                            ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
                        create_service_company.isErrorEnabled = false
                    }
                } catch (e: Exception) {
                }
            }
        create_service_company_out.clearFocus()
    }

    private fun getCreateServiceType() {
        var list: ArrayList<PeriodServiceModel> = arrayListOf()

        viewModel.createPeriodService()
            .observe(viewLifecycleOwner, androidx.lifecycle.Observer { result ->
                val msg = result.msg
                val data = result.data
                when (result.status) {
                    Status.SUCCESS -> {
                        val adapterCreateServiceType = data?.let {
                            ArrayAdapter(context!!, android.R.layout.simple_dropdown_item_1line, it)
                        }
                        create_service_type_out.setAdapter(adapterCreateServiceType)

                        list = data as ArrayList<PeriodServiceModel>
                    }
                    Status.NETWORK, Status.ERROR -> {
                        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                    }
                }
            })
        create_service_type_out.keyListener = null
        ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
        create_service_type_out.onItemClickListener =
            AdapterView.OnItemClickListener { parent, _, position, _ ->
                create_service_type_out.showDropDown()
                parent.getItemAtPosition(position).toString()
                create_service_type_out.clearFocus()
                periodId = list[position].id
            }
        create_service_type_out.setOnClickListener {
            create_service_type_out.showDropDown()
        }
        create_service_type_out.onFocusChangeListener =
            View.OnFocusChangeListener { view, hasFocus ->
                try {
                    if (hasFocus) {
                        create_service_type_out.showDropDown()
                    }
                    if (!hasFocus && create_service_type_out.text!!.isNotEmpty()) {
                        create_service_type.defaultHintTextColor =
                            ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
                        create_service_type.isErrorEnabled = false
                    }
                } catch (e: Exception) {
                }
            }
        create_service_type_out.clearFocus()
    }

    private fun hintText() {
        create_service_name_out.addTextChangedListener {
            create_service_name.isErrorEnabled = false
            create_service_name.defaultHintTextColor =
                ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
        }

        create_service_rate_out.addTextChangedListener {
            create_service_rate.isErrorEnabled = false
            create_service_rate.defaultHintTextColor =
                ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
        }
    }

    private fun validate(): Boolean {
        var valid = true
        if (create_service_name_out.text!!.toString().isEmpty()) {
            create_service_name.error = "Поле не должно быть пустым"
            valid = false
        } else {
            create_service_name.isErrorEnabled = false
        }

        if (create_service_rate_out.text!!.toString().isEmpty()) {
            create_service_rate.error = "Поле не должно быть пустым"
            valid = false
        } else {
            create_service_rate.isErrorEnabled = false
        }

        if (create_service_company_out.text!!.toString().isEmpty()) {
            create_service_company.error = "Поле не должно быть пустым"
            valid = false
        } else {
            create_service_company.isErrorEnabled = false
        }

        if (create_service_type_out.text!!.toString().isEmpty()) {
            create_service_type.error = "Поле не должно быть пустым"
            valid = false
        } else {
            create_service_type.isErrorEnabled = false
        }
        return valid
    }
}