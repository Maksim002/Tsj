package com.example.tsj.ui.personal


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.example.tsj.R
import com.example.tsj.adapters.pesonal.PersonalAdapter
import com.example.tsj.utils.MyUtils
import kotlinx.android.synthetic.main.fragment_personal.*
import java.lang.Exception

class PersonalFragment : Fragment() {

    private lateinit var adapters: PersonalAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var textCurrant: TextView
    private lateinit var textAddress: TextView
    private lateinit var textOperation: TextView
    private lateinit var textService: TextView
    private lateinit var textToFrom: TextView
    private lateinit var textBalance: TextView
    private lateinit var viewModel: PersonalViewModel
    private var to: String? = ""
    private var from: String? = ""

    private var servicesId: Int = 0
    private var operationsId: Int = 0
    private var placementId: Int = 0

    var bSave: Button? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_personal, container, false)
        viewModel = ViewModelProviders.of(this).get(PersonalViewModel::class.java)

        bSave = root.findViewById(R.id.bottomSave)

        textCurrant = root.findViewById(R.id.list_currant)
        textAddress = root.findViewById(R.id.text_address)
        textOperation = root.findViewById(R.id.text_operation_name)
        textService = root.findViewById(R.id.text_service_name)
        textToFrom = root.findViewById(R.id.text_to_from)
        textBalance = root.findViewById(R.id.text_balance)

        recyclerView = root.findViewById(R.id.recyclerPersonal)
        getRecyclerView()

        return root
    }

    override fun onStart() {
        super.onStart()
        getBtn()
        getTextCurrant()

        viewModel.invoices(servicesId, operationsId, placementId, MyUtils.toServerDate(to!!), MyUtils.toServerDate(from!!))
            .observe(this, Observer { list ->
                textBalance.setText("Текущий баланс " + list.balance)
            })

        viewModel.account(servicesId, operationsId, placementId, MyUtils.toServerDate(to!!), MyUtils.toServerDate(from!!)).observe(this, Observer {
            println()
        })

    }

    private fun getBtn() {
        try {
            if (arguments!!.getBoolean("btn")) {
                bottomSave.visibility = View.GONE
            }
        } catch (e: Exception) {
        }
    }

    private fun getTextCurrant() {
        val id = try {
            arguments!!.getInt("res")
        } catch (e: Exception) {
            0
        }

        val serviceName = try {
            arguments!!.getString("serviceName")
        } catch (e: Exception) {
            ""
        }
        servicesId = try {
            arguments!!.getInt("servicesId")
        } catch (e: Exception) {
            0
        }

        val operationName = try {
            arguments!!.getString("operationName")
        } catch (e: Exception) {
            ""
        }
        operationsId = try {
            arguments!!.getInt("operationsId")
        } catch (e: Exception) {
            0
        }

        val address = try {
            arguments!!.getString("address")
        } catch (e: Exception) {
            ""
        }
        placementId = try {
            arguments!!.getInt("placementId")
        } catch (e: Exception) {
            0
        }

        to = try {
            arguments!!.getString("to")
        } catch (e: Exception) {
            ""
        }
        from = try {
            arguments!!.getString("from")
        } catch (e: Exception) {
            ""
        }


        textCurrant.setText("Лицевой счет №" + id)
        textAddress.setText(address.toString())
        textOperation.setText(operationName.toString())
        textService.setText(serviceName.toString())
        textToFrom.setText("История оплат с " + to + "-" + from)

    }
    private fun getRecyclerView() {
        recyclerView.apply {
            adapters = PersonalAdapter()
            adapter = adapters
        }
    }
}
