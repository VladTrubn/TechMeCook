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
import com.example.techmecook.model.ingredient.IngredientGeneralInfo
import com.example.techmecook.recyclerview.adapters.IngredientAdapter
import com.example.techmecook.recyclerview.adapters.InstructionAdapter
import com.example.techmecook.recyclerview.click_listeners.IngredientClickListener

class RecipeDetailFragment : Fragment(), IngredientClickListener {
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

        val random = (690000..720000).random()

        //val instAdapter = InstructionAdapter()
        //binding.instruction.adapter = instAdapter
        val ingrAdapter = IngredientAdapter(this)
        binding.ingredientsList.adapter = ingrAdapter

        viewModel.getRecipe(random).observe(viewLifecycleOwner) {
            it?.let {
                binding.recipe = it
                //instAdapter.submitList(it.analyzedInstructions)
                ingrAdapter.submitList(it.extendedIngredients)
                binding.executePendingBindings()
            }
        }

    }

    override fun onClick(ingredient: IngredientGeneralInfo) {
    }

}