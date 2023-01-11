package com.example.cardbank.ui.room.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cardbank.R
import com.example.cardbank.databinding.RecyclerRoomItemListBinding
import com.example.cardbank.domain.data.models.base.Bank

class ViewHolderRoom(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val binding = RecyclerRoomItemListBinding.bind(itemView)

    companion object {
        fun createView(parent: ViewGroup): ViewHolderRoom {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_room_item_list, parent, false)
            return ViewHolderRoom(view)
        }
    }

    fun bind(item: Bank) {
        binding.itemNameBankTv.text = item.name
        binding.itemPhoneTv.text = item.phone
    }

}