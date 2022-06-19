package com.test.todoapp.common.adapter

import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.test.common.extension.inflate
import com.test.common.entities.CommonItem
import com.test.todoapp.R
import kotlinx.android.synthetic.main.item_common_detail_adapter.view.*

class CommonDetailAdapter(private val commonDetailList: MutableList<CommonItem>) :
    RecyclerView.Adapter<CommonDetailAdapter.BuyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BuyViewHolder {
        return BuyViewHolder(parent.context.inflate(R.layout.item_common_detail_adapter, parent))
    }

    override fun onBindViewHolder(holder: BuyViewHolder, position: Int) {
        holder.bindData(commonDetailList[position], position, commonDetailList.size - 1)
    }

    override fun getItemCount(): Int {
        return commonDetailList.size
    }

    inner class BuyViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bindData(
            item: CommonItem,
            pos: Int,
            lastPos: Int
        ) {
            view.apply {
                tvTitleItem.text = item.value
                tvTitleItem.setTextColor(ContextCompat.getColor(context, item.color))
            }
        }
    }
}

