package com.example.arquitecturamvvm.adapters

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.arquitecturamvvm.databinding.ListItemBinding
import com.example.arquitecturamvvm.model.Course

class CourseViewHolder (val itemBinding: ListItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(coursename : String){
        itemBinding.tVNombre.text = coursename
    }




}