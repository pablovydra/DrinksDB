package com.vydra.possumusdrinks.drinks.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = Drink.TABLE_NAME)
data class Drink(
    @ColumnInfo(name = "title") @NotNull val title: String,
    @ColumnInfo(name = "img") @NotNull val img: String
) {
    companion object {
        const val TABLE_NAME = "drink"
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "drink_id")
    var drinkId: Int = 0
}
