package com.example.techmecook.recyclerview.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.techmecook.databinding.ItemIngredientInrecipeBinding
import com.example.techmecook.model.ingredient.IngredientGeneralInfo
import com.example.techmecook.recyclerview.click_listeners.IngredientClickListener
import com.example.techmecook.recyclerview.viewholders.IngredientViewHolder

class IngredientAdapter(private val clickListener: IngredientClickListener) :
    ListAdapter<IngredientGeneralInfo, IngredientViewHolder>(ListItemCallback()) {

    private class ListItemCallback : DiffUtil.ItemCallback<IngredientGeneralInfo>() {
        override fun areItemsTheSame(oldItem: IngredientGeneralInfo, newItem: IngredientGeneralInfo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: IngredientGeneralInfo, newItem: IngredientGeneralInfo): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemIngredientInrecipeBinding.inflate(inflater, parent, false)

        return IngredientViewHolder(binding)
    }

    override fun onBindViewHolder(holder: IngredientViewHolder, position: Int) {
         getItem(position)?.let {
             holder.bind(it, clickListener)
         }
    }
}