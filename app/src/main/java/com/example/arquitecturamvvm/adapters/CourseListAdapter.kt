package com.example.arquitecturamvvm.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.arquitecturamvvm.databinding.ListItemBinding
import com.example.arquitecturamvvm.utils.utils

class CourseListAdapter : ListAdapter<String, CourseViewHolder>(utils.DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val itemBinding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CourseViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        holder.bind(this.getItem(position))
    }

}