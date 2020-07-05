package com.vydra.possumusdrinks.drinks.details

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.vydra.possumusdrinks.drinks.database.Drink
import com.vydra.possumusdrinks.drinks.database.DrinksRepository

class DetailsViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = DrinksRepository(application)

    val drink = repository.getDrinks()

    fun saveDrink(drink: Drink) {
        repository.insert(drink)
    }

}