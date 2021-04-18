package com.example.techmecook.ui.recipe_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.techmecook.databinding.*
import com.example.techmecook.model.*

class RecipeDetailFragment : Fragment() {
    private val viewModel by viewModels<RecipeDetailViewModel>()
    private lateinit var binding: FragmentRecipeDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecipeDetailBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val Id = 639580


        viewModel.getRecipe(Id).observe(viewLifecycleOwner) {
            it?.let {
                binding.recipe = it
                binding.executePendingBindings()
            }
        }

    }

}