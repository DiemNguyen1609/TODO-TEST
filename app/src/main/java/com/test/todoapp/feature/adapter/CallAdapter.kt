package com.test.todoapp.feature.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.common.extension.inflate
import com.test.common.entities.CommonDataEntity
import com.test.todoapp.R
import com.test.todoapp.common.adapter.CommonDetailAdapter
import kotlinx.android.synthetic.main.item_call_adapter.view.*

class CallAdapter(private val callList: MutableList<CommonDataEntity>) :
    RecyclerView.Adapter<CallAdapter.BuyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BuyViewHolder {
        return BuyViewHolder(parent.context.inflate(R.layout.item_call_adapter, parent))
    }

    override fun onBindViewHolder(holder: BuyViewHolder, position: Int) {
        holder.bindData(callList[position], position, callList.size - 1)
    }

    override fun getItemCount(): Int {
        return callList.size
    }

    inner class BuyViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        fun bindData(
            item: CommonDataEntity,
            pos: Int,
            lastPos: Int
        ) {
            view.apply {
                val commonAdapter = CommonDetailAdapter(item.dataDetailList.toMutableList())
                rcvCallDetail.adapter = commonAdapter
            }
        }
    }
}

