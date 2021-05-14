package com.example.techmecook.ui.recipe_detail

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.techmecook.databinding.FragmentRecipeDetailBinding
import com.example.techmecook.model.ingredient.IngredientGeneralInfo
import com.example.techmecook.model.instruction.Instruction
import com.example.techmecook.model.result.Success
import com.example.techmecook.recyclerview.adapters.IngredientAdapter
import com.example.techmecook.recyclerview.adapters.InstructionAdapter
import com.example.techmecook.recyclerview.click_listeners.IngredientClickListener
import com.example.techmecook.recyclerview.click_listeners.InstructionClickListener
import com.example.techmecook.ui.LikeViewModel
import com.example.techmecook.util.getToken

class RecipeDetailFragment : Fragment(), IngredientClickListener, InstructionClickListener {
    private val viewModel by viewModels<RecipeDetailViewModel>()
    private val likeViewModel by viewModels<LikeViewModel>()
    private lateinit var binding: FragmentRecipeDetailBinding
    private val args by navArgs<RecipeDetailFragmentArgs>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRecipeDetailBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recipeId = args.recipeId


        val instAdapter = InstructionAdapter(this)
        binding.instruction.adapter = instAdapter
        val ingrAdapter = IngredientAdapter(this)
        binding.ingredientsList.adapter = ingrAdapter

        getLikes()
        observeLikes()

        viewModel.getRecipe(recipeId).observe(viewLifecycleOwner) {
            it?.let {
                binding.recipe = it
                instAdapter.submitList(it.analyzedInstructions)
                ingrAdapter.submitList(it.extendedIngredients)
                binding.executePendingBindings()
            }
        }

    }

    override fun onClick(ingredient: IngredientGeneralInfo) {
    }

    override fun onClick(instruction: Instruction) {
    }

    private fun getLikes()
    {
        activity?.getToken()?.let { likeViewModel.getLikes(args.recipeId, it) }
        observeLikes()
    }

    private fun observeLikes() {
        likeViewModel.likes.observe(viewLifecycleOwner)
        {
            when (it) {
                is Success -> {
                    Log.e("SUCCESSFULLY LIKED!!", "LIKES!!!")
                    if (it.value.likedByUser)
                        binding.like.setColorFilter(Color.argb(255, 255, 233, 0))
                    else
                        binding.like.setColorFilter(Color.argb(255, 0, 0, 0))
                    binding.likeCounter.text = it.value.likes.size.toString()
                }
            }
        }
    }

}