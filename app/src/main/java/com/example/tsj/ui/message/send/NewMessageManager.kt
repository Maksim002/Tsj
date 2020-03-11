package com.example.tsj.ui.message.send


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.tsj.R
import com.example.tsj.service.request.ToManager
import com.example.tsj.ui.message.MessagesViewModel
import kotlinx.android.synthetic.main.new_message_chairman.*


@Suppress("UNREACHABLE_CODE")
class NewMessageManager : Fragment() {


    private lateinit var viewModel: MessagesViewModel

// Новое сообщение председателю

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this).get(MessagesViewModel::class.java)
        (activity as AppCompatActivity).supportActionBar?.show()
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.new_message_chairman, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.new_message_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.send_message -> {
                sendMessage()
            }
            R.id.fasten_file -> {
                fastenFile()
            }

        }
        return super.onOptionsItemSelected(item)

    }

    private fun fastenFile() {

    }

    private fun validateEditText(title: String, content: String): Boolean {
        return title.isNotEmpty() || content.isNotEmpty()
    }

    private fun sendMessage() {

        val title = manager_msg_referenc.text.toString()
        val content = manager_msg_content.text.toString()
        //проверка на пустоту edit text
        if (validateEditText(title, content)) {
            title_container.error = null
            content_container.error = null

            //Метод для post сообщания

            viewModel.toManager(content, title).observe(this, Observer {

            })
        } else {
            title_container.error = "Загаловка не может быть пустым"
            content_container.error = "Письмо не может быть пустым"
        }
    }

}
