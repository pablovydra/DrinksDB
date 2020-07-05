package com.vydra.possumusdrinks.drinks.favorites

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.vydra.possumusdrinks.drinks.database.Drink
import com.vydra.possumusdrinks.drinks.database.DrinksRepository

class FavoritesViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = DrinksRepository(application)

    val drinks = repository.getDrinks()

    fun saveDrink(drink: Drink) {
        repository.insert(drink)
    }
}