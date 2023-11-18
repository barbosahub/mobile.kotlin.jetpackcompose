package com.example.myapplication.util

//sealed class StateFlow<T>(val data:T? = null, val message:String? = null) {
//    class Success<T>(data: T?): StateFlow<T>(data)
//    class Error<T>(message: String, data: T?= null): StateFlow<T>(data,message)
//    class Loading<T>(val isLoading: Boolean = true): StateFlow<T>(null)
//}

sealed interface StateFlow {
    data class Loading(val  loading: Boolean): StateFlow
    data class Success<T>(val  data: T): StateFlow
    data class Error(val  errorMessage: String, val errorCode: String?, val detail: String?, val errorId: String?): StateFlow
}
