package com.timelysoft.tsjdomcom.ui.setting

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.adapters.setting.BankDetailAdapter
import com.timelysoft.tsjdomcom.adapters.setting.BankDetailModel
import kotlinx.android.synthetic.main.fragment_bank_details.*

class BankDetailsFragment : Fragment() {

    private var myAdapter = BankDetailAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bank_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.show()
        initRecycler()
        initArgument()
    }

    private fun initArgument() {
        bank_detail_add.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("zero", 0)
            findNavController().navigate(R.id.navigation_add_bank, bundle)
        }
    }

    private fun initRecycler() {
        val list: ArrayList<BankDetailModel> = arrayListOf()
        list.add(BankDetailModel(""))
        list.add(BankDetailModel(""))
        list.add(BankDetailModel(""))
        list.add(BankDetailModel(""))

        myAdapter.update(list)
        bank_detail_recycler.adapter = myAdapter
    }
}