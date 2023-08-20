package com.example.guessinggame.result

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.guessinggame.R
import com.example.guessinggame.databinding.FragmentResultBinding


class ResultFragment : Fragment() {
lateinit var viewModelResult:ViewModelResultFragment
lateinit var viewModelFactory: ResultViewModelFactory
private var _binding:FragmentResultBinding?=null
    val binding get()=_binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
     _binding= FragmentResultBinding.inflate(inflater,container,false)
        val view= binding.root
      //  val result=ResultFragmentArgs.fromBundle(requireArguments()).reselt
        // viewModelResult=ViewModelProvider(requireActivity(),viewModelFactory).get(ViewModelResultFragment::class.java)
       // binding.winLost.text=ResultFragmentArgs.fromBundle(requireArguments()).result
       // binding.winLost.text=viewModelResult.result
        binding.goToGameButton.setOnClickListener {
           view.findNavController().navigate(R.id.action_resultFragment_to_gameFragment)

        }

        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }


}