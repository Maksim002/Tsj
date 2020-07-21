package com.timelysoft.tsjdomcom.ui.setting

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.timelysoft.tsjdomcom.R
import kotlinx.android.synthetic.main.fragment_general_edit.*

class GeneralEditFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_general_edit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.show()
        initArgument()
    }

    private fun initArgument() {
        val address_title = try {
            arguments!!.getString("address_title")
        } catch (e: Exception) {
            ""
        }

        val information_title = try {
            arguments!!.getString("information_title")
        } catch (e: Exception) {
            ""
        }
        if (address_title != null || information_title == null){
            general_edit_information.setText(address_title)
        }else{
            general_edit_information.setText(information_title)
        }
    }
}