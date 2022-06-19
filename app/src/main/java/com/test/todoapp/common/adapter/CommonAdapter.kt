package com.test.todoapp.common.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.common.extension.inflate
import com.test.common.entities.CommonDataEntity
import com.test.todoapp.R
import kotlinx.android.synthetic.main.item_common_adapter.view.*

class CommonAdapter(private val commonList: MutableList<CommonDataEntity>) :
    RecyclerView.Adapter<CommonAdapter.BuyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BuyViewHolder {
        return BuyViewHolder(parent.context.inflate(R.layout.item_common_adapter, parent))
    }

    override fun onBindViewHolder(holder: BuyViewHolder, position: Int) {
        holder.bindData(commonList[position], position, commonList.size - 1)
    }

    override fun getItemCount(): Int {
        return commonList.size
    }

    inner class BuyViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        fun bindData(
            item: CommonDataEntity,
            pos: Int,
            lastPos: Int
        ) {
            view.apply {
                val commonAdapter = CommonDetailAdapter(item.dataDetailList.toMutableList())
                rcvCommon.adapter = commonAdapter
            }
        }
    }
}

