package com.example.guessinggame.game


import android.util.Log
import androidx.lifecycle.ViewModel

class ViewModelGameFragment : ViewModel() {
    private  val TAG = "ViewModelGameFragment"
    val words= listOf<String>("Activity", "Kotlin","Fragment","Android")
    val secret_word = words.random().uppercase()
    var secret_word_Display=""
    var correct_Guesses=""
    var in_correct_Guesses=""
    var livesleft=8
init {
    secret_word_Display=driveSecritWordDisplayed()

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
}