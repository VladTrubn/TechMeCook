package com.example.techmecook.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.techmecook.R

class FragmentRecipeDetails : Fragment() {

    companion object {
        fun newInstance() = FragmentRecipeDetails()
    }

    private lateinit var viewModel: FragmentRecipeDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recipe_details, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FragmentRecipeDetailsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}