package com.example.techmecook.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.techmecook.databinding.FragmentCommentListBinding
import com.example.techmecook.model.comment.Comment
import com.example.techmecook.model.comment.CommentCreate
import com.example.techmecook.model.result.Error
import com.example.techmecook.model.result.NetworkError
import com.example.techmecook.model.result.Success
import com.example.techmecook.recyclerview.adapters.CommentAdapter
import com.example.techmecook.recyclerview.click_listeners.CommentClickListener
import com.example.techmecook.util.showShortText

class CommentFragment : Fragment(), CommentClickListener {
    private val viewModel by viewModels<CommentViewModel>()
    private lateinit var binding: FragmentCommentListBinding


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = FragmentCommentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val adapter = CommentAdapter(this)
        binding.recView.adapter = adapter

        var recipeId = 639456
        fetchComments(recipeId)
       /* postComment((CommentCreate("FFFFFFFFFFFFFFFFFUUUUUUUUUUUUUUUUUUUUUCCCCCCCCCCCCCCCCKKKKKKKKKKKKKKKKKK", "j@gmail.com", "639456")))
        viewModel.createResponse.observe(viewLifecycleOwner)
        {
            when (it) {
                is Success -> {
                   // adapter.submitList(adapter.currentList+it.value)
                }
            }
        } */

        viewModel.comments.observe(viewLifecycleOwner) {
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
    }

    private fun fetchComments(CommentId: Int) {
        Log.e("AAAAAAAAAAAAAAAA", "UUUUUUUUUUUUUUUUU")
        viewModel.getComments(CommentId)
    }

    private fun postComment(comment: CommentCreate)
    {
        viewModel.postComment(comment)
    }

    override fun onClick(comment: Comment) {
    }

}

