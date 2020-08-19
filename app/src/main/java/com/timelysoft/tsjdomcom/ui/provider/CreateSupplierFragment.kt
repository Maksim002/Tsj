package com.timelysoft.tsjdomcom.ui.provider

import android.content.res.ColorStateList
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.service.Status
import com.timelysoft.tsjdomcom.service.request.provider.CreateSupplier
import com.timelysoft.tsjdomcom.service.request.provider.ProviderEdit
import com.timelysoft.tsjdomcom.ui.main.MainActivity
import kotlinx.android.synthetic.main.fragment_add_invoice.*
import kotlinx.android.synthetic.main.fragment_create_supplier.*
import java.lang.Exception

class CreateSupplierFragment : Fragment() {

    private var viewModel = ProviderViewModel()
    private var position = -1
    private var providerId: Int = 0

    private var model = ProviderEdit()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_supplier, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.show()
        initArguments()
    }

    private fun initArguments() {
        position = try {
            arguments!!.getInt("position")
        } catch (e: Exception) {
            -1
        }

        if (position != -1){
            providerId = try {
                arguments!!.getInt("providerId")
            } catch (e: Exception) {
                0
            }
            viewModel.providerId(providerId).observe(viewLifecycleOwner, Observer {result ->
                val msg = result.msg
                val data = result.data
                when (result.status) {
                    Status.SUCCESS -> {
                        create_supplier_address_out.setText(data!!.address)
                        create_supplier_name_out.setText(data.name)
                        create_supplier_organization_out.setText(data.organizationType)
                        create_supplier_inn_out.setText(data.tin)
                        create_supplier_okpo_out.setText(data.okpo)
                        create_supplier_bik_out.setText(data.bic)
                        create_supplier_account_out.setText(data.checkingAccount)
                        create_supplier_telephone_out.setText(data.phone)
                        create_supplier_email_out.setText(data.email)
                        model.id = data.id

                    }
                    Status.ERROR, Status.NETWORK -> {
                        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                    }
                }
            })
            owner_save.setOnClickListener {
                if (validate()) {
                    MainActivity.alert.show()
                    model.id
                    model.address = create_supplier_address_out.text.toString()
                    model.name = create_supplier_name_out.text.toString()
                    model.organizationType = create_supplier_organization_out.text.toString()
                    model.tin = create_supplier_inn_out.text.toString()
                    model.okpo = create_supplier_okpo_out.text.toString()
                    model.bic = create_supplier_bik_out.text.toString()
                    model.checkingAccount = create_supplier_account_out.text.toString()
                    model.phone = create_supplier_telephone_out.text.toString()
                    model.email = create_supplier_email_out.text.toString()

                    viewModel.providerEdit(model).observe(viewLifecycleOwner, Observer { result ->
                        val msg = result.msg
                        when (result.status) {
                            Status.SUCCESS -> {
                                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                                findNavController().popBackStack()
                            }
                            Status.ERROR, Status.NETWORK -> {
                                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                            }
                        }
                        MainActivity.alert.hide()
                    })
                }
            }
        }

        if (position == -1) {
            owner_save.setOnClickListener {
                if (validate()) {
                    MainActivity.alert.show()
                    val model = CreateSupplier()
                    model.address = create_supplier_address_out.text.toString()
                    model.name = create_supplier_name_out.text.toString()
                    model.organizationType = create_supplier_organization_out.text.toString()
                    model.tin = create_supplier_inn_out.text.toString()
                    model.okpo = create_supplier_okpo_out.text.toString()
                    model.bic = create_supplier_bik_out.text.toString()
                    model.checkingAccount = create_supplier_account_out.text.toString()
                    model.phone = create_supplier_telephone_out.text.toString()
                    model.email = create_supplier_email_out.text.toString()
                    viewModel.createSupplier(model).observe(viewLifecycleOwner, Observer { result ->
                        val msg = result.msg
                        when (result.status) {
                            Status.SUCCESS -> {
                                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                                findNavController().popBackStack()
                            }
                            Status.ERROR, Status.NETWORK -> {
                                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                            }
                        }
                        MainActivity.alert.hide()
                    })
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        check()
        hintText()
    }

    private fun check() {
        if (position != -1) {
            (activity as AppCompatActivity).supportActionBar?.title = "Редактировать"
        }
    }

    private fun hintText() {
        create_supplier_name_out.addTextChangedListener {
            create_supplier_name.isErrorEnabled = false
            create_supplier_name.defaultHintTextColor = ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
        }

        create_supplier_organization_out.addTextChangedListener {
            create_supplier_organization.isErrorEnabled = false
            create_supplier_organization.defaultHintTextColor = ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
        }

        create_supplier_address_out.addTextChangedListener {
            create_supplier_address.isErrorEnabled = false
            create_supplier_address.defaultHintTextColor = ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
        }

        create_supplier_inn_out.addTextChangedListener {
            create_supplier_inn.isErrorEnabled = false
            create_supplier_inn.defaultHintTextColor = ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
        }

        create_supplier_okpo_out.addTextChangedListener {
            create_supplier_okpo.isErrorEnabled = false
            create_supplier_okpo.defaultHintTextColor = ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
        }

        create_supplier_bik_out.addTextChangedListener {
            create_supplier_bik.isErrorEnabled = false
            create_supplier_bik.defaultHintTextColor = ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
        }

        create_supplier_account_out.addTextChangedListener {
            create_supplier_account.isErrorEnabled = false
            create_supplier_account.defaultHintTextColor = ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
        }

        create_supplier_telephone_out.addTextChangedListener {
            create_supplier_telephone.isErrorEnabled = false
            create_supplier_telephone.defaultHintTextColor = ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
        }

        create_supplier_email_out.addTextChangedListener {
            create_supplier_email.isErrorEnabled = false
            create_supplier_email.defaultHintTextColor = ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
        }
    }

    private fun validate(): Boolean {
        var valid = true
        if (create_supplier_name_out.text!!.toString().isEmpty()) {
            create_supplier_name.error = "Поле не должно быть пустым"
            valid = false
        } else {
            create_supplier_name.isErrorEnabled = false
        }

        if (create_supplier_organization_out.text.toString().isEmpty()) {
            create_supplier_organization.error = "Поле не должно быть пустым"
            valid = false
        } else {
            create_supplier_organization.isErrorEnabled = false
        }

        if (create_supplier_address_out.text.toString().isEmpty()) {
            create_supplier_address.error = "Поле не должно быть пустым"
            valid = false
        } else {
            create_supplier_address.isErrorEnabled = false
        }

        if (create_supplier_inn_out.text.toString().isEmpty()) {
            create_supplier_inn.error = "Поле не должно быть пустым"
            valid = false
        } else {
            create_supplier_inn.isErrorEnabled = false
        }

        if (create_supplier_okpo_out.text.toString().isEmpty()) {
            create_supplier_okpo.error = "Поле не должно быть пустым"
            valid = false
        } else {
            create_supplier_okpo.isErrorEnabled = false
        }

        if (create_supplier_bik_out.text.toString().isEmpty()) {
            create_supplier_bik.error = "Поле не должно быть пустым"
            valid = false
        } else {
            create_supplier_bik.isErrorEnabled = false
        }

        if (create_supplier_account_out.text.toString().isEmpty()) {
            create_supplier_account.error = "Поле не должно быть пустым"
            valid = false
        } else {
            create_supplier_account.isErrorEnabled = false
        }

        if (create_supplier_telephone_out.text.toString().isEmpty()) {
            create_supplier_telephone.error = "Поле не должно быть пустым"
            valid = false
        } else {
            create_supplier_telephone.isErrorEnabled = false
        }

        if (create_supplier_email_out.text.toString().isEmpty()) {
            create_supplier_email.error = "Поле не должно быть пустым"
            valid = false
        } else {
            create_supplier_email.isErrorEnabled = false
        }
        return valid
    }
}