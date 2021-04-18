package com.example.techmecook.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.techmecook.R
import com.google.android.material.snackbar.Snackbar
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.techmecook.BuildConfig
import com.example.techmecook.repositories.*
import com.example.techmecook.model.*
import com.example.techmecook.model.result.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button_first).setOnClickListener {
            //findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
            GlobalScope.launch {
                val repo = RecipeRepository()
                when (val result = repo.getRandomRecipes(1, "")) {
                    is Error -> Log.e("ERROR IN ACTIVITY", "${result.exceptionInfo}")
                    is NetworkError ->  Log.e("ERROR IN ACTIVITY", "INTERNET ERROR") // Snackbar.make(view, "Network error", Snackbar.LENGTH_LONG).setAction("Action", null).show()
                    is Success ->  Log.e("SUCCESS IN ACTIVITY", result.value.recipes[0].analyzedInstructions[0].steps[0].step) //Snackbar.make(view, result.value.title, Snackbar.LENGTH_LONG).setAction("Action", null).show()
                }
            }
        }
    }
}