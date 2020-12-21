package com.pokelist.pokelist.views.fragments

import android.graphics.drawable.ClipDrawable.VERTICAL
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import com.pokelist.pokelist.R
import com.pokelist.pokelist.views.adapters.PokemonListAdapter
import com.pokelist.pokelist.views.viewmodels.PokemonListViewModel
import kotlinx.android.synthetic.main.fragment_pokemon_list.*


class PokemonListFragment : Fragment() {

    private val viewModel: PokemonListViewModel by viewModels()
    private val adapter = PokemonListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewModel.getPokemonList()
        return inflater.inflate(R.layout.fragment_pokemon_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pokemonRecyclerView.adapter = adapter
        //pokemonRecyclerView.addItemDecoration(DividerItemDecoration(requireContext(), ))

        viewModel.getPokemonListResponse().observe(viewLifecycleOwner){ pokemonList ->
            adapter.characterList = pokemonList
            pokemonRecyclerView.visibility = View.VISIBLE
        }

        viewModel.getIsLoadingRequest().observe(viewLifecycleOwner){ isLoading ->
            progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }
       // viewModel.getPokemonList()
    }
}