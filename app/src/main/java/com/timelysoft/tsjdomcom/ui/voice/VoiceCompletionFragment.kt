package com.timelysoft.tsjdomcom.ui.voice

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.adapters.voice.VoiceCompletionAdapter
import com.timelysoft.tsjdomcom.adapters.voice.VoiceCompletionModel
import kotlinx.android.synthetic.main.fragment_voice_completion.*

class VoiceCompletionFragment : Fragment() {

    private var myAdapter = VoiceCompletionAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_voice_completion, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
        list.add(VoiceCompletionModel(""))

        myAdapter.update(list)
        voice_completion_recycler.adapter = myAdapter
    }
}