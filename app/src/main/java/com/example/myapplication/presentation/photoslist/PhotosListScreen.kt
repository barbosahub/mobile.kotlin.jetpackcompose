package com.example.myapplication.presentation.photoslist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun AlbumListScreen(
    photosListState:PhotosListState
){
  Box(
      modifier = Modifier.fillMaxSize(),
      contentAlignment = Alignment.Center
      ){
          LazyColumn(modifier = Modifier.fillMaxSize()){
              items(photosListState.photosList.size){
                  Text(
                      text = photosListState.photosList[it].title,
                      color = Color.Green
                  )
                  Spacer(modifier = Modifier.size(10.dp))
              }
      }

      if (photosListState.isLoading){
          CircularProgressIndicator()
      } 

  }

}