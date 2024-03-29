package com.example.guessinggame.game

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorDestinationBuilder
import androidx.navigation.fragment.findNavController
import com.example.guessinggame.R
import com.example.guessinggame.databinding.FragmentGameBinding
import kotlin.random.Random

class GameFragment : Fragment() {
private var _binding:FragmentGameBinding?=null
 private   val binding get() = _binding!!
    private val TAG = "GameFragment"
 lateinit var viewmodel:ViewModelGameFragment
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentGameBinding.inflate(inflater,container,false)
        val view =binding.root

        viewmodel=ViewModelProvider(this).get(ViewModelGameFragment::class.java)
        binding.viewmodel=viewmodel
        viewmodel.livesleft.observe(viewLifecycleOwner,Observer{new_value ->
            binding.lives.text= "You have new value is $new_value"
         //   Log.i(TAG, "onCreateView: ${binding.lives.text?=new_value}")
        })
       viewmodel.secret_word_Display.observe(viewLifecycleOwner, Observer {new_value->
    binding.word.text=new_value
})
        viewmodel.in_correct_Guesses.observe(viewLifecycleOwner, Observer { new_value->
            binding.incorrectGuesses.text=new_value })

        viewmodel.gameOver.observe(viewLifecycleOwner, Observer { new_value->
            if(new_value)
            {
                view.findNavController().navigate(R.id.action_gameFragment_to_resultFragment)
            }
        })
        binding.guessesButton.setOnClickListener {
       viewmodel.makeGuess(binding.guesses.text.toString().uppercase())
       binding.guesses.text=null
//       if (viewmodel.isLost()||viewmodel.isWin())
//       {
//              //     val result= view.findNavController().navigate(isWinOrLost())
//           view.findNavController().navigate(R.id.action_gameFragment_to_resultFragment)
//
//     }
        }

        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }
}