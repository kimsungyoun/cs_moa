package com.mju.csmoa.home.recipe.domain.model

data class Recipe(
    val recipeId: Long,
    val recipeName: String,
    val recipeMainImageUrl: String,
    val recipeContent: String,
    val ingredients: String,
    var viewNum: Int,
    var likeNum: Int,
    var isLike: Boolean
)
