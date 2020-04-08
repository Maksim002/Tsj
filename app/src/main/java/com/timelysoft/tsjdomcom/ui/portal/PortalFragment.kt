package com.timelysoft.tsjdomcom.ui.portal


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.timelysoft.tsjdomcom.R


class PortalFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_portal_tsj, container, false)
        (activity as AppCompatActivity).supportActionBar!!.show()
        return root
    }
}
