package com.kotlin.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.kotlin.navigation.databinding.FragmentGameOverBinding

class GameOverFragment : Fragment() {

    lateinit var binding: FragmentGameOverBinding
    private lateinit var navController: NavController

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_game_over, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        navController = view.findNavController()

        binding.tryAgainButton.setOnClickListener {
            navController.navigate(GameOverFragmentDirections.actionGameOverToGame())
        }
    }
}
