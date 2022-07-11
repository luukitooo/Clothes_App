package com.example.clothesapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.clothesapp.databinding.CategoryItemBinding
import com.example.clothesapp.utils.Categories

class CategoriesAdapter : RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder>() {

    var onItemClickListener: ((Categories) -> Unit)? = null

    inner class CategoriesViewHolder(val binding: CategoryItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CategoriesViewHolder(
        CategoryItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        val category = Categories.values()[position]
        holder.binding.apply {
            categoryTextView.text = category.asOutput
            root.setOnClickListener {onItemClickListener?.invoke(category)}
        }
    }

    override fun getItemCount() = Categories.values().size

}