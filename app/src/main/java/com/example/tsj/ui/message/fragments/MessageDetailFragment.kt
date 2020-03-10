package com.example.tsj.ui.message.fragments


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import com.example.tsj.R
import com.example.tsj.ui.message.fragments.MessageBottomSheet
import kotlinx.android.synthetic.main.fragment_message_detail.*

@Suppress("UNREACHABLE_CODE")
class MessageDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        (activity as AppCompatActivity).supportActionBar!!.show()

        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_message_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        msg_answer_button.setOnClickListener {
            val messageBottomSheet = MessageBottomSheet()
            messageBottomSheet.show(fragmentManager!!, "MessageBottomSheet")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_message_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
        when (item.itemId) {
            R.id.delete_message -> {
                deleteMessage()
            }
        }
        return super.onOptionsItemSelected(item)

    }

    private fun deleteMessage() {


    }
}
