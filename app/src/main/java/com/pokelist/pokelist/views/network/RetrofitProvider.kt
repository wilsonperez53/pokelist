package com.pokelist.pokelist.views.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitProvider {
    val retrofit: Retrofit
    private val baseUrl = "https://pokeapi.co/api/"
    init {
        retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    fun getPokemonService() : PokemonService{
        return retrofit.create(PokemonService::class.java)
    }
}