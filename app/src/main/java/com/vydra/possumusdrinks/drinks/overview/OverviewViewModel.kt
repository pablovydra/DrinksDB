package com.vydra.possumusdrinks.drinks.overview

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vydra.possumusdrinks.drinks.network.DrinkList
import com.vydra.possumusdrinks.drinks.network.DrinksApi
import com.vydra.possumusdrinks.drinks.network.DrinksResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class OverviewViewModel : ViewModel() {

    // LIVE DATA
    private val _response = MutableLiveData<String>()
    val response: LiveData<String>
        get() = _response

    private val _properties = MutableLiveData<DrinksResponse>()
    val properties: LiveData<DrinksResponse>
        get() = _properties

    private val _drinks = MutableLiveData<List<DrinkList>>()
    val agenda: LiveData<List<DrinkList>>
        get() = _drinks

    private val _internet = MutableLiveData<Boolean>()
    val internet: LiveData<Boolean>
        get() = _internet

    // JOB
    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        getDrinksProperties()
    }

    // GET
    private fun getDrinksProperties() {
        coroutineScope.launch {
            // Get the Deferred object for our Retrofit request
            var getPropertiesDeferred = DrinksApi.retrofitService.getProperties("margarita")
            try {
                // Await the completion of our Retrofit request
                var listResult = getPropertiesDeferred.await()

                _drinks.value = listResult.drinks

                Log.e("OverviewViewModel", "cantidad: ${agenda.value?.size}")
                Log.e("OverviewViewModel", "Response: ${listResult}")

                _internet.value = true

            } catch (e: Exception) {
                _response.value = "Failure: ${e.message}"
                Log.e("OverviewViewModel", "${e.message}")

                _internet.value = false

            }
        }
    }

    // ON CLEARED
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}