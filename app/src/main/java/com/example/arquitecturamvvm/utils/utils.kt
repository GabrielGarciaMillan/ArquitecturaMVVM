package com.example.arquitecturamvvm.utils

import androidx.recyclerview.widget.DiffUtil

class utils {

    companion object DIFF_CALLBACK: DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem.equals(newItem)
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }


    }
}