package com.example.techmecook.ui.recipe_detail

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.techmecook.databinding.FragmentRecipeDetailBinding
import com.example.techmecook.model.comment.Comment
import com.example.techmecook.model.comment.CommentCreate
import com.example.techmecook.model.ingredient.IngredientGeneralInfo
import com.example.techmecook.model.instruction.Instruction
import com.example.techmecook.model.result.Error
import com.example.techmecook.model.result.NetworkError
import com.example.techmecook.model.result.Success
import com.example.techmecook.recyclerview.adapters.CommentAdapter
import com.example.techmecook.recyclerview.adapters.IngredientAdapter
import com.example.techmecook.recyclerview.adapters.InstructionAdapter
import com.example.techmecook.recyclerview.click_listeners.CommentClickListener
import com.example.techmecook.recyclerview.click_listeners.IngredientClickListener
import com.example.techmecook.recyclerview.click_listeners.InstructionClickListener
import com.example.techmecook.ui.CommentViewModel
import com.example.techmecook.util.getToken
import com.example.techmecook.util.showShortText

class RecipeDetailFragment : Fragment(), IngredientClickListener, InstructionClickListener,
        CommentClickListener {
    private val viewModel by viewModels<RecipeDetailViewModel>()
    private val commentViewModel by viewModels<CommentViewModel>()
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

        val adapter = CommentAdapter(this)
        binding.recView.adapter = adapter

        fetchComments(args.recipeId)
        commentViewModel.comments.observe(viewLifecycleOwner) {
            when (it) {
                is Error -> {
                    requireContext().showShortText("Data couldn't be fetched")
                }
                NetworkError -> {
                    requireContext().showShortText("Internet connection failed!")
                }
                is Success -> {
                    adapter.submitList(it.value)
                }
            }
        }


        commentViewModel.createResponse.observe(viewLifecycleOwner) {
            when (it) {
                is Error -> {
                    requireContext().showShortText("Comment send error")
                }
                NetworkError -> {
                    requireContext().showShortText("Internet connection failed!")
                }
                is Success -> {
                    Log.d("Comment", it.value.toString())
                    adapter.submitList(adapter.currentList + it.value)
                }
            }
        }

        binding.commentSend.setOnClickListener { sendComment() }


        val instAdapter = InstructionAdapter(this)
        binding.instruction.adapter = instAdapter
        val ingrAdapter = IngredientAdapter(this)
        binding.ingredientsList.adapter = ingrAdapter

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

    private fun fetchComments(CommentId:     Int) {
        commentViewModel.getComments(CommentId)
    }


    private fun sendComment() {
        val text = binding.commentInput.text.toString()
        if (text.isEmpty()) return

        val commentCreate = CommentCreate(text, requireActivity().getToken(), args.recipeId.toString())
        commentViewModel.sendComment(commentCreate)
        binding.commentInput.setText("" )
    }



    override fun onClick(comment: Comment) {
    }


}