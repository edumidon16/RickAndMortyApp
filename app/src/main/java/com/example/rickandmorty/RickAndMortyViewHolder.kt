package com.example.rickandmorty

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.API.CharacterDataResponse
import com.example.rickandmorty.databinding.ItemCharactersBinding
import com.squareup.picasso.Picasso

class RickAndMortyViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private val binding = ItemCharactersBinding.bind(view)

    fun bind(characterDataResponse: CharacterDataResponse, onItemSelected: (Int) -> Unit){
        binding.tvCharacterName.text = characterDataResponse.name
        binding.tvCharacterStatus.text = characterDataResponse.status
        Picasso.get().load(characterDataResponse.characterImage).into(binding.iwCharacter)

        binding.root.setOnClickListener{onItemSelected(characterDataResponse.characterId)}
    }
}