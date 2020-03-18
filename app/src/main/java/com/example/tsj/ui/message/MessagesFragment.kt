package com.example.tsj.ui.message


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
import com.example.tsj.model.MessageItemModel
import com.example.tsj.service.AppPreferences
import kotlinx.android.synthetic.main.fraagment_messages.*

class MessagesFragment(private val idMessage: Int) : Fragment(), MessageClicklItemListener {

    private lateinit var messageAdapter: MessageAdapter
    private lateinit var viewModel: MessagesViewModel
    private lateinit var recyclerview: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fraagment_messages, container, false)
        viewModel = ViewModelProviders.of(this).get(MessagesViewModel::class.java)
        recyclerview = root.findViewById(R.id.msg_recyclerview)

        (activity as AppCompatActivity).supportActionBar!!.show()
        return root

    }

    override fun onStart() {
        super.onStart()
        initRec()
    }

    private fun initRec() {
        if (AppPreferences.isLogined) {
            viewModel.messages(idMessage).observe(this, Observer { list ->
                messageAdapter = MessageAdapter(this, list)
                recyclerview.apply { adapter = messageAdapter }

                if (messageAdapter.itemCount == 0) {
                    msg_empty_textview.visibility = View.VISIBLE
                    recyclerview.visibility = View.GONE
                } else {
                    msg_empty_textview.visibility = View.GONE
                    recyclerview.visibility = View.VISIBLE
                }
            })
        }

    }


    override fun onClickMessage(item: MessageItemModel) {
        val bundle = Bundle()
        bundle.putInt("id", item.id)
        findNavController().navigate(R.id.navigation_message_detail, bundle)

    }
}
