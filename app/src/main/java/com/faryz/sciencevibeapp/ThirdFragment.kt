package com.faryz.sciencevibeapp

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log.d
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.faryz.sciencevibeapp.databinding.FragmentSecondBinding
import com.faryz.sciencevibeapp.databinding.FragmentThirdBinding


class ThirdFragment : Fragment() {

    private var _binding: FragmentThirdBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
//    var bgMusic: MediaPlayer? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentThirdBinding.inflate(inflater, container, false)
        binding.appBarName3.text = (activity as MainActivity).name
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imageSoundOnOff.setOnClickListener {
            if ((activity as MainActivity).sound) {
                binding.imageSoundOnOff.setImageResource(R.drawable.off_button)
                (activity as MainActivity).sound = false
                d("bomoh", "click")
                requireActivity().stopService(Intent(requireActivity(), MyService::class.java))

            } else {
                binding.imageSoundOnOff.setImageResource(R.drawable.on_button)
                (activity as MainActivity).sound = true
                d("bomoh", "click2")
                requireActivity().startService(Intent(requireActivity(), MyService::class.java))
            }
        }

        binding.editTextTextPersonName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                binding.appBarName3.text = binding.editTextTextPersonName.text.toString()
                (activity as MainActivity).name = binding.editTextTextPersonName.text.toString()
            }

            override fun afterTextChanged(p0: Editable?) {}

        })

        binding.homeImage.setOnClickListener {
            findNavController().popBackStack(R.id.SecondFragment, false)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}