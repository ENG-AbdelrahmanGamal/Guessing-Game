package com.example.guessinggame.game

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
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
    val words= listOf<String>("Activity", "Kotlin","Fragment","Android")
    val secret_word = words.random().uppercase()
    var secret_word_Display=""
    var correct_Guesses=""
    var in_correct_Guesses=""
    var livesleft=8
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentGameBinding.inflate(inflater,container,false)
        val view =binding.root



       secret_word_Display=driveSecritWordDisplayed()
        updateSecret()
        binding.guessesButton.setOnClickListener {
       makeGuess(binding.guesses.text.toString().uppercase())
       binding.guesses.text=null
       updateSecret()
       if (isLost()||isWin())
       {
              //     val result= view.findNavController().navigate(isWinOrLost())
           view.findNavController().navigate(R.id.action_gameFragment_to_resultFragment)

     }


        }

        return view
    }

    private fun updateSecret() {
     binding.word.text=secret_word_Display
        binding.lives.text="you have $livesleft lives lift "
    }

    private fun driveSecritWordDisplayed(): String {
        var display=""
        secret_word.forEach { display+=checkLetter(it.toString()) }
return display
    }



    private fun checkLetter(toString: String)=when(correct_Guesses.contains(toString)){

true ->toString
        false->"_"
    }
    fun makeGuess(guess:String) {
        if (guess.length==1){
            correct_Guesses+=guess
            secret_word_Display=driveSecritWordDisplayed()

        }else{
            in_correct_Guesses+=guess
            livesleft--
        }
    }
    fun isWin() =secret_word.equals(secret_word_Display,true)
    fun isLost()=livesleft<=0

    fun isWinOrLost():String{
        var message=""

        if(isWin())message= "you are Win Congratulation"
       else if(isLost())message="You are lost!"
        message +="the correct word was $secret_word "
        return message
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }
}