package com.example.tsj.ui.message.send


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import com.example.tsj.R


@Suppress("UNREACHABLE_CODE")
class NewMessageOwner : Fragment() {
// Новое сообщение собственнику
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        (activity as AppCompatActivity).supportActionBar?.show()
       // setHasOptionsMenu(true)
      return inflater.inflate(R.layout.new_message_owner, container, false)
    }


/*
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.new_message_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
*/

  /*  override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
        when (item.itemId) {
            R.id.send_message -> {
                sendMessage()
            }
            R.id.fasten_file -> {
                fastenFile()
            }

        }
        return super.onOptionsItemSelected(item)

    }*/

    private fun fastenFile() {

    }

    private fun sendMessage() {

    }

}
