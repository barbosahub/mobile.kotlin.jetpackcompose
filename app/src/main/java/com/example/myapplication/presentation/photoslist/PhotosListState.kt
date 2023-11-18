package com.example.myapplication.presentation.photoslist

import com.example.myapplication.domain.models.PhotosItem

data class PhotosListState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val photosList: List<PhotosItem> = emptyList()
)

