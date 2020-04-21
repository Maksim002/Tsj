package com.timelysoft.tsjdomcom.ui.reference


import android.Manifest
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.DownloadManager
import android.content.Context
import android.content.pm.PackageManager
import android.content.res.ColorStateList
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.os.SystemClock
import android.view.*
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.timelysoft.tsjdomcom.MainActivity
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.adapters.families.FamilyAdapter
import com.timelysoft.tsjdomcom.adapters.families.FamilyListener
import com.timelysoft.tsjdomcom.service.model.PersonModel
import com.timelysoft.tsjdomcom.service.model.RelativeModel
import com.timelysoft.tsjdomcom.service.request.CertificateRequest
import com.timelysoft.tsjdomcom.utils.MyUtils
import kotlinx.android.synthetic.main.fragment_history.*
import kotlinx.android.synthetic.main.fragment_new_reference.*
import kotlinx.android.synthetic.main.fragment_new_reference.view.*
import kotlinx.android.synthetic.main.item_choose_manager.view.*
import java.util.*
import kotlin.collections.ArrayList


class AddUpdateReferenceFragment : Fragment(), FamilyListener {
    private lateinit var relativeAdapter: FamilyAdapter
    private lateinit var viewModel: ReferenceViewModel
    private var update = false
    val certificateRequest = CertificateRequest()
    private var mLastClickTime: Long = 0
    private var chairmanId = 0
    private val STORAGE_PERMISION_CODE: Int = 1000
    private var certificatesUrl = ""
    private lateinit var layoutView: View

    init {
        if (certificateRequest.person == null)
            certificateRequest.person = PersonModel()
        if (certificateRequest.relatives == null)
            certificateRequest.relatives = listOf()
    }

    companion object {
        val relativesList = ArrayList<RelativeModel>()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_new_reference, container, false)
        initArguments()
        initViews(root)

        (activity as AppCompatActivity).supportActionBar!!.show()
        viewModel = ViewModelProviders.of(this).get(ReferenceViewModel::class.java)
        setHasOptionsMenu(true)
        return root
    }

    private fun validate(): Boolean {
        var valid = true
        if (reference_date.text.toString().isEmpty()) {
            reference_date_out.error = "Вы не выбрали дату"
            valid = false
        } else {
            reference_date_out.isErrorEnabled = false
        }

        if (reference_name.text.toString().isEmpty()) {
            reference_name_out.error = "Поле не должно быть пустым"
            valid = false
        } else {
            reference_name_out.isErrorEnabled = false
        }

        return valid
    }

    private fun initHint() {
        if (reference_name.text.isNotEmpty()) {
            reference_name_out.defaultHintTextColor =
                ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
            reference_date_out.defaultHintTextColor =
                ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
        }
    }

    private fun initViews(root: View) {
        root.reference_add_relative.setOnClickListener {
            findNavController().navigate(R.id.navigation_families)
        }
        relativeAdapter = FamilyAdapter(this)
        root.relative_rv.adapter = relativeAdapter
        relativeAdapter.update(relativesList)

        root.reference_name.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus && root.reference_name.text.isNotEmpty()) {
                root.reference_name_out.defaultHintTextColor =
                    ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
                root.reference_name_out.isErrorEnabled = false
            }
        }

        root.reference_save.setOnClickListener {
            MyUtils.hideKeyboard(activity!!, view!!)
            if (validate()) {
                certificateRequest.relatives = relativesList
                certificateRequest.person.fullName = reference_name.text.toString()
                certificateRequest.person.dateOfBirth =
                    MyUtils.toServerDate(reference_date.text.toString())
                if (!update) {
                    MainActivity.alert.show()
                    viewModel.addReferences(certificateRequest).observe(this, Observer {
                        if (it) {
                            findNavController().popBackStack()
                        } else {
                            Toast.makeText(
                                context,
                                "Ошибка при отправке данных",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                        MainActivity.alert.hide()
                    })
                } else {
                    MainActivity.alert.show()
                    viewModel.updateReference(certificateRequest).observe(this, Observer {
                        if (it) {
                            findNavController().popBackStack()
                        } else {
                            Toast.makeText(
                                context,
                                "Ошибка при отправке данных",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                        MainActivity.alert.hide()
                    })
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        if (update) {
            inflater.inflate(R.menu.reference_bottom_senddownload, menu)
            super.onCreateOptionsMenu(menu, inflater)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.send_download -> {
                chooseManagerDialog()
            }
        }
        return super.onOptionsItemSelected(item)

    private fun chooseManagerDialog() {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Выберите председателя ТСЖ")
        val layoutInflater = LayoutInflater.from(context)
        layoutView = layoutInflater.inflate(R.layout.item_choose_manager, null)
        builder.setCancelable(false)
        builder.setView(layoutView)
        val dialog: AlertDialog = builder.create()
        MainActivity.alert.show()

        viewModel.managers(certificateRequest.placementId).observe(this, Observer { list ->
            val adapterAddress =
                ArrayAdapter(context!!, android.R.layout.simple_dropdown_item_1line, list)
            layoutView.reference_dialog_text.setAdapter(adapterAddress)
            MainActivity.alert.hide()

            layoutView.reference_dialog_text.setOnItemClickListener { parent, view, position, id ->
                chairmanId = (list[position]).id
                layoutView.reference_dialog.error = null
                layoutView.reference_dialog.defaultHintTextColor =
                    ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
            }
            MainActivity.alert.hide()
        })

        layoutView.reference_dialog_text.setOnClickListener {
            layoutView.reference_dialog_text.showDropDown()
        }

        layoutView.reference_dialog_text.onFocusChangeListener =
            View.OnFocusChangeListener { view, hasFocus ->
                try {
                    if (hasFocus) {
                        layoutView.reference_dialog_text.showDropDown()
                    }
                    if (!hasFocus && layoutView.reference_dialog_text.text!!.isNotEmpty()) {
                        layoutView.reference_dialog.defaultHintTextColor =
                            ColorStateList.valueOf(resources.getColor(R.color.itemIconTintF))
                        history_address_out.isErrorEnabled = false
                    }

                } catch (e: Exception) {
                }
            }

        layoutView.text_save_reference.setOnClickListener {
            if (isValid(layoutView.reference_dialog_text.text.toString())){
                MainActivity.alert.show()
                viewModel.chooseManager(certificateRequest.id, chairmanId)
                    .observe(this, Observer { url ->
                        if (url.isNotEmpty()){
                            this.certificatesUrl = url
                            checkPermissions(url)
                            Toast.makeText(context, "загрузка началось", Toast.LENGTH_SHORT).show()
                            dialog.dismiss()
                        }else{
                            Toast.makeText(context, "Ошибка проверьте данные на заполнение", Toast.LENGTH_SHORT).show()
                        }
                        MainActivity.alert.hide()
                    })
            }else{
                layoutView.reference_dialog.error = "Поле не может быть пустым"
            }

        }
        layoutView.text_dismiss_reference.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

    private fun isValid(str: String): Boolean {
        var valid = true
        if (str.isEmpty()) {
            valid = false
        }
        return valid
    }

    private fun checkPermissions(url: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(
                    context!!,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_DENIED
            ) {
                //permission denied
                val permissions = arrayOf(
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                )
                //show popup to request runtime permission
                requestPermissions(permissions, STORAGE_PERMISION_CODE)


            } else {
                //permission already granted
                downloadFile()

            }
        } else {
            //system OS is < Marshmallow
            //pickImageFromGallery()
            downloadFile()
        }
    }

    private fun downloadFile() {
        val reguest = DownloadManager.Request(Uri.parse(certificatesUrl))
        reguest.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
        reguest.setTitle(MyUtils.fileName(certificatesUrl))
        reguest.setDescription("Файл загружаеться.....")

        reguest.allowScanningByMediaScanner()
        reguest.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        reguest.setDestinationInExternalPublicDir(
            Environment.DIRECTORY_DOWNLOADS,
            "${System.currentTimeMillis()}"
        )

        val manager = activity?.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        manager.enqueue(reguest)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            STORAGE_PERMISION_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    downloadFile()
                } else {
                    //permission from popup denied
                    Toast.makeText(context, "Нет разрешений", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()


        getEditReferenceS()
        if (update) {
            reference_save.text = "Обновить"
            (activity as AppCompatActivity?)!!.supportActionBar?.title = "Обновить справку"
        }
        if (certificateRequest.id != null && certificateRequest.id != 0 && !update) {
            update = true
            reference_save.text = "Обновить"
            (activity as AppCompatActivity?)!!.supportActionBar?.title = "Обновить справку"
            viewModel.reference(certificateRequest.id).observe(this, Observer {
                certificateRequest.person.id = it.person.id
                reference_name.setText(it.person.fullName)
                reference_date.setText(MyUtils.toMyDate(it.person.dateOfBirth))
                it.relatives?.forEach { item ->
                    relativesList.add(item)
                }
                relativeAdapter.update(relativesList)
                initHint()
            })
        }
        initHint()
    }


    private fun initArguments() {
        certificateRequest.placementId = try {
            arguments!!.getInt("placementId")
        } catch (e: Exception) {
            0
        }

        certificateRequest.id = try {
            arguments!!.getInt("referenceId")
        } catch (e: Exception) {
            0
        }
    }

    private fun getEditReferenceS() {
        reference_date.keyListener = null
        // Временный тракеч. Приложение падает.
        reference_date.setOnFocusChangeListener setOnClickListener@{ _, hasFocus ->
            MyUtils.hideKeyboard(activity!!, view!!)
            if (hasFocus) {
                // Педотврощает двоной клик на editText
                if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
                    return@setOnClickListener
                }
                mLastClickTime = SystemClock.elapsedRealtime()

                val cldr = Calendar.getInstance()
                val col = ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
                reference_date_out.defaultHintTextColor = col
                val picker =
                    DatePickerDialog(
                        activity!!, { _, year1, monthOfYear, dayOfMonth ->
                            reference_date.setText(
                                MyUtils.convertDate(
                                    dayOfMonth,
                                    monthOfYear,
                                    year1
                                )
                            )
                        },
                        cldr.get(Calendar.YEAR),
                        cldr.get(Calendar.MONTH),
                        cldr.get(Calendar.DAY_OF_MONTH)
                    )
                picker.show()
                reference_date.clearFocus()
            }
            if (!hasFocus && reference_date.text.isNotEmpty()) {
                reference_date_out.isErrorEnabled = false
            }
        }
    }

    override fun onClickDelete(id: Int) {
        relativesList.removeAt(id)
        relativeAdapter.update(relativesList)
    }

    override fun onClickItem(id: Int) {
        val bundle = Bundle()
        bundle.putInt("position", id)
        findNavController().navigate(R.id.navigation_families, bundle)
    }
}