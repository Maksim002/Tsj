package com.example.tsj.ui.message.fragments


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.tsj.MainActivity
import com.example.tsj.R
import com.example.tsj.ui.message.MessagesViewModel
import com.example.tsj.ui.message.fragments.MessageBottomSheet
import com.example.tsj.utils.MyUtils
import kotlinx.android.synthetic.main.fragment_message_detail.*
import java.lang.Exception

class MessageDetailFragment : Fragment() {

    private var idMessage = 0
    private lateinit var viewModel: MessagesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        (activity as AppCompatActivity).supportActionBar!!.show()
        viewModel = ViewModelProviders.of(this).get(MessagesViewModel::class.java)
        initArguments()
        setHasOptionsMenu(true)
        MainActivity.alert.show()
        return inflater.inflate(R.layout.fragment_message_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initData()
    }

    private fun initData() {
        viewModel.message(idMessage).observe(this, Observer {
            MainActivity.alert.hide()
            msg_detail_date.text = MyUtils.toMyDate(it.sendDate)
            msg_detail_sender.text = "от: " + it.personName
            msg_detail_title.text = it.title
            msg_detail_content.text = it.body
        })
    }

    private fun initArguments() {
        idMessage = try {
            arguments!!.getInt("id")
        } catch (e: Exception) {
            0
        }
    }

    private fun initViews() {
        msg_answer_button.setOnClickListener {
            val messageBottomSheet = MessageBottomSheet(idMessage)
            messageBottomSheet.show(fragmentManager!!, "MessageBottomSheet")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_message_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.delete_message -> {
                deleteMessage()
            }
        }
        return super.onOptionsItemSelected(item)

    }

    private fun deleteMessage() {
        MainActivity.alert.show()
        viewModel.deleteMessage(idMessage).observe(this, Observer {
            if (it){
                findNavController().popBackStack()
            }
            MainActivity.alert.hide()
        })
    }
}
