package com.pokelist.pokelist.views.network

import com.pokelist.pokelist.views.network.models.PokemonResponse
import retrofit2.Call
import retrofit2.http.GET

interface PokemonService {
    @GET("v2/pokemon?limit=100")

    fun getPokemonList() : Call<PokemonResponse>
}
