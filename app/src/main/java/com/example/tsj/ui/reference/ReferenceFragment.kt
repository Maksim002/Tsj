package com.example.tsj.ui.reference


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.tsj.R
import com.example.tsj.adapters.NewsAdapter
import com.example.tsj.adapters.references.AdapterReferences
import com.example.tsj.model.ReferencesModel
import kotlinx.android.synthetic.main.fragment_reference.view.*

class ReferenceFragment : Fragment() {

    private lateinit var refAdapter: AdapterReferences
    private lateinit var recyclerViewR: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_reference, container, false)
            recyclerViewR = root.findViewById(R.id.recyclerViewReferences)
            recyclerViewR.apply {
                refAdapter = AdapterReferences(getRef())
                adapter = refAdapter

                val ref = root.findViewById<Button>(R.id.new_references)

                ref.setOnClickListener {
                    findNavController().navigate(R.id.navigation_new_reference)
                }
            }

        (activity as AppCompatActivity).supportActionBar?.show()
        return root
    }
    private fun getRef(): ArrayList<ReferencesModel>{
        var list = ArrayList<ReferencesModel>()
        list.add(ReferencesModel("№ 2/2-20","Антонову Степану Александровичу","От 21.02.2020"))
        list.add(ReferencesModel("№ 2/2-20","Антонову Степану Александровичу","От 21.02.2020"))
        list.add(ReferencesModel("№ 2/2-20","Антонову Степану Александровичу","От 21.02.2020"))
        list.add(ReferencesModel("№ 2/2-20","Антонову Степану Александровичу","От 21.02.2020"))
        list.add(ReferencesModel("№ 2/2-20","Антонову Степану Александровичу","От 21.02.2020"))
        list.add(ReferencesModel("№ 2/2-20","Антонову Степану Александровичу","От 21.02.2020"))
        list.add(ReferencesModel("№ 2/2-20","Антонову Степану Александровичу","От 21.02.2020"))
        list.add(ReferencesModel("№ 2/2-20","Антонову Степану Александровичу","От 21.02.2020"))
        list.add(ReferencesModel("№ 2/2-20","Антонову Степану Александровичу","От 21.02.2020"))
        list.add(ReferencesModel("№ 2/2-20","Антонову Степану Александровичу","От 21.02.2020"))
        return list
    }
}
