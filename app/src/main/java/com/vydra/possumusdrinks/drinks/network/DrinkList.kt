package com.vydra.possumusdrinks.drinks.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DrinkList(
    @Json(name = "idDrink")
    val idDrink: String?,
    val strDrink: String?
)