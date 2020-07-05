package com.vydra.possumusdrinks.drinks.favorites

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vydra.possumusdrinks.drinks.database.Drink
import com.vydra.possumusdrinks.drinks.database.DrinksRepository
import com.vydra.possumusdrinks.drinks.network.DrinkList

class FavoritesViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = DrinksRepository(application)

    val drinks = repository.getDrinks()

    fun saveDrink(drink: Drink) {
        repository.insert(drink)
    }
}