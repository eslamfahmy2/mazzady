package com.testapp.domain.state

import com.testapp.data.networking.dto.Category
import com.testapp.data.networking.dto.Children
import com.testapp.data.networking.dto.SubCategoryDto


enum class SearchWidgetState {
    OPENED,
    CLOSED
}

enum class SearchSuggestionState {
    OPENED,
    CLOSED
}

sealed class CategoriesScreenState {
    object Loading : CategoriesScreenState()
    data class Success(
        val categories: List<Category>,
        val subCategories: List<Children>?,
        val options: List<SubCategoryDto>?,
    ) : CategoriesScreenState()

    data class Error(val msg: String?) : CategoriesScreenState()
}

