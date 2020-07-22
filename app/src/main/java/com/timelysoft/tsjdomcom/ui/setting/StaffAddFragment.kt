package com.timelysoft.tsjdomcom.ui.setting

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.timelysoft.tsjdomcom.R

class StaffAddFragment : Fragment() {

    private var update: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_staff_add, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.show()
        initArgument()
    }

    private fun initArgument() {
        update = try {
            arguments!!.getInt("zero")
        }catch (e: Exception){
            0
        }

        update = try {
            arguments!!.getInt("one")
        }catch (e: Exception){
            1
        }

        if (update == 0){
            (activity as AppCompatActivity).supportActionBar?.title ="Добавить"
        }else{
            (activity as AppCompatActivity).supportActionBar?.title ="Редактировать"
        }
    }
}