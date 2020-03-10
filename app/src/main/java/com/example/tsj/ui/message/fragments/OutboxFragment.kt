package com.example.tsj.ui.message.fragments


import android.opengl.Visibility
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.tsj.R
import com.example.tsj.adapters.message.MessageAdapter
import com.example.tsj.adapters.message.MessageClicklItemListener
import com.example.tsj.model.MessageModel
import com.example.tsj.service.AppPreferences
import com.example.tsj.ui.message.MessagesViewModel
import kotlinx.android.synthetic.main.navigation_outbox.*

class OutboxFragment : Fragment(), MessageClicklItemListener {
    private lateinit var messageAdapter: MessageAdapter
    private lateinit var viewModel: MessagesViewModel
    private lateinit var recyclerview: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.navigation_outbox, container, false)
        viewModel = ViewModelProviders.of(this).get(MessagesViewModel::class.java)
        recyclerview = root.findViewById(R.id.msg_recyclerview_out)

        (activity as AppCompatActivity).supportActionBar!!.show()
        return root

    }

    override fun onStart() {
        super.onStart()
        initRec()
    }

    private fun initRec() {
        if(AppPreferences.isLogined){
            viewModel.messages(0).observe(this, Observer { list ->
                messageAdapter = MessageAdapter(this, list)

                recyclerview.apply { adapter = messageAdapter }

                if (messageAdapter.itemCount == 0) {
                    msg_empty_textview_out.visibility = View.VISIBLE
                    recyclerview.visibility = View.GONE
                } else {
                    msg_empty_textview_out.visibility = View.GONE
                    recyclerview.visibility = View.VISIBLE
                }
            })
        }



    }

    override fun onClickMessage(item: MessageModel) {
        findNavController().navigate(R.id.navigation_message_detail)
    }

}
