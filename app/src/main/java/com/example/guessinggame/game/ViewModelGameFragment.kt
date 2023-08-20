package com.example.guessinggame.game


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ViewModelGameFragment : ViewModel() {
    private  val TAG = "ViewModelGameFragment"
   private val words= listOf<String>("Activity", "Kotlin","Fragment","Android")
 private   val secret_word = words.random().uppercase()
  private    var correct_Guesses=("")

   private val  _secret_word_Display=MutableLiveData<String>()
    val secret_word_Display:LiveData<String> get()=_secret_word_Display
  private  val _in_correct_Guesses=MutableLiveData<String>("")
    val in_correct_Guesses:LiveData<String> get() = _in_correct_Guesses
  private  val  _livesleft =MutableLiveData<Int>(8)
    val livesleft:LiveData<Int>get() = _livesleft
    private val _game_over=MutableLiveData<Boolean>(false)
    val gameOver :LiveData<Boolean>get() = _game_over

init {
    _secret_word_Display.value=driveSecritWordDisplayed()
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
                _secret_word_Display.value = driveSecritWordDisplayed()

            } else {
                _in_correct_Guesses.value += guess
                _livesleft.value=livesleft.value?.minus(1)
            }
        }
        if(isWin() ||  isLost() )  _game_over.value=true

    }
  private  fun isWin() =secret_word.equals(secret_word_Display.value,true)
 private   fun isLost()=livesleft.value?:0 <= 0

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