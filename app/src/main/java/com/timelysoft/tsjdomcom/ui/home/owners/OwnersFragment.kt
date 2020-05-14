package com.timelysoft.tsjdomcom.ui.home.owners

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.adapters.owners.OwnersAdapter
import com.timelysoft.tsjdomcom.adapters.owners.OwnersModel
import com.timelysoft.tsjdomcom.ui.home.main.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_owners.at_home_recycler_view
import kotlinx.android.synthetic.main.fragment_owners.floatingActionButton

class OwnersFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_owners, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initViews()
    }
    fun initRecyclerView(){
        val list: ArrayList<OwnersModel> = ArrayList()
        list.add(OwnersModel("Активен","Бейшенкулова Ж. Ж.","Прописка 8-46-5","Заблокировать","Зарегистрировать","",""))
        list.add(OwnersModel("Активен","Бейшенкулова Ж. Ж.","Прописка 8-46-5","Заблокировать","","","tsjreceiver@yandex.ru"))
        list.add(OwnersModel("Активен","Бейшенкулова Ж. Ж.","Прописка 8-46-5","Заблокировать","","","ermek_akmatov@inbox.ru"))
        list.add(OwnersModel("Активен","Бейшенкулова Ж. Ж.","Прописка 8-46-5","Заблокировать","Зарегистрировать","",""))
        list.add(OwnersModel("Активен","Бейшенкулова Ж. Ж.","Прописка 8-46-5","Заблокировать","Зарегистрировать","",""))
        list.add(OwnersModel("Активен","Бейшенкулова Ж. Ж.","Прописка 8-46-5","Заблокировать","Зарегистрировать","",""))

        val adapters = OwnersAdapter()
        adapters.update(list)
        at_home_recycler_view.adapter = adapters
    }

    private fun initViews() {
        floatingActionButton.setOnClickListener {
            val atBottomSheetDialog =
                BottomSheetDialogFragment()
            atBottomSheetDialog.show(fragmentManager!!, "AtBottomSheetDialog")
        }
    }
}
