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

    private val _drinkByName = MutableLiveData<List<DrinkList>>()
    val drinkByName: LiveData<List<DrinkList>>
        get() = _drinkByName

    private val _drinkByIngredient = MutableLiveData<List<DrinkList>>()
    val drinkByIngredient: LiveData<List<DrinkList>>
        get() = _drinkByIngredient

    private val _drinksAll = MutableLiveData<List<DrinkList>>()
    val drinksAll: LiveData<List<DrinkList>>
        get() = _drinksAll

    private val _internet = MutableLiveData<Boolean>()
    val internet: LiveData<Boolean>
        get() = _internet

    // JOB
    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        getDrinksByName("all", "Alcoholic")
    }

    // GET BY FILTER SEARCH: NAME
    fun getDrinksByName(drink: String, alcoholic: String) {
        coroutineScope.launch {
            var getPropertiesDeferred = DrinksApi.retrofitService.getProperties(drink, alcoholic)
            try {
                var listResult = getPropertiesDeferred.await()

                _drinkByName.value = listResult.drinks

                Log.e("OverviewViewModel", "cantidad: ${drinkByName.value?.size}")
                Log.e("OverviewViewModel", "Response: ${listResult}")

                _internet.value = true

            } catch (e: Exception) {
                _response.value = "Failure: ${e.message}"
                Log.e("OverviewViewModel", "${e.message}")

                _internet.value = false

            }
        }
    }

    // GET BY FILTER SEARCH: INGREDIENT
    fun getDrinksByIngredient(Ingredient: String, alcoholic: String) {
        coroutineScope.launch {
            var getPropertiesDeferred =
                DrinksApi.retrofitService.getProperties2(Ingredient, alcoholic)
            try {
                var listResult = getPropertiesDeferred.await()

                _drinkByIngredient.value = listResult.drinks

                Log.e("OverviewViewModel", "cantidad: ${drinkByIngredient.value?.size}")
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