package com.vydra.possumusdrinks.drinks.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Drink::class], version = 1)
abstract class DrinksDatabase : RoomDatabase() {
    abstract fun drinkDao(): DrinkDao

    companion object {
        private const val DATABASE_NAME = "drink_database"

        @Volatile
        private var INSTANCE: DrinksDatabase? = null

        fun getInstance(context: Context): DrinksDatabase? {
            INSTANCE ?: synchronized(this) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    DrinksDatabase::class.java,
                    DATABASE_NAME
                ).build()
            }
            return INSTANCE
        }
    }

}