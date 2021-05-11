package com.example.techmecook.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.techmecook.databinding.DialogFilterBinding
import com.example.techmecook.databinding.FragmentRecipeListBinding
import com.example.techmecook.model.recipe.RecipeLight
import com.example.techmecook.model.result.Error
import com.example.techmecook.model.result.NetworkError
import com.example.techmecook.model.result.Success
import com.example.techmecook.recyclerview.adapters.RecipeListAdapter
import com.example.techmecook.recyclerview.click_listeners.RecipeClickListener
import com.example.techmecook.util.showShortText
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class RecipeListFragment : Fragment(), RecipeClickListener {
    private val viewModel by viewModels<RecipeListViewModel>()
    private lateinit var binding: FragmentRecipeListBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRecipeListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val adapter = RecipeListAdapter(this)
        binding.recView.adapter = adapter

        binding.swipeRefreshLayout.isRefreshing = true
        fetchRandomRecipes()

        viewModel.recipes.observe(viewLifecycleOwner) {
            when (it) {
                is Error -> {
                    requireContext().showShortText("Data couldn't be fetched")
                    binding.swipeRefreshLayout.isRefreshing = false
                }
                NetworkError -> {
                    requireContext().showShortText("Internet connection failed!")
                    binding.swipeRefreshLayout.isRefreshing = false
                }
                is Success -> {
                    adapter.submitList(it.value)
                    binding.swipeRefreshLayout.isRefreshing = false
                }
            }
        }

        binding.floatingActionButton.setOnClickListener {
            showFilterDialog()
            binding.swipeRefreshLayout.isRefreshing = true
        }

        binding.swipeRefreshLayout.setOnRefreshListener {
            fetchRandomRecipes()
        }
    }

    private fun fetchRandomRecipes() {
        viewModel.getRandomRecipes()
    }

    override fun onClick(recipe: RecipeLight) {
        findNavController().navigate(
            RecipeListFragmentDirections.actionRecipeListFragmentToRecipeDetailFragment(recipe.id)
        )
    }

    private fun showFilterDialog() {
        val dialogBinding = DialogFilterBinding.inflate(layoutInflater)
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Filter")
            .setNeutralButton("Cancel") { _, _ -> }
            .setPositiveButton("Search") { _, _ -> }
            .setView(dialogBinding.root)
            .setOnDismissListener { binding.swipeRefreshLayout.isRefreshing = false }
            .show()
    }

}