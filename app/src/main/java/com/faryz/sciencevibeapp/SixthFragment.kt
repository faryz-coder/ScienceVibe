package com.faryz.sciencevibeapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.faryz.sciencevibeapp.databinding.FragmentSixthBinding


class SixthFragment : Fragment() {
    private var _binding: FragmentSixthBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSixthBinding.inflate(inflater, container, false)
        binding.appBarName6.text = (activity as MainActivity).name
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.homeImage.setOnClickListener {
            findNavController().popBackStack(R.id.SecondFragment, false)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}