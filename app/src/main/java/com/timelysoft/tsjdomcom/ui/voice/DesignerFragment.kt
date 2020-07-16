package com.timelysoft.tsjdomcom.ui.voice

import android.content.res.ColorStateList
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.timelysoft.tsjdomcom.R
import kotlinx.android.synthetic.main.fragment_designer.*

class DesignerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_designer, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.show()
        getTsj()
        getHome()
        iniArgument()
    }

    private fun iniArgument() {
        designer_add_voice.setOnClickListener {
            findNavController().navigate(R.id.navigation_add_voice)
        }
    }

    private fun getTsj() {
        val list = arrayOf("зайчик", "вышел", "погулять","вода")

        val adapterTsj = ArrayAdapter(context!!, android.R.layout.simple_dropdown_item_1line, list)
        designer_tsj_out.setAdapter(adapterTsj)

        designer_tsj_out.keyListener = null
        ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
        designer_tsj_out.onItemClickListener =
            AdapterView.OnItemClickListener { parent, _, position, _ ->
                designer_tsj_out.showDropDown()
                parent.getItemAtPosition(position).toString()
                designer_tsj_out.clearFocus()
            }
        designer_tsj_out.setOnClickListener {
            designer_tsj_out.showDropDown()
        }
        designer_tsj_out.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            try {
                if (hasFocus) {
                    designer_tsj_out.showDropDown()
                }
                if (!hasFocus && designer_tsj_out.text!!.isNotEmpty()) {
                    designer_tsj.defaultHintTextColor =
                        ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
                    designer_tsj.isErrorEnabled = false
                }
            } catch (e: Exception) {
            }
        }
        designer_tsj_out.clearFocus()
    }

    private fun getHome() {
        val list = arrayOf("зайчик", "вышел", "погулять","вода")

        val adapterHome = ArrayAdapter(context!!, android.R.layout.simple_dropdown_item_1line, list)
        designer_home_out.setAdapter(adapterHome)

        designer_home_out.keyListener = null
        ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
        designer_home_out.onItemClickListener =
            AdapterView.OnItemClickListener { parent, _, position, _ ->
                designer_home_out.showDropDown()
                parent.getItemAtPosition(position).toString()
                designer_home_out.clearFocus()
            }
        designer_home_out.setOnClickListener {
            designer_home_out.showDropDown()
        }
        designer_home_out.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            try {
                if (hasFocus) {
                    designer_home_out.showDropDown()
                }
                if (!hasFocus && designer_home_out.text!!.isNotEmpty()) {
                    designer_home.defaultHintTextColor =
                        ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
                    designer_home.isErrorEnabled = false
                }
            } catch (e: Exception) {
            }
        }
        designer_home_out.clearFocus()
    }
}