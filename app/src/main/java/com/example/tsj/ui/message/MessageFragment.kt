package com.example.tsj.ui.message

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.tsj.R
import com.example.tsj.adapters.message.MessageAdapter
import com.example.tsj.adapters.message.MessageCliclItemListener
import com.example.tsj.model.MessageModel
import kotlinx.android.synthetic.main.fragment_message.*

class MessageFragment : Fragment(), MessageCliclItemListener {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_message, container, false)

        (activity as AppCompatActivity).supportActionBar!!.show()

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRec()
        initListener()
    }

    private fun initListener() {
        msg_add.setOnClickListener { findNavController().navigate(R.id.navigation_new_message) }
    }

    private fun initRec() {

        val items = arrayListOf(
            MessageModel("Председатель", "Документы", "Как поступить с документами", "27 февраля"),
            MessageModel("Председатель", "Документы", "Как поступить с документами", "27 февраля"),
            MessageModel("Председатель", "Документы", "Как поступить с документами", "27 февраля"),
            MessageModel("Председатель", "Документы", "Как поступить с документами", "27 февраля"),
            MessageModel("Председатель", "Документы", "Как поступить с документами", "27 февраля"),
            MessageModel("Председатель", "Документы", "Как поступить с документами", "27 февраля"),
            MessageModel("Председатель", "Документы", "Как поступить с документами", "27 февраля"),
            MessageModel("Председатель", "Документы", "Как поступить с документами", "27 февраля")

        )

        val adapter = MessageAdapter(this, items)

        if (adapter.itemCount != 0) {
            msg_empty_textview.visibility = View.GONE
            msg_recyclerview.visibility = View.VISIBLE
        } else {
            msg_empty_textview.visibility = View.VISIBLE
            msg_recyclerview.visibility = View.GONE
        }

        msg_recyclerview.adapter = adapter
    }

    override fun onClickMessage(item: MessageModel) {
        findNavController().navigate(R.id.navigation_message_detail)
    }
}
