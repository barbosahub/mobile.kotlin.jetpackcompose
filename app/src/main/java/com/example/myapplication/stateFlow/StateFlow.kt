package com.example.myapplication.stateFlow

import androidx.compose.runtime.Composable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
sealed interface StateFlow {
    data class Loading(val  loading: Boolean): StateFlow
    data class Success<T>(val  data: T): StateFlow
    data class Error(val  errorMessage: String): StateFlow
}
