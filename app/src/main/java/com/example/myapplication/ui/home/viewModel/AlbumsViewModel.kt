package com.example.myapplication.ui.home.viewModel

import androidx.lifecycle.MutableLiveData
import com.example.myapplication.network.repository.AlbumsRepository
import com.example.myapplication.stateFlow.StateFlow
import com.example.myapplication.ui.base.BaseViewModel
import com.example.myapplication.ui.home.view.model.AlbumJson
import com.example.myapplication.ui.home.view.model.PhotoJson
import org.json.JSONArray
import org.json.JSONObject


class AlbumsViewModel(private val albumsRepository: AlbumsRepository) : BaseViewModel() {

    val photosList = MutableLiveData<StateFlow>()
    val album = MutableLiveData<StateFlow>()

    val photosArray = ArrayList<PhotoJson>()
    lateinit var albumObj: AlbumJson

    fun getAlbumsById(id: Int) = fetchData(album) {
        albumsRepository.getAlbumsById(id)
    }

    fun getPhotos() = fetchData(photosList) {
        albumsRepository.getPhotos()
    }

    fun fillPhotos(result: ArrayList<*>) {
        val jsonArray = JSONArray(result)

        (0 until jsonArray.length())
            .map { jsonArray.getJSONObject(it) }
            .forEach { photosArray += PhotoJson(it) }
    }

    fun fillAlbum(result: ArrayList<*>) {
        val jsonArray = JSONArray(result)[0]
        val jsonObj = JSONObject(jsonArray.toString())

        albumObj = AlbumJson(jsonObj)
    }


}