package ir.apptaste.android.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ir.apptaste.android.R
import ir.apptaste.android.model.api.ResultResponse
import kotlinx.android.synthetic.main.result_list_item.view.*
import javax.inject.Inject

class ResultListAdapter @Inject constructor() :
    RecyclerView.Adapter<ResultListAdapter.ResultListViewHolder>() {
    private val mItems = ArrayList<ResultResponse>()
    var onItemClick: ((ResultResponse) -> Unit)? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultListViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.result_list_item, parent, false)
        return ResultListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ResultListViewHolder, position: Int) {
        holder.bind(mItems[position])
    }

    override fun getItemCount(): Int = mItems.size


    inner class ResultListViewHolder(mItemView: View) :
        RecyclerView.ViewHolder(mItemView) {
        private val name: TextView = mItemView.tvName
        private val type: TextView = mItemView.tvType

        init {
            mItemView.setOnClickListener {
                onItemClick?.invoke(mItems[adapterPosition])
            }
        }

        fun bind(tasteResponse: ResultResponse) {
            name.text = tasteResponse.name
            type.text = tasteResponse.type
        }

    }

    fun addItems(items: ArrayList<ResultResponse>) {
        mItems.clear()
        mItems.addAll(items)
        notifyDataSetChanged()
    }
}