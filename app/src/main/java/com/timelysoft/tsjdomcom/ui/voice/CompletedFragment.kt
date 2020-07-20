package com.timelysoft.tsjdomcom.ui.voice

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.adapters.voice.CompletedAdapter
import com.timelysoft.tsjdomcom.adapters.voice.VoiceCompletionModel
import kotlinx.android.synthetic.main.fragment_completed.*

class CompletedFragment : Fragment() {
    private var myAdapter = CompletedAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_completed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.show()
        initRecycler()
    }

    private fun initRecycler() {
        val list: ArrayList<VoiceCompletionModel> = arrayListOf()
        list.add(VoiceCompletionModel(""))
        list.add(VoiceCompletionModel(""))
        list.add(VoiceCompletionModel(""))
        list.add(VoiceCompletionModel(""))
        list.add(VoiceCompletionModel(""))
        list.add(VoiceCompletionModel(""))

        myAdapter.update(list)
        completed_recycler.adapter = myAdapter
    }
}