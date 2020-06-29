package com.vydra.possumusdrinks.drinks.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://www.thecocktaildb.com/api/json/v1/1/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

interface DrinksApiService {

//    @GET("/app/views/appmobile.php")
//    fun getProperties(
//        @Query("_action") _action: String = "getEventos"
//    ):
//            Deferred<DrinksResponse>
//
//    @GET("realestate")
//    fun getProperties():
//            Deferred<List<MarsProperty>>

//    @GET("filter.php?a=Alcoholic")
//    fun getAlcoholic(): Call<Any>
//
//    @GET("filter.php?a=Non_Alcoholic")
//    fun getNonAlcoholic(): Call<Any>

    @GET("/search.php")
    fun getProperties(
        drinkName: String,
        @Query("s") s: String = drinkName
    ):
            Deferred<DrinksResponse>

}

object DrinksApi {
    val retrofitService : DrinksApiService by lazy { retrofit.create(DrinksApiService::class.java) }
}