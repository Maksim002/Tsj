package com.timelysoft.tsjdomcom.ui.providers

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.adapters.provider.ProviderAdapter
import kotlinx.android.synthetic.main.fragment_create_supplier.view.*
import java.lang.Exception

class CreateSupplierFragment : Fragment() {
    private var valye: Boolean? = null

    private var myAdapter = ProviderAdapter()

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
        initClick(view)
    }

    private fun initClick(view: View) {
        view.owner_save.setOnClickListener {
            if (valye == false){

            }else{

            }
        }
    }

    private fun initArguments() {
        valye = try {
            arguments!!.getBoolean("false")
        } catch (e: Exception) {
            false
        }

        valye = try {
            arguments!!.getBoolean("true")
        } catch (e: Exception) {
            true
        }
    }
}