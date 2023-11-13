package com.example.myapplication.ui

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.myapplication.ui.theme.Purple40

@Composable
fun MyTopAppBar(modifier: Modifier) {
    Scaffold(
        topBar = { MyTopBar() },
        modifier = modifier
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
        }
    }

}

@Composable
fun MyTopBar() {
    TopAppBar(
        title = { Text(text = "Albums API") },
        backgroundColor = Purple40,
        contentColor = Color.White,
        elevation = 4.dp
    )
}