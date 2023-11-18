package com.example.myapplication.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.util.StateFlow
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.Response

open class BaseViewModel : ViewModel() {

    private var job = Job()
        get() {
            if (field.isCancelled) field = Job()
            return field
        }

    fun fetchData(
        mutableStateFlow: MutableStateFlow<StateFlow>,
        service: suspend () -> Response<*>
    ) {
        viewModelScope.launch(job) {
            mutableStateFlow.value = StateFlow.Loading(true)
            try {
                val response = service()
                mutableStateFlow.value = StateFlow.Loading(false)
                if (response.isSuccessful){
                    val jsonResponse = JSONObject(response.body()!! as Map<*, *>)
                    if (jsonResponse.optBoolean("success", false) ||
                        jsonResponse.optBoolean("SUCCESS", false) ||
                        jsonResponse.optInt("count", 0) > 0 //
                    // LEO -> O padrão do projeto é voltado para minha empresa, nesse caso não são todas apis que irão funcionar com esses parametros,
                    // no caso da poke API não existe o parametro de sucesso, nesse caso eu adicionei a validacao
                    ) {
                        mutableStateFlow.value = StateFlow.Success(jsonResponse)
                    }else {
                        mutableStateFlow.value = StateFlow.Error(
                            jsonResponse.getIgnoreCase("errormessage"),
                            jsonResponse.getIgnoreCase("errorcode"),
                            jsonResponse.getIgnoreCase("detail"),
                            if (jsonResponse.has("errorID")) jsonResponse.getString("errorID") else null
                        )
                    }

                } else if (response.code() == 504) {
                    mutableStateFlow.value = StateFlow.Error(
                        "Erro ao chamar serviço, por favor tente novamente.", null, null, null)
                } else {
                    mutableStateFlow.value = StateFlow.Error(response.errorBody()!!.string(), null, null, null)
                }
            } catch (e: Exception) {
                Log.e("VMViewModel", Log.getStackTraceString(e))
                mutableStateFlow.value = StateFlow.Loading(false)
                mutableStateFlow.value = StateFlow.Error(e.message!!, null, null, null)
            }
        }

    }


    //    fun fetchData(liveData: MutableLiveData<StateFlow>,
//                  service: suspend () -> Response<*>) {
//        viewModelScope.launch(job) {
//            liveData.value = StateFlow.Loading(true)
//            try {
//                val response = service()
//                liveData.value = StateFlow.Loading(false)
//                if (response.isSuccessful) {
//
//                    val jsonResponse = response.body()!! as ArrayList<*>
//                    if (jsonResponse.isNotEmpty()) {
//                        liveData.value = StateFlow.Success(jsonResponse)
//                    } else {
//                        liveData.value = StateFlow.Error(
//                            jsonResponse[0].toString()
//                        )
//                    }
//
//                } else if (response.code() == 504) {
//                    liveData.value = StateFlow.Error(
//                        "Erro ao chamar serviço, por favor tente novamente."
//                    )
//                } else {
//                    liveData.value =
//                        StateFlow.Error(response.errorBody()!!.string())
//                }
//            } catch (e: Exception) {
//                liveData.value = StateFlow.Loading(false)
//                liveData.value = StateFlow.Error(e.message!!)
//            }
//        }
//    }
    private fun JSONObject.getIgnoreCase(key: String): String {
        keys().forEach {
            if (it.equals(key, true)) {
                return getString(it)
            }
        }
        return ""
    }

    companion object {
        //const val TESTING_CODE = 299
    }
}