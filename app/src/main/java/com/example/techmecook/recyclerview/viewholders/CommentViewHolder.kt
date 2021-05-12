package com.example.techmecook.recyclerview.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.example.techmecook.databinding.ItemCommentBinding
import com.example.techmecook.model.comment.Comment
import com.example.techmecook.recyclerview.click_listeners.CommentClickListener

class CommentViewHolder(private val binding: ItemCommentBinding) :
        RecyclerView.ViewHolder(binding.root) {

    fun bind(
            comment: Comment,
            clickListener: CommentClickListener
    ) {
        binding.text.text = comment.text
        binding.created.text = comment.created
        binding.creatorName.text = comment.creatorName
    }
}