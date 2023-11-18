package com.example.myapplication.presentation.photoslist

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.myapplication.domain.models.PhotosItem
import com.example.myapplication.presentation.BaseViewModel
import com.example.myapplication.repository.PhotosRepository
import com.example.myapplication.util.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import org.json.JSONArray


class PhotosViewModel(private val photosRepository: PhotosRepository) : BaseViewModel() {

    /**
     * This property already is a data class, so now we just need to take it result as JSONOBJECT
     * and make it be our model.
     */
    var resultMutableStateFlow = MutableStateFlow<StateFlow>(StateFlow.Loading(false))

    // These properties will represent the model that our ui will have access.
    var photosListState by mutableStateOf(PhotosListState())
    val listPhotos = ArrayList<PhotosItem>()

    init {
        getPhotosListAsJson()
        convertDataFromJsonToState()
    }

    private fun getPhotosListAsJson() = fetchData(resultMutableStateFlow) {
        photosRepository.getPhotosList()
    }

    private fun convertDataFromJsonToState() {
        when (val result = resultMutableStateFlow.value) {
            is StateFlow.Loading -> {
                photosListState = photosListState.copy(
                    isLoading = true,
                    error = null
                )
            }
            is StateFlow.Error -> {
                photosListState = photosListState.copy(
                    isLoading = false,
                    error = result.errorMessage
                )
            }
            is StateFlow.Success<*> -> {
                val jsonArray = result.data as JSONArray
                (0 until jsonArray.length())
                    .map { jsonArray.getJSONObject(it) }
                    .forEach {
                        listPhotos += PhotosItem(
                            albumId = it.getInt("albumId"),
                            id = it.getInt("id"),
                            title = it.getString("title"),
                            thumbnailUrl = it.getString("thumbnailUrl"),
                            url = it.getString("url")

                        )
                    }
                photosListState = photosListState.copy(
                    isLoading = false,
                    photosList = listPhotos
                )
            }

        }

    }
}



//    val photosArray = ArrayList<PhotoJsonNetworkEntity>()
//    lateinit var albumObj: AlbumJsonNetworkEntity

//    fun getAlbumsById(id: Int) = fetchData(album) {
//        albumsRepository.getAlbumsById(id)
//    }
//
//    fun getPhotos() = fetchData(photosList) {
//        albumsRepository.getPhotos()
//    }
//
//    fun fillPhotos(result: ArrayList<*>) {
//        val jsonArray = JSONArray(result)
//
//        (0 until jsonArray.length())
//            .map { jsonArray.getJSONObject(it) }
//            .forEach { photosArray += PhotoJsonNetworkEntity(it) }
//    }
//
//    fun fillAlbum(result: ArrayList<*>) {
//        val jsonArray = JSONArray(result)[0]
//        val jsonObj = JSONObject(jsonArray.toString())
//
//        albumObj = AlbumJsonNetworkEntity(jsonObj)
//    }
//

