package com.test.todoapp.feature.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.common.extension.inflate
import com.test.domain.entities.BuyItemResult
import com.test.todoapp.R

class BuyAdapter(private val buyList: MutableList<BuyItemResult>) :
    RecyclerView.Adapter<BuyAdapter.BuyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BuyViewHolder {
        return BuyViewHolder(parent.context.inflate(R.layout.item_buy_adapter, parent))
    }

    override fun onBindViewHolder(holder: BuyViewHolder, position: Int) {
        holder.bindData(buyList[position], position, buyList.size - 1)
    }

    override fun getItemCount(): Int {
        return buyList.size
    }

    inner class BuyViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bindData(
            item: BuyItemResult,
            pos: Int,
            lastPos: Int
        ) {

        }
    }
}