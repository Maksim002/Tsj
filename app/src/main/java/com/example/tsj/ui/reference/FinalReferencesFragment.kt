package com.example.tsj.ui.reference


import android.content.res.ColorStateList
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tsj.R
import com.example.tsj.adapters.families.AdapterFamilies
import com.example.tsj.model.FamiliesModel
import kotlinx.android.synthetic.main.fragment_final_references.*

class FinalReferencesFragment : Fragment() {
    private lateinit var refAdapter: AdapterFamilies
    private lateinit var recyclerViewR: RecyclerView
    private lateinit var col:ColorStateList

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root= inflater.inflate(R.layout.fragment_final_references, container, false)
        col = ColorStateList.valueOf(getResources().getColor(R.color.colorAccent))
        recyclerViewR = root.findViewById(R.id.familiesRecyclerView)
        recyclerViewR.apply {
            refAdapter = AdapterFamilies(getFamilies())
            adapter = refAdapter
        }
        return root
    }
    private fun getFamilies(): ArrayList<FamiliesModel>{
        var list = ArrayList<FamiliesModel>()
        list.add(FamiliesModel("Сын","Степанов Владимир Алексеевич","12.05.2001 года рождения"))
        list.add(FamiliesModel("Сын","Степанов Владимир Алексеевич","12.05.2001 года рождения"))
        list.add(FamiliesModel("Сын","Степанов Владимир Алексеевич","12.05.2001 года рождения"))
        list.add(FamiliesModel("Сын","Степанов Владимир Алексеевич","12.05.2001 года рождения"))
        list.add(FamiliesModel("Сын","Степанов Владимир Алексеевич","12.05.2001 года рождения"))

        return list
    }

    override fun onStart() {
        super.onStart()
        getTextOne()
        getTextTwo()
    }

    private fun getTextOne(){
        edit_final_date.setOnFocusChangeListener{ pref, boolean ->
            if (boolean){
                text_final_date.defaultHintTextColor = col
                
            }else{
                text_final_date.defaultHintTextColor = col
            }
        }
    }
    private fun getTextTwo(){
        edit_final_to.setOnFocusChangeListener{ pref, boolean ->
            if (boolean){
                text_final_to.defaultHintTextColor = col
            }else{
                text_final_to.defaultHintTextColor = col
            }
        }
    }
}
