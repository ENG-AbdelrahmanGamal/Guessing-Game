package com.example.guessinggame.game


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ViewModelGameFragment : ViewModel() {
    private  val TAG = "ViewModelGameFragment"
    val words= listOf<String>("Activity", "Kotlin","Fragment","Android")
    val secret_word = words.random().uppercase()
    var correct_Guesses=("")

    val  secret_word_Display=MutableLiveData<String>()
    val in_correct_Guesses=MutableLiveData<String>("")
    val  livesleft =MutableLiveData<Int>(8)
init {
    secret_word_Display.value=driveSecritWordDisplayed()
    Log.i(TAG, "created: ")
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
        if (guess.length==1) {
            if (secret_word.contains(guess)) {
                correct_Guesses += guess
                secret_word_Display.value = driveSecritWordDisplayed()

            } else {
                in_correct_Guesses.value += guess
                livesleft.value=livesleft.value?.minus(1)
            }
        }
    }
    fun isWin() =secret_word.equals(secret_word_Display.value,true)
    fun isLost()=livesleft.value?:0 <= 0

    fun isWinOrLost():String{
        var message=""

        if(isWin())message= "you are Win Congratulation"
        else if(isLost())message="You are lost!"
        message +="the correct word was $secret_word "
        return message
    }

    override fun onCleared() {
        super.onCleared()
        Log.i(TAG, "onCleared:  cleared")
    }
    fun jointogether() {
        Log.i(TAG, "jointogether:  jointogether")
    }
}