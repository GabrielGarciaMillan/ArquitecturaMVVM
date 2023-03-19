package com.example.arquitecturamvvm.data

import com.example.arquitecturamvvm.model.Course
import com.example.arquitecturamvvm.model.CourseDAO
import java.util.concurrent.Flow

class CourseRepository(private val courseDAO: CourseDAO) {


    fun getCourseNames() = courseDAO.getNames()

    fun getCourseByName(coursename: String): Flow<Course> {
        return courseDAO.getCourseByName(coursename)
    }

    suspend fun insertCourse(course: Course) = courseDAO.insertCourse(course)
}