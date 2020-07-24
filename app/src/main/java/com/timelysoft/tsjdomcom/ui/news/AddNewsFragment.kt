package com.timelysoft.tsjdomcom.ui.news

import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.provider.MediaStore.MediaColumns
import android.view.*
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.loader.content.CursorLoader
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.adapters.news.AddNewsAdapter
import com.timelysoft.tsjdomcom.adapters.news.AddNewsModel
import com.timelysoft.tsjdomcom.service.AppPreferences
import com.timelysoft.tsjdomcom.ui.contact.fragments.AccountsBottomSheet
import kotlinx.android.synthetic.main.fragment_add_news.*
import kotlinx.android.synthetic.main.fragment_contacts.view.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File


class AddNewsFragment : Fragment() {

    private lateinit var addNewsBottomShit: AddNewsBottomShitFragment

    private var myAdapter = AddNewsAdapter()
    var list: ArrayList<AddNewsModel> = arrayListOf()

    private var position: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addNewsBottomShit = AddNewsBottomShitFragment()
        setHasOptionsMenu(true)
        iniArgument()
        initRecycler()
    }

    private fun iniArgument() {
        val item = try {
            arguments!!.getSerializable("file")
        }catch (e: Exception){
            ""
        }
        list.add(AddNewsModel(position, item.toString()))
        myAdapter.update(list)

    }

    private fun initRecycler() {
        add_news_recycler.adapter = myAdapter
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_news_chairman, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.send_message -> {

            }
            R.id.fasten_file -> {
                addNewsBottomShit.show(fragmentManager!!, "addNewsBottomShit")
            }
        }
        return super.onOptionsItemSelected(item)
    }
}