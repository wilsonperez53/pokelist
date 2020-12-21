package com.pokelist.pokelist.views.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pokelist.pokelist.R
import com.pokelist.pokelist.views.network.models.PokemonDetailResponse
import kotlinx.android.synthetic.main.pokemon_character_cell.view.*

class PokemonListAdapter: RecyclerView.Adapter<PokemonListAdapter.PokemonCharacterViewHolder>() {

    var characterList: List<PokemonDetailResponse> = emptyList()
    set(value){
        field = value
        notifyDataSetChanged()
    }
    inner class PokemonCharacterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(poke: PokemonDetailResponse){
            itemView.pokemonNameTextView.text = poke.name
            itemView.pokemonDescriptionTextView.text = poke.url
            var str = poke.url

            var formattedUrl = str.replace("https://pokeapi.co/api/v2/pokemon","https://pokeres.bastionbot.org/images/pokemon")//"https://pokeres.bastionbot.org/images/pokemon/1.png" // "${poke.url}.${poke.url}".replace("https://pokeapi.co/api/v2/","https://pokeres.bastionbot.org/images/")
            formattedUrl += ".png"
            formattedUrl = formattedUrl.replace("/.",".")//"https://pokeres.bastionbot.org/images/pokemon/1.png" // "${poke.url}.${poke.url}".replace("https://pokeapi.co/api/v2/","https://pokeres.bastionbot.org/images/")

            Glide.with(itemView.context)
                .load(formattedUrl)
                .circleCrop()
                .into(itemView.pokemonImageView)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonCharacterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.pokemon_character_cell, parent, false)
        return PokemonCharacterViewHolder(view)
    }

    override fun onBindViewHolder(holder: PokemonCharacterViewHolder, position: Int) {
        holder.bind(characterList[position])
    }

    override fun getItemCount() = characterList.size
}