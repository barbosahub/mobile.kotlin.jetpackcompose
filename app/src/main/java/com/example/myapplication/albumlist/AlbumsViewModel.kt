package com.example.myapplication.albumlist

import androidx.compose.runtime.mutableStateOf
import com.example.myapplication.repository.AlbumsRepository
import com.example.myapplication.state.UiState


class AlbumsViewModel(private val albumsRepository: AlbumsRepository) : BaseViewModel() {
    /*
    PODE PARA DE USAR LIVEDATA

    Não é recomendado usar mais o LiveData porque ele está meio obsoleto...
    Os novos observáveis agora são: StateFlow ou composeState

    Vantagens:

    StateFlow - MutableStateFlow()

    - A versão StateFlow pode fazer mais coisas, por exemplo
    - Mapear o resultado facilmente
    - Filtrar
    - É muito mais fácil de testar (porque eles usam Coroutines, as capacidades de teste para Coroutines são maiores do que para LiveData).

      composeState - mutableStateOf()

    - Especificamente projetado para o Jetpack Compose.
    - Melhor integração com o framework geral do Compose.

    val photosList = MutableLiveData<StateFlow>()
    val album = MutableLiveData<StateFlow>()
    */

    var uiState = mutableStateOf(UiState())
        private set

    init {
        getPhotos()
    }

    private fun getPhotos() = fetchData(uiState) {
        albumsRepository.getPhotosList()
    }

}





//
//    val photosArray = ArrayList<PhotoJson>()
//    lateinit var albumObj: AlbumJson

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
//            .forEach { photosArray += PhotoJson(it) }
//    }
//
//    fun fillAlbum(result: ArrayList<*>) {
//        val jsonArray = JSONArray(result)[0]
//        val jsonObj = JSONObject(jsonArray.toString())
//
//        albumObj = AlbumJson(jsonObj)
//    }
//

