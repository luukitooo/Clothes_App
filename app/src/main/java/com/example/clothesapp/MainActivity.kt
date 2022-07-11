package com.example.clothesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.clothesapp.adapters.CategoriesAdapter
import com.example.clothesapp.adapters.OutfitsAdapter
import com.example.clothesapp.databinding.ActivityMainBinding
import com.example.clothesapp.models.Outfit
import com.example.clothesapp.utils.Categories
import com.example.clothesapp.utils.StaticOutfits

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val categoriesAdapter by lazy {
        val adapter = CategoriesAdapter()
        adapter
    }

    private val outfitsAdapter by lazy {
        val adapter = OutfitsAdapter(StaticOutfits.outfitsList.shuffled())
        adapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        buildCategoriesRV()

        buildOutfitsRV()

        onClickListeners()

    }

    private fun buildCategoriesRV() = with(binding.categoriesRecyclerView) {
        adapter = categoriesAdapter
        layoutManager = LinearLayoutManager(
            this@MainActivity,
            LinearLayoutManager.HORIZONTAL,
            false
        )
    }

    private fun buildOutfitsRV() = with(binding.outfitsRecyclerView) {
        adapter = outfitsAdapter
        layoutManager = GridLayoutManager(
            this@MainActivity,
            2
        )
    }

    private fun onClickListeners() {
        categoriesAdapter.onItemClickListener = {
            refreshOutfits(category = it)
            binding.outfitsRecyclerView.smoothScrollToPosition(0)
        }
    }

    private fun refreshOutfits(category: Categories) {
        if (category == Categories.ALL) {
            outfitsAdapter.setData(StaticOutfits.outfitsList)
        } else {
            outfitsAdapter.setData(StaticOutfits.outfitsList.filterByCategory(category))
        }
    }

    private fun List<Outfit>.filterByCategory(category: Categories) = this.filter {
        it.category == category
    }

}