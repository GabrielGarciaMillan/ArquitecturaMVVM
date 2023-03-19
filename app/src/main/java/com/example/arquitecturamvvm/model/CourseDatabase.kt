package com.example.arquitecturamvvm.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Course::class], version = 1)
abstract class CourseDatabase: RoomDatabase() {
    abstract fun courseDao(): CourseDAO

    companion object {
        private var INSTANCE: CourseDatabase? = null

        fun getInstance(context: Context): CourseDatabase? {
            if (INSTANCE == null) {
                synchronized(CourseDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        CourseDatabase::class.java, "course.db"
                    )
                        .addCallback(CALLBACK)
                        .build()
                }
            }
            return INSTANCE!!
        }

        fun destroyInstance() {
            INSTANCE = null
        }

        private val CALLBACK = object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)

                CoroutineScope(Dispatchers.IO). launch {
                    INSTANCE!!.courseDao().insertCourse(
                        Course(
                            "Informatica Movil",
                            "Jose Ramon",
                            "Desarrollo de aplicaciones android"
                        )
                    )
                }
            }
        }
    }
}