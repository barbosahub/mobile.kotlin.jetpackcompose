package com.example.myapplication.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.stateFlow.StateFlow
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Response

open class BaseViewModel: ViewModel() {

    private var job = Job()
        get() {
            if (field.isCancelled) field = Job()
            return field
        }

    fun fetchData(liveData: MutableLiveData<StateFlow>,
                  service: suspend () -> Response<*>) {
        viewModelScope.launch(job) {
            liveData.value = StateFlow.Loading(true)
            try {
                val response = service()
                liveData.value = StateFlow.Loading(false)
                if (response.isSuccessful) {

                        val jsonResponse = response.body()!! as ArrayList<*>
                        if (jsonResponse.isNotEmpty()) {
                            liveData.value = StateFlow.Success(jsonResponse)
                        } else {
                            liveData.value = StateFlow.Error(
                                jsonResponse[0].toString()
                            )
                        }

                } else if (response.code() == 504) {
                    liveData.value = StateFlow.Error(
                        "Erro ao chamar serviço, por favor tente novamente."
                    )
                } else {
                    liveData.value =
                        StateFlow.Error(response.errorBody()!!.string())
                }
            } catch (e: Exception) {
                liveData.value = StateFlow.Loading(false)
                liveData.value = StateFlow.Error(e.message!!)
            }
        }
    }

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