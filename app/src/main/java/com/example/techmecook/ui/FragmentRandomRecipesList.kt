package com.example.techmecook.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.techmecook.R

class   FragmentRandomRecipesList : Fragment() {

    companion object {
        fun newInstance() = FragmentRandomRecipesList()
    }

    private lateinit var viewModel: FragmentRandomRecipesListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_random_recipes_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FragmentRandomRecipesListViewModel::class.java)
        // TODO: Use the ViewModel
    }

}