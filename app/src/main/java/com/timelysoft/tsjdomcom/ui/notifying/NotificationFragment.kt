package com.timelysoft.tsjdomcom.ui.notifying

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.timelysoft.tsjdomcom.R
import kotlinx.android.synthetic.main.fragment_notification.*
import java.lang.Exception

class NotificationFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notification, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.show()
        initArgument()
    }

    private fun initArgument() {
        val list = try {
            arguments!!.getSerializable("notifying")
        } catch (e: Exception) {
            ""
        }

        notification_title.setText(list.toString())
    }
}