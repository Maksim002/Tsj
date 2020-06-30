package com.timelysoft.tsjdomcom.ui.provider

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.adapters.provider.ProviderAdapter
import com.timelysoft.tsjdomcom.adapters.provider.ProviderListener
import com.timelysoft.tsjdomcom.adapters.provider.ProviderModel
import com.timelysoft.tsjdomcom.service.model.MessagesPersonsModel
import kotlinx.android.synthetic.main.fragment_create_supplier.view.*
import kotlinx.android.synthetic.main.fragment_relative.*
import kotlinx.android.synthetic.main.fragment_relative.view.*
import kotlinx.android.synthetic.main.fragment_relative.view.buttonFamilies
import java.lang.Exception

class CreateSupplierFragment : Fragment(){

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
        initArguments()
        (activity as AppCompatActivity).supportActionBar?.show()
        initClick(view)
    }

    private fun initClick(view: View) {

        if (position != -1){
          view.create_supplier_name_out.setText(ProviderFragment.counterList[position].title)
            view.create_supplier_organization_out.setText(ProviderFragment.counterList[position].type)
            view.create_supplier_address_out.setText(ProviderFragment.counterList[position].address)
            view.create_supplier_inn_out.setText(ProviderFragment.counterList[position].inn)
            view.create_supplier_okpo_out.setText(ProviderFragment.counterList[position].okpo)
            view.create_supplier_bik_out.setText(ProviderFragment.counterList[position].telephone)
            view.create_supplier_account_out.setText(ProviderFragment.counterList[position].account)
            view.create_supplier_telephone_out.setText(ProviderFragment.counterList[position].delet)
            view.create_supplier_email_out.setText(ProviderFragment.counterList[position].eit)
        }

        view.owner_save.setOnClickListener {

                if (position == -1){
                    ProviderFragment.counterList.add(ProviderModel(0,
                        view.create_supplier_name_out.text.toString(),
                        view.create_supplier_organization_out.text.toString(),
                        view.create_supplier_address_out.text.toString(),
                        view.create_supplier_inn_out.text.toString(),
                        view.create_supplier_okpo_out.text.toString(),
                        view.create_supplier_bik_out.text.toString(),
                        view.create_supplier_account_out.text.toString(),
                        view.create_supplier_telephone_out.text.toString(),
                        view.create_supplier_email_out.text.toString()))
                }else{
                    ProviderFragment.counterList[position] = ProviderModel(0,
                        view.create_supplier_name_out.text.toString(),
                        view.create_supplier_organization_out.text.toString(),
                        view.create_supplier_address_out.text.toString(),
                        view.create_supplier_inn_out.text.toString(),
                        view.create_supplier_okpo_out.text.toString(),
                        view.create_supplier_bik_out.text.toString(),
                        view.create_supplier_account_out.text.toString(),
                        view.create_supplier_telephone_out.text.toString(),
                        view.create_supplier_email_out.text.toString())
                }
                findNavController().popBackStack()
        }
    }

    private fun initArguments() {
        position = try {
           arguments!!.getInt("position")
       } catch (e: Exception) {
            -1
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