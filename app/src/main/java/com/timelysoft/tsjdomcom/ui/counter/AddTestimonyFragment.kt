package com.timelysoft.tsjdomcom.ui.counter

import android.content.res.ColorStateList
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.timelysoft.tsjdomcom.R
import kotlinx.android.synthetic.main.fragment_add_testimony.*

class AddTestimonyFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_testimony, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.show()
        getAddTestimonyHome()
    }

    private fun getAddTestimonyHome() {
        val list = arrayOf("зайчик", "вышел", "погулять","вода")

        val adapterAddTestimonyHome = ArrayAdapter(context!!, android.R.layout.simple_dropdown_item_1line, list)
        add_testimony_home_out.setAdapter(adapterAddTestimonyHome)

        add_testimony_home_out.keyListener = null
        ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
        add_testimony_home_out.onItemClickListener =
            AdapterView.OnItemClickListener { parent, _, position, _ ->
                add_testimony_home_out.showDropDown()
                parent.getItemAtPosition(position).toString()
                add_testimony_home_out.clearFocus()
            }
        add_testimony_home_out.setOnClickListener {
            add_testimony_home_out.showDropDown()
        }
        add_testimony_home_out.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            try {
                if (hasFocus) {
                    add_testimony_home_out.showDropDown()
                }
                if (!hasFocus && add_testimony_home_out.text!!.isNotEmpty()) {
                    add_testimony_home.defaultHintTextColor =
                        ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
                    add_testimony_home.isErrorEnabled = false
                }
            } catch (e: Exception) {
            }
        }
        add_testimony_home_out.clearFocus()
    }
}