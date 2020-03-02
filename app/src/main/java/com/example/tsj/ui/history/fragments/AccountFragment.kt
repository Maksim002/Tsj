package com.example.tsj.ui.history.fragments


import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.TextView
import androidx.navigation.Navigation
import com.example.tsj.R

class AccountFragment : Fragment() {

    private val TAG = "myLogs"

    private var idI = 0
    private var title: String? = ""
    private lateinit var textAccounts: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var root = inflater.inflate(R.layout.fragment_account, container, false)

        var bReturn = root.findViewById<Button>(R.id.bottomReturn)

        textAccounts = root.findViewById(R.id.textAccount)
        initArguments()
        textAccounts.text = title

        setHasOptionsMenu(true)


        bReturn.setOnClickListener { l ->
            var bundle = Bundle()
            bundle.putBoolean("btn", true)
            Navigation.findNavController(root).navigate(R.id.navigation_personal, bundle)
        }

        return root
    }

    private fun initArguments() {
        assert(arguments != null)
        idI = arguments!!.getInt("id")
        title = arguments!!.getString("title")
    }
//
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.account_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
///
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.account -> {
                getAlert()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getAlert() {
        val builder = AlertDialog.Builder(context)
        builder.setMessage("Вы действительно хотите удалить\n" +
                "Лицевой счет № 2425")
            .setPositiveButton("Да, удалить") { dialog, id ->
                dialog.cancel()
            }
            .setNegativeButton("Отменить") { dialog, id ->
                dialog.cancel()
            }
        builder.create()
        builder.show()
    }
}
