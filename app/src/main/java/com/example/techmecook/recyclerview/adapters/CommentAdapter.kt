package com.example.techmecook.recyclerview.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.techmecook.databinding.ItemCommentBinding
import com.example.techmecook.model.comment.Comment
import com.example.techmecook.recyclerview.click_listeners.CommentClickListener
import com.example.techmecook.recyclerview.viewholders.CommentViewHolder

class CommentAdapter(
        private val clickListener: CommentClickListener
) : ListAdapter<Comment, CommentViewHolder>(ListItemCallback()) {

    private class ListItemCallback : DiffUtil.ItemCallback<Comment>() {
        override fun areItemsTheSame(
                oldItem: Comment,
                newItem: Comment
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
                oldItem: Comment,
                newItem: Comment
        ): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCommentBinding.inflate(inflater, parent, false)

        return CommentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }
}