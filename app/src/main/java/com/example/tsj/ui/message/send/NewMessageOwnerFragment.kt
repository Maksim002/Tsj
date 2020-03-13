package com.example.tsj.ui.message.send

import android.app.Activity
import android.content.Intent
import android.content.res.ColorStateList
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.loader.content.CursorLoader
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.tsj.R
import com.example.tsj.adapters.message.GeneralClickListener
import com.example.tsj.adapters.message.OwnerAdapter
import com.example.tsj.service.model.MessagesHousesModel
import com.example.tsj.service.model.MessagesPersonsModel
import com.example.tsj.service.model.MessagesPlacementsModel
import com.example.tsj.ui.message.MessagesViewModel
import kotlinx.android.synthetic.main.new_message_chairman.*
import kotlinx.android.synthetic.main.new_message_owner.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class NewMessageOwnerFragment : Fragment(), GeneralClickListener {

    private lateinit var viewModel: MessagesViewModel
    private lateinit var adapterOwner: OwnerAdapter
    private lateinit var recyclerOwner: RecyclerView
    private var houseId: Int = 0
    private var placementId: Int = 0
    private var personId: Int = 0
    private var files = ArrayList<MultipartBody.Part>()
    private var name = ArrayList<String>()
    private val IMAGE_PICK_CODE = 11

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        (activity as AppCompatActivity).supportActionBar?.show()
        setHasOptionsMenu(true)
        val root = inflater.inflate(R.layout.new_message_owner, container, false)
        viewModel = ViewModelProviders.of(this).get(MessagesViewModel::class.java)

        recyclerOwner = root.findViewById(R.id.recyclerOwner)
        getRecyclerView()

        return root
    }

    override fun onClickManager(position: Int) {
        name.removeAt(position)
        files.removeAt(position)
        adapterOwner.update(name)
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
        val myFile = Intent(Intent.ACTION_PICK)
        myFile.setType("*/*");
        startActivityForResult(Intent.createChooser(myFile,"Select Picture") , IMAGE_PICK_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            if (data != null) {
                val uri = data.data!!
                val file = File(getPath(uri))
                val requestFile = file.asRequestBody("*/*".toMediaTypeOrNull())
                val photo = MultipartBody.Part.createFormData("File", file.name, requestFile)
                files.add(photo)
                name.add(photo.toString().substring(0, 15))
                adapterOwner.update(name)
            }
        }
    }

    private fun getPath(uri: Uri): String {
        val proj = arrayOf(MediaStore.Images.Media.DATA)
        val loader = CursorLoader(context!!, uri, proj, null, null, null)
        val cursor = loader.loadInBackground()
        val columnIndex = cursor!!.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        cursor.moveToFirst()
        val res = cursor.getString(columnIndex)
        cursor.close()
        return res
    }

    private fun validateEditText(title: String, content: String): Boolean {
        return title.isNotEmpty() || content.isNotEmpty()
    }

    private fun sendMessage() {
        val title = new_msg_referenc.text.toString()
        val body = new_msg_content.text.toString()
        //проверка на пустоту edit text
        if (validateEditText(title, body)) {
            new_msg_referenc.error = null
            new_msg_content.error = null
            //Метод для post сообщания
            viewModel.messageToPerson(placementId, body, title, files).observe(this, Observer {
                if (it) {
                    findNavController().popBackStack()
                } else {
                    Toast.makeText(context, "Неудочно", Toast.LENGTH_LONG).show()
                }
            })

        } else {
            title_container.error = "Загаловка не может быть пустым"
            content_container.error = "Письмо не может быть пустым"
        }
    }

    override fun onStart() {
        super.onStart()
        initViews()
        initData()
        getNewMsgHouse()
    }

    private fun initData() {

    }

    private fun initViews() {
        new_msg_house.setKeyListener(null)
        new_msg_appartment.setKeyListener(null)
        new_msg_who.setKeyListener(null)
    }

    private fun getNewMsgHouse() {
        var listHouses = ArrayList<MessagesHousesModel>()
        viewModel.houses().observe(this, Observer { services ->
            val list = services.map {
                it.address
            }
            listHouses = services as ArrayList<MessagesHousesModel>
            val adapterServices =
                ArrayAdapter<String>(context!!, android.R.layout.simple_dropdown_item_1line, list)
            adapterServices.notifyDataSetChanged()
            new_msg_house.setAdapter(adapterServices)

        })


        new_msg_house.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                new_msg_house.showDropDown()
                new_house.defaultHintTextColor =
                    ColorStateList.valueOf(getResources().getColor(R.color.colorAccent))
                parent.getItemAtPosition(position).toString()
                houseId = listHouses.get(position).id
                getMessagesPlacements()

                new_msg_appartment.setAdapter(null)
                new_msg_appartment.setText("")
                new_msg_who.setAdapter(null)
                new_msg_who.setText("")
            }
        new_msg_house.setOnClickListener {
            new_msg_house.showDropDown()
        }
        new_msg_house.onFocusChangeListener = View.OnFocusChangeListener { view, b ->
            if (b) {
                try {
                    new_msg_house.showDropDown()
                } catch (e: Exception) {
                    println()
                }
            }
        }
    }

    private fun getMessagesPlacements() {
        var listPlacements = ArrayList<MessagesPlacementsModel>()
        viewModel.placements(houseId).observe(this, Observer { services ->
            val list = services.map {
                it.number
            }
            listPlacements = services as ArrayList<MessagesPlacementsModel>
            val adapterServices =
                ArrayAdapter<String>(context!!, android.R.layout.simple_dropdown_item_1line, list)
            adapterServices.notifyDataSetChanged()
            new_msg_appartment.setAdapter(adapterServices)
        })


        new_msg_appartment.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                new_msg_appartment.showDropDown()
                new_appartment.defaultHintTextColor =
                    ColorStateList.valueOf(getResources().getColor(R.color.colorAccent))
                parent.getItemAtPosition(position).toString()
                placementId = listPlacements.get(position).id
                getMessagesPersons()
                new_msg_who.setAdapter(null)
                new_msg_who.setText("")
            }
        new_msg_appartment.setOnClickListener {
            new_msg_appartment.showDropDown()
        }
        new_msg_appartment.onFocusChangeListener = View.OnFocusChangeListener { view, b ->
            if (b) {
                try {
                    new_msg_appartment.showDropDown()
                } catch (e: Exception) {
                    println()
                }
            }
        }
    }

    private fun getMessagesPersons() {
        var listPersons = ArrayList<MessagesPersonsModel>()
        viewModel.persons(placementId).observe(this, Observer { services ->
            val list = services.map {
                it.name
            }
            listPersons = services as ArrayList<MessagesPersonsModel>
            val adapterServices =
                ArrayAdapter<String>(context!!, android.R.layout.simple_dropdown_item_1line, list)
            adapterServices.notifyDataSetChanged()
            new_msg_who.setAdapter(adapterServices)
        })


        new_msg_who.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                new_msg_who.showDropDown()
                new_who.defaultHintTextColor =
                    ColorStateList.valueOf(getResources().getColor(R.color.colorAccent))
                parent.getItemAtPosition(position).toString()
                personId = listPersons.get(position).id
            }
        new_msg_who.setOnClickListener {
            new_msg_who.showDropDown()
        }
        new_msg_who.onFocusChangeListener = View.OnFocusChangeListener { view, b ->
            if (b) {
                try {
                    new_msg_who.showDropDown()
                } catch (e: Exception) {
                    println()
                }
            }
        }
    }
    private fun getRecyclerView() {
        adapterOwner = OwnerAdapter(this)
        recyclerOwner.apply {
            adapter = adapterOwner

        }
    }
}