package com.example.clothesapp.models

import com.example.clothesapp.utils.Categories

data class Outfit(
    val image: String, // With URL
    val title: String,
    val price: Int,
    val category: Categories
)