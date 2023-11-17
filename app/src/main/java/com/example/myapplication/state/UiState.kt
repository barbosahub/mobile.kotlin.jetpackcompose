package com.example.myapplication.state

import com.example.myapplication.data.remote.responses.PhotoJson

data class UiState(
    val isLoading:Boolean = false,
    val error:String? = null,
    val items:List<PhotoJson> = listOf()
){
//    sealed class Error{
//        object NetworkError : Error()
//        object  ListEmpty: Error()
//
//    }
}
