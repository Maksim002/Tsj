package com.timelysoft.tsjdomcom.ui.voice

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.adapters.voice.VoiceAdapter
import com.timelysoft.tsjdomcom.adapters.voice.VoiceModel
import kotlinx.android.synthetic.main.fragment_voice.*

class VoiceFragment : Fragment() {

    private var myAdapter = VoiceAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_voice, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.show()
        initRecycler()
        initArgument()
    }

    private fun initArgument() {
        voice_designer.setOnClickListener {
            findNavController().navigate(R.id.navigation_designer)
        }
    }

    private fun initRecycler() {
        val list: ArrayList<VoiceModel> = arrayListOf()
        list.add(VoiceModel(""))
        list.add(VoiceModel(""))
        list.add(VoiceModel(""))
        list.add(VoiceModel(""))

        myAdapter.update(list)
        voice_recycler.adapter = myAdapter
    }
}