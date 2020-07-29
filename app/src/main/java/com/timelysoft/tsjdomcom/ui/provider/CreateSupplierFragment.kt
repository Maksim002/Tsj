package com.timelysoft.tsjdomcom.ui.provider

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.service.Status
import com.timelysoft.tsjdomcom.service.model.provider.ProviderModel
import com.timelysoft.tsjdomcom.service.request.provider.CreateSupplier
import com.timelysoft.tsjdomcom.service.request.provider.ProviderEdit
import kotlinx.android.synthetic.main.fragment_create_supplier.*
import java.lang.Exception

class CreateSupplierFragment : Fragment() {

    private var viewModel = ProviderViewModel()
    private var position = -1

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
            val model = ProviderEdit()
            owner_save.setOnClickListener {
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
                })
            }
        }

        if (position == -1) {
            owner_save.setOnClickListener {
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
                })
            }
        }
    }

    override fun onStart() {
        super.onStart()
        check()
    }

    private fun check() {
        if (position != -1) {
            (activity as AppCompatActivity).supportActionBar?.title = "Редактировать"
        }
    }
}