package com.example.arquitecturamvvm.domain

import androidx.lifecycle.*
import com.example.arquitecturamvvm.CourseViewModel
import com.example.arquitecturamvvm.data.CourseRepository
import com.example.arquitecturamvvm.model.Course

class CourseDetailsViewModel (val repository: CourseRepository) : ViewModel(){
    private val courseName = MutableLiveData<String>()

    fun setCourse(coursename:String) {
        courseName.value = coursename
    }

    val mCourse: LiveData<Course> = Transformations.switchMap(courseName) {
            name -> repository.getCourseByName(name).asLiveData()
    }
}


class CourseDetailsViewModelFactory(private val repository: CourseRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CourseDetailsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CourseDetailsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
