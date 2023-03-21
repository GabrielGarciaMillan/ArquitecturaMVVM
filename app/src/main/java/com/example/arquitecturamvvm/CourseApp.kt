package com.example.arquitecturamvvm

import android.app.Application
import com.example.arquitecturamvvm.data.CourseRepository
import com.example.arquitecturamvvm.model.CourseDatabase

class CourseApp: Application() {

    val courseDatabase by lazy {
        CourseDatabase.getInstance(this)
    }
    val repository by lazy {
        CourseRepository(courseDatabase!!.courseDao())
    }
}