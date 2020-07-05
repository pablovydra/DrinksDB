package com.vydra.possumusdrinks.drinks.database

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class DrinksRepository(application: Application) {
    private val drinkDao: DrinkDao? = DrinksDatabase.getInstance(application)?.drinkDao()

    fun insert(drink: Drink) {
        if (drinkDao != null) InsertAsyncTask(drinkDao).execute(drink)
    }

    fun getDrinks(): LiveData<List<Drink>> {
        return drinkDao?.getOrderedDrinks() ?: MutableLiveData<List<Drink>>()
    }

    private class InsertAsyncTask(private val drinkDao: DrinkDao) :
        AsyncTask<Drink, Void, Void>() {
        override fun doInBackground(vararg drinks: Drink?): Void? {
            for (drink in drinks) {
                if (drink != null) drinkDao.insert(drink)
            }
            return null
        }
    }
}