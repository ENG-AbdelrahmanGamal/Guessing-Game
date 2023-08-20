package com.example.guessinggame.result

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import java.lang.IllegalArgumentException

class ResultViewModelFactory (private val finalResult:String):ViewModelProvider.Factory {


    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        if(modelClass.isAssignableFrom(ViewModelResultFragment::class.java))


            return ViewModelResultFragment(finalResult)as T
        throw IllegalArgumentException("unknown view model ")
    }

}