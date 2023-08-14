package com.example.guessinggame.game

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.guessinggame.R
import com.example.guessinggame.databinding.FragmentGameBinding
import kotlin.random.Random

class GameFragment : Fragment() {
private var _binding:FragmentGameBinding?=null
 private   val binding get() = _binding!!
    private val TAG = "GameFragment"
    val words= listOf<String>("Activity", "Kotlin","Fragment","Android")
    val secret_word = words.random().uppercase()
    var secret_word_Display=""
    var correct_Guesses=""
    var In_correct_Guesses=""
    var livesleft=8
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentGameBinding.inflate(inflater,container,false)
        val view =binding.root


        binding.guessesButton.setOnClickListener {
            Log.i(TAG, "onCreateView: button press")
        }
        return view
    }

  

}