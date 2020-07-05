package com.vydra.possumusdrinks.drinks.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface DrinkDao {
    @Insert
    fun insert(drink: Drink)

    @Update
    fun update(vararg drink: Drink)

    @Delete
    fun delete(vararg drink: Drink)

    @Query("SELECT * FROM " + Drink.TABLE_NAME + " ORDER BY title")
    fun getOrderedDrinks(): LiveData<List<Drink>>
}