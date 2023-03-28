package com.example.arquitecturamvvm.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.arquitecturamvvm.CourseApp
import com.example.arquitecturamvvm.CourseViewModel
import com.example.arquitecturamvvm.CourseViewModelFactory
import com.example.arquitecturamvvm.R
import com.example.arquitecturamvvm.adapters.CourseListAdapter
import com.example.arquitecturamvvm.databinding.FragmentDetailsBinding
import com.example.arquitecturamvvm.databinding.FragmentListBinding
import com.example.arquitecturamvvm.domain.CourseDetailsViewModel
import com.example.arquitecturamvvm.domain.CourseDetailsViewModelFactory
import com.example.arquitecturamvvm.model.Course

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null

    private val courseDetailsViewModel: CourseDetailsViewModel by viewModels {
        CourseDetailsViewModelFactory((activity?.application as CourseApp).repository)
    }

    private lateinit var observer : LiveData<Course>


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            var asignatura : String? = it.getString("asignatura")
            courseDetailsViewModel.setCourse(asignatura!!)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        courseDetailsViewModel.mCourse.observe(viewLifecycleOwner){
            binding.tVAsignatura.text = it.name
            binding.tVProfesor.text = it.teacher
            binding.tVDetalles.text = it.description
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}