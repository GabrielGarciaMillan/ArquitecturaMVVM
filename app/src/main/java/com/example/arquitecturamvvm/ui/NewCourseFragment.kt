package com.example.arquitecturamvvm.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.arquitecturamvvm.CourseApp
import com.example.arquitecturamvvm.CourseViewModel
import com.example.arquitecturamvvm.CourseViewModelFactory
import com.example.arquitecturamvvm.databinding.FragmentListBinding
import com.example.arquitecturamvvm.databinding.FragmentNewCourseBinding
import com.example.arquitecturamvvm.model.Course
import com.example.arquitecturamvvm.model.CourseDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class NewCourseFragment : Fragment() {

    private var _binding: FragmentNewCourseBinding? = null

    private val courseViewModel: CourseViewModel by viewModels {
        CourseViewModelFactory((activity?.application as CourseApp).repository)
    }

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentNewCourseBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.bGuardar.setOnClickListener {
            CoroutineScope(Dispatchers.IO). launch {
                courseViewModel.repository.insertCourse(getData())
            }
            //Navegar a listfragment
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun getData() : Course {
        var course : Course = Course(binding.tInNombre.editText.toString(),binding.tInProfesor.editText.toString(),binding.tInDetalles.editText.toString())
        return course
    }
}