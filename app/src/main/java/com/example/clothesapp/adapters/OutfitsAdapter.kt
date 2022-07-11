package com.example.clothesapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.clothesapp.R
import com.example.clothesapp.databinding.OutfitItemBinding
import com.example.clothesapp.models.Outfit

class OutfitsAdapter(private var outfitsList: List<Outfit>): RecyclerView.Adapter<OutfitsAdapter.OutfitsViewHolder>() {

    inner class OutfitsViewHolder(val binding: OutfitItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = OutfitsViewHolder(
        OutfitItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: OutfitsViewHolder, position: Int) {
        val outfit = outfitsList[position]
        val context = holder.itemView.context
        holder.binding.apply {
            Glide.with(holder.itemView).load(outfit.image).into(outfitImageView)
            titleTextView.text = outfit.title
            priceTextView.text = "${context.resources.getText(R.string.dollar)}${outfit.price}"
        }
    }

    override fun getItemCount() = outfitsList.size

    fun setData(newList: List<Outfit>) {
        val outfitsDiffCallback = OutfitsDiffCallback(outfitsList, newList)
        val diffResult = DiffUtil.calculateDiff(outfitsDiffCallback)
        outfitsList = newList
        diffResult.dispatchUpdatesTo(this)
    }

    inner class OutfitsDiffCallback(
        private val oldList: List<Outfit>,
        private val newList: List<Outfit>
    ) : DiffUtil.Callback() {

        override fun getOldListSize() = oldList.size

        override fun getNewListSize() = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].image == newList[newItemPosition].image
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }

    }
}