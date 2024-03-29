package com.example.arquitecturamvvm.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.arquitecturamvvm.CourseApp
import com.example.arquitecturamvvm.CourseViewModel
import com.example.arquitecturamvvm.CourseViewModelFactory
import com.example.arquitecturamvvm.R
import com.example.arquitecturamvvm.adapters.CourseListAdapter
import com.example.arquitecturamvvm.databinding.FragmentListBinding
const val ASIGNATURA = "asignatura"
/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null

    private val courseViewModel: CourseViewModel by viewModels {
        CourseViewModelFactory((activity?.application as CourseApp).repository)
    }

    private val adapter = CourseListAdapter { it ->
        val bundle = Bundle().apply {
            putString(ASIGNATURA,it)
        }
        findNavController().navigate(R.id.action_fromlist_to_details,bundle)
    }

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        courseViewModel.courseNames.observe(viewLifecycleOwner) { names ->
            names.let { adapter.submitList(names.toMutableList()) }
        }

        binding.RecyclerView.adapter = adapter
        binding.RecyclerView.layoutManager = LinearLayoutManager(context)


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}