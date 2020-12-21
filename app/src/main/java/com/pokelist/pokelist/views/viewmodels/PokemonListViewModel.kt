package com.pokelist.pokelist.views.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pokelist.pokelist.views.network.RetrofitProvider
import com.pokelist.pokelist.views.network.models.PokemonDetailResponse
import com.pokelist.pokelist.views.network.models.PokemonResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokemonListViewModel : ViewModel() {
    var retrofitProvider = RetrofitProvider()

    private val pokemonListResponse : MutableLiveData<List<PokemonDetailResponse>> = MutableLiveData()
    private val isLoadingRequest: MutableLiveData<Boolean> = MutableLiveData()

    fun getPokemonListResponse() : LiveData<List<PokemonDetailResponse>>{
        return pokemonListResponse
    }

    fun getIsLoadingRequest() : LiveData<Boolean>{
        return isLoadingRequest
    }
    fun getPokemonList() {
        isLoadingRequest.postValue(true)
       retrofitProvider.getPokemonService().getPokemonList().enqueue(object :
               Callback<PokemonResponse>{
           override fun onResponse(call: Call<PokemonResponse>, response: Response<PokemonResponse>) {
               isLoadingRequest.postValue(false)
               if(response.isSuccessful){

                   response.body()?.let { pokeresponse ->
                       pokemonListResponse.postValue(pokeresponse.results)
                       Log.d("Lista de Pokemones ", pokeresponse.toString())
                   }
               }
               else {
                    //TODO agregar else
               }
           }

           override fun onFailure(call: Call<PokemonResponse>, t: Throwable) {
               //TODO("Implementar errores")
           }

       })
    }
}