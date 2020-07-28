package com.timelysoft.tsjdomcom.ui.message.fragments

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.ColorStateList
import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.adapters.message.MessageChairmanAdapter
import com.timelysoft.tsjdomcom.adapters.message.MessageChairmanModel
import kotlinx.android.synthetic.main.fragment_message_chairman.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class MessageChairmanFragment : Fragment() {
    private val STORAGE_PERMISION_CODE: Int = 1
    private val IMAGE_PICK_CODE = 10
    private var files = ArrayList<MultipartBody.Part>()
    private var names = ArrayList<String>()

    private var myAdapter = MessageChairmanAdapter()

    private var list: ArrayList<MessageChairmanModel> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_message_chairman, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.show()
        setHasOptionsMenu(true)
        getAddress()
        getFlat()
        getUser()
        initRecycler()
    }

    private fun initRecycler() {
        myAdapter.update(list)
        message_chairman_recycler.adapter = myAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_message_chairman, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.send_message -> {

            }
            R.id.fasten_file -> {
                loadFiles()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getAddress() {
        val list = arrayOf("зайчик", "вышел", "погулять","вода")

        val adapterAddress = ArrayAdapter(context!!, android.R.layout.simple_dropdown_item_1line, list)
        message_chairman_address_out.setAdapter(adapterAddress)

        message_chairman_address_out.keyListener = null
        ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
        message_chairman_address_out.onItemClickListener =
            AdapterView.OnItemClickListener { parent, _, position, _ ->
                message_chairman_address_out.showDropDown()
                parent.getItemAtPosition(position).toString()
                message_chairman_address_out.clearFocus()
            }
        message_chairman_address_out.setOnClickListener {
            message_chairman_address_out.showDropDown()
        }
        message_chairman_address_out.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            try {
                if (hasFocus) {
                    message_chairman_address_out.showDropDown()
                }
                if (!hasFocus && message_chairman_address_out.text!!.isNotEmpty()) {
                    message_chairman_address.defaultHintTextColor =
                        ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
                    message_chairman_address.isErrorEnabled = false
                }
            } catch (e: Exception) {
            }
        }
        message_chairman_address_out.clearFocus()
    }

    private fun getFlat() {
        val list = arrayOf("зайчик", "вышел", "погулять","вода")

        val adapterFlat = ArrayAdapter(context!!, android.R.layout.simple_dropdown_item_1line, list)
        message_chairman_flat_out.setAdapter(adapterFlat)

        message_chairman_flat_out.keyListener = null
        ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
        message_chairman_flat_out.onItemClickListener =
            AdapterView.OnItemClickListener { parent, _, position, _ ->
                message_chairman_flat_out.showDropDown()
                parent.getItemAtPosition(position).toString()
                message_chairman_flat_out.clearFocus()
            }
        message_chairman_flat_out.setOnClickListener {
            message_chairman_flat_out.showDropDown()
        }
        message_chairman_flat_out.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            try {
                if (hasFocus) {
                    message_chairman_flat_out.showDropDown()
                }
                if (!hasFocus && message_chairman_flat_out.text!!.isNotEmpty()) {
                    message_chairman_flat.defaultHintTextColor =
                        ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
                    message_chairman_flat.isErrorEnabled = false
                }
            } catch (e: Exception) {
            }
        }
        message_chairman_flat_out.clearFocus()
    }

    private fun getUser() {
        val list = arrayOf("зайчик", "вышел", "погулять","вода")

        val adapterUser = ArrayAdapter(context!!, android.R.layout.simple_dropdown_item_1line, list)
        message_chairman_user_out.setAdapter(adapterUser)

        message_chairman_user_out.keyListener = null
        ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
        message_chairman_user_out.onItemClickListener =
            AdapterView.OnItemClickListener { parent, _, position, _ ->
                message_chairman_user_out.showDropDown()
                parent.getItemAtPosition(position).toString()
                message_chairman_user_out.clearFocus()
            }
        message_chairman_user_out.setOnClickListener {
            message_chairman_user_out.showDropDown()
        }
        message_chairman_user_out.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            try {
                if (hasFocus) {
                    message_chairman_user_out.showDropDown()
                }
                if (!hasFocus && message_chairman_user_out.text!!.isNotEmpty()) {
                    message_chairman_user.defaultHintTextColor =
                        ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
                    message_chairman_user.isErrorEnabled = false
                }
            } catch (e: Exception) {
            }
        }
        message_chairman_user_out.clearFocus()
    }

    private fun loadFiles() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(context!!, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                val permissions = arrayOf(
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                )
                requestPermissions(permissions, STORAGE_PERMISION_CODE)
            } else {
                getMyFile()
            }
        } else {
            getMyFile()
        }
    }

    private fun getMyFile() {
        val myFile = Intent(Intent.ACTION_OPEN_DOCUMENT)
        myFile.setType("*/*");
        startActivityForResult(Intent.createChooser(myFile,"Select Picture") , IMAGE_PICK_CODE)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            getMyFile()
        } else {
            Toast.makeText(context, "Нет разрешений", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE) {

            if (data != null) {
                val uri = data.data!!
                val file = File(uri.toString())
                val requestFile = file.asRequestBody("file/*".toMediaTypeOrNull())
                val photo = MultipartBody.Part.createFormData("File", file.name, requestFile)
                files.add(photo)
                names.add(photo.toString())
                val filename = file.name
                list.add(MessageChairmanModel(0, filename))
                myAdapter.notifyDataSetChanged()
            }
        }
    }
}