package com.example.myapplication.albumlist

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
import com.example.myapplication.state.UiState

@Composable
fun AlbumListScreen(
    uiState: UiState
){
  Box(
      modifier = Modifier.fillMaxSize(),
      contentAlignment = Alignment.Center
      ){
          LazyColumn(modifier = Modifier.fillMaxSize()){
              items(uiState.items.size){
                  Text(
                      text = uiState.items[it].title,
                      color = Color.Green
                  )
                  Spacer(modifier = Modifier.size(10.dp))
              }
      }

      if (uiState.isLoading){
          CircularProgressIndicator()
      } 

  }

}