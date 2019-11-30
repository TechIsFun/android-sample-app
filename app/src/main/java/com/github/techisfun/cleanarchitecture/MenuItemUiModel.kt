package com.github.techisfun.cleanarchitecture

/**
 * @author Andrea Maglie
 */
data class MenuItemUiModel(
    val name: String,
    val url: String? = null,
    val destination: Int? = null
)
