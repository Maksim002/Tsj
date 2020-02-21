package com.example.tsj.ui.personal


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.tsj.R
import com.example.tsj.adapters.pesonal.PersonalAdapter
import com.example.tsj.model.PersonalModel
import kotlinx.android.synthetic.main.fragment_personal.*
import kotlinx.android.synthetic.main.fragment_personal.view.*
import java.lang.Exception

class PersonalFragment : Fragment() {

    private lateinit var adapters: PersonalAdapter
    private lateinit var recyclerView: RecyclerView

    var bSave: Button? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_personal, container, false)
        bSave = root.findViewById(R.id.bottomSave)


        recyclerView = root.findViewById(R.id.recyclerPersonal)
        getRecyclerView()
        getDataSource()
        adapters.submitList(getDataSource())

        return root
    }

    override fun onStart() {
        super.onStart()
        try {
            if (arguments!!.getBoolean("btn")){
                bottomSave.visibility = View.GONE
            }

        }catch (e:Exception){

        }
    }

    private fun getDataSource(): ArrayList<PersonalModel>{
            val list = ArrayList<PersonalModel>()
            list.add(PersonalModel("641.52","01/12/2019"))
            list.add(PersonalModel("641.52","01/12/2019"))
            list.add(PersonalModel("641.52","01/12/2019"))
            list.add(PersonalModel("620.83","31/12/2019"))
            list.add(PersonalModel("620.83","31/12/2019"))
            list.add(PersonalModel("620.83","31/12/2019"))
            list.add(PersonalModel("620.83","31/12/2019"))
            list.add(PersonalModel("620.83","31/12/2019"))
            return list
    }
    private fun getRecyclerView() {
        recyclerView.apply {
            adapters = PersonalAdapter()
            adapter = adapters
        }
    }

}
