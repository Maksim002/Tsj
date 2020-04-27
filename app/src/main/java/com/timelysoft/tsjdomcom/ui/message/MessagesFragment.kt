package com.timelysoft.tsjdomcom.ui.message


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.adapters.message.MessageAdapter
import com.timelysoft.tsjdomcom.adapters.message.MessageClicklItemListener
import com.timelysoft.tsjdomcom.service.model.MessageItemModel
import com.timelysoft.tsjdomcom.service.AppPreferences
import com.timelysoft.tsjdomcom.service.Status
import kotlinx.android.synthetic.main.fragment_messages.*

class MessagesFragment(private val idMessage: Int) : Fragment(), MessageClicklItemListener {

    private lateinit var messageAdapter: MessageAdapter
    private lateinit var viewModel: MessagesViewModel
    private lateinit var recyclerview: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_messages, container, false)
        viewModel = ViewModelProviders.of(this).get(MessagesViewModel::class.java)
        recyclerview = root.findViewById(R.id.messages_recyclerview)

        (activity as AppCompatActivity).supportActionBar!!.show()
        return root

    }

    override fun onStart() {
        super.onStart()
        initRec()
    }

    private fun initRec() {
        if (AppPreferences.isLogined) {
            viewModel.messages(idMessage).observe(this, Observer { result ->
                val msg = result.msg
                val data = result.data
                when(result.status){
                    Status.SUCCESS ->{
                        messageAdapter = MessageAdapter(this, data!!)
                        recyclerview.apply { adapter = messageAdapter }

                        if (messageAdapter.itemCount == 0) {
                            messages_empty_text.visibility = View.VISIBLE
                            recyclerview.visibility = View.GONE
                        } else {
                            messages_empty_text.visibility = View.GONE
                            recyclerview.visibility = View.VISIBLE
                        }
                    }
                    Status.ERROR, Status.NETWORK -> {
                        Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
                    }
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
