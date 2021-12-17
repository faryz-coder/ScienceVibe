package com.faryz.sciencevibeapp

import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log.d
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.faryz.sciencevibeapp.databinding.FragmentFourthBinding
import com.faryz.sciencevibeapp.databinding.FragmentSecondBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso

class FourthFragment : Fragment() {

    private var _binding: FragmentFourthBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val listQuestion = mutableListOf<ListQuestion>()
    private val listOption = mutableListOf<ListOption>()
    private val listAnswer = mutableListOf<ListAnswer>()
    private val db = Firebase.firestore
    var count = 0
    private val delay : Long = 2000

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFourthBinding.inflate(inflater, container, false)
        binding.appBarName4.text = (activity as MainActivity).name
        getListQuestion()
        binding.opA.isVisible = false
        binding.opB.isVisible = false
        binding.opC.isVisible = false
        binding.opD.isVisible = false
        binding.imageMonkeyEyeClose.isVisible = false
        binding.monkeyBubble.isVisible = false
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.homeImage.setOnClickListener {
            findNavController().popBackStack(R.id.SecondFragment, false)
        }

        binding.imageA.setOnClickListener {
            submitAnswer("A")
        }
        binding.imageB.setOnClickListener {
            submitAnswer("B")
        }
        binding.imageC.setOnClickListener {
            submitAnswer("C")
        }
        binding.imageD.setOnClickListener {
            submitAnswer("D")
        }
    }

    private fun submitAnswer(ans: String) {
        if (count < 1) {
            nextQuestion()
            binding.opA.isVisible = true
            binding.opB.isVisible = true
            binding.opC.isVisible = true
            binding.opD.isVisible = true
            binding.imageMonkeyEyeClose.isVisible = true
            binding.monkeyBubble.isVisible = true
            binding.layoutA.setBackgroundResource(R.drawable.card_background)
            binding.layoutB.setBackgroundResource(R.drawable.card_background)
            binding.layoutC.setBackgroundResource(R.drawable.card_background)
            binding.layoutD.setBackgroundResource(R.drawable.card_background)
        } else if (count > 0 && count <= listQuestion.size) {
            checkAnswer(ans)
            Handler(Looper.getMainLooper()).postDelayed({
                if (count<listQuestion.size) {
                   nextQuestion()
                } else {
                    findNavController().navigate(R.id.action_FourthFragment_to_SixthFragment)
                }
            }, delay)
        }
    }

    private fun checkAnswer(ans: String) {
        if (listAnswer[count-1].answer == ans) {
            binding.monkeyBubble.setImageResource(R.drawable.correct_logo)
            playCorrect(0)
        } else {
            binding.monkeyBubble.setImageResource(R.drawable.wrong_logo)
            playCorrect(1)
        }
    }

    private fun nextQuestion() {
        if (count < listQuestion.size) {
            binding.exerciseLabel.setText(listQuestion[count].question)
            binding.monkeyBubble.setImageResource(R.drawable.buble_you_can_do_it)
            Picasso.get().load(listOption[count].a).into(binding.imageA)
            Picasso.get().load(listOption[count].b).into(binding.imageB)
            Picasso.get().load(listOption[count].c).into(binding.imageC)
            Picasso.get().load(listOption[count].d).into(binding.imageD)
            playCorrect(2)
            count += 1
        }
    }

    private fun getListQuestion() {
        db.collection("list").document("list question")
            .get()
            .addOnSuccessListener {
                // get list question
                for (question in it.data!!.keys) {
                    listQuestion.add(ListQuestion(question))
                }

                // get option contain link for the image
                for (option in it.data!!.values) {
                    val v = option as ArrayList<String>
                    listOption.add(ListOption(v[0], v[1], v[2], v[3]))
                }
            }
        // get answer
        db.collection("list").document("list answer")
            .get()
            .addOnSuccessListener {
                for (answer in it.data!!.values) {
                    val ans = answer as ArrayList<String>
                    for (a in ans) {
                        listAnswer.add(ListAnswer(a))
                    }
                }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun playCorrect(tf: Int) {
        if ((activity as MainActivity).sound) {
            if (tf == 0) {
                val correctMusic = MediaPlayer.create(requireContext(), R.raw.correct_answer)
                correctMusic.setOnPreparedListener {
                    correctMusic.start()
                }
            } else if (tf == 1) {
                val correctMusic = MediaPlayer.create(requireContext(), R.raw.wrong_answer)
                correctMusic.setOnPreparedListener {
                    correctMusic.start()
                }
            } else if (tf == 2) {
                val correctMusic = MediaPlayer.create(requireContext(), R.raw.popup_sound)
                correctMusic.setOnPreparedListener {
                    correctMusic.start()
                }
            }
        }
    }
}

class ListAnswer (
    val answer: String
)

class ListOption (
    val a: String,
    val b: String,
    val c: String,
    val d: String
)

class ListQuestion (
    val question: String
)