package com.timelysoft.tsjdomcom.adapters.edit
import android.text.Editable
import android.text.TextWatcher
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.common.GenericRecyclerAdapter
import com.timelysoft.tsjdomcom.common.ViewHolder
import kotlinx.android.synthetic.main.fragment_edit_estimate.view.edit_estimate_recycler
import kotlinx.android.synthetic.main.item_edit_estemate_template.view.*

class EditEstimateTemplatesAdapter (var date: ArrayList<EditEstimateTemplatesModel> = arrayListOf()): GenericRecyclerAdapter<EditEstimateTemplatesModel>(date){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return super.onCreateViewHolder(parent, R.layout.item_edit_estemate_template)
    }

    override fun bind(item: EditEstimateTemplatesModel, holder: ViewHolder) {
        val myAdapter = EditEstimateTemplatesAdapter()
        myAdapter.items = item.list
        holder.itemView.edit_estimate_recycler.adapter = myAdapter
        holder.itemView.edit_estimate_name_out.setText(item.name)
        holder.itemView.edit_estimate_sum_out.setText(item.sum)

        holder.itemView.edit_estimate_add.setOnClickListener {
            item.list.add(EditEstimateTemplatesModel("", ""))
            notifyItemRangeChanged(holder.adapterPosition, item.list.size)
        }

        holder.itemView.edit_estimate_clear.setOnClickListener {
            items.removeAt(holder.adapterPosition)
            notifyItemRemoved(holder.adapterPosition)
            notifyItemRangeChanged(holder.adapterPosition, date.size)
        }

        holder.itemView.edit_estimate_sum_out.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                try {
                    item.sum = holder.itemView.edit_estimate_sum_out.text.toString()
                } catch (e: Exception) {
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })


        holder.itemView.edit_estimate_name_out.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                try {
                    item.name = holder.itemView.edit_estimate_name_out.text.toString()
                } catch (e: Exception) {
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }
}