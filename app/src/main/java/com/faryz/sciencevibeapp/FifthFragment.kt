package com.faryz.sciencevibeapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.faryz.sciencevibeapp.databinding.FragmentFifthBinding

class FifthFragment : Fragment() {
    private var _binding: FragmentFifthBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFifthBinding.inflate(inflater, container, false)
        binding.appBarName3.text = (activity as MainActivity).name
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.startNowButton.setOnClickListener {
            findNavController().navigate(R.id.action_FifthFragment_to_FourthFragment)
        }
        binding.homeImage.setOnClickListener {
            findNavController().popBackStack(R.id.SecondFragment, false)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}