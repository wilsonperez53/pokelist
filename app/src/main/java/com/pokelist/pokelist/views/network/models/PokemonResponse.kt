package com.pokelist.pokelist.views.network.models

data class PokemonResponse(
    val count: Int,
    val results:List<PokemonDetailResponse>
)