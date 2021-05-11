package com.example.techmecook.recyclerview.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.techmecook.databinding.ItemRecipeBinding
import com.example.techmecook.model.recipe.RecipeLight
import com.example.techmecook.recyclerview.click_listeners.RecipeClickListener
import com.example.techmecook.recyclerview.viewholders.RecipeListViewHolder

class RecipeListAdapter(
    private val clickListener: RecipeClickListener
) : ListAdapter<RecipeLight, RecipeListViewHolder>(ListItemCallback()) {

    private class ListItemCallback : DiffUtil.ItemCallback<RecipeLight>() {
        override fun areItemsTheSame(
            oldItem: RecipeLight,
            newItem: RecipeLight
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: RecipeLight,
            newItem: RecipeLight
        ): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRecipeBinding.inflate(inflater, parent, false)

        return RecipeListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipeListViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }
}