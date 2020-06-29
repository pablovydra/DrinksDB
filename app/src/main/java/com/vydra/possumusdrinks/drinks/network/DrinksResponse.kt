package com.vydra.possumusdrinks.drinks.network

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DrinksResponse(
    @Json(name = "drinks")
    val drinks: List<DrinkList>?
)