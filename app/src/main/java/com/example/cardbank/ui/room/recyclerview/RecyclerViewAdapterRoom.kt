package com.example.cardbank.ui.room.recyclerview

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cardbank.domain.data.models.base.Bank

class RecyclerViewAdapterRoom(private val itemClick: (Bank) -> Unit) : RecyclerView.Adapter<ViewHolderRoom>() {

    private var bankList: List<Bank> = arrayListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newBankList: List<Bank>) {
        this.bankList = newBankList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderRoom {
        return ViewHolderRoom.createView(parent)
    }

    override fun onBindViewHolder(holder: ViewHolderRoom, position: Int) {
        holder.bind(getItem(position))
    }

    private fun getItem(position: Int): Bank = bankList[position]

    override fun getItemCount() = bankList.size
}