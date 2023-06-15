package com.example.rickandmorty

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.API.CharacterDataResponse

class RickAndMortyAdapter(
    var characterList: List<CharacterDataResponse> = emptyList(),
    private val onItemSelected: (Int) -> Unit
) : RecyclerView.Adapter<RickAndMortyViewHolder>() {

    override fun onBindViewHolder(
        holder: RickAndMortyViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        super.onBindViewHolder(holder, position, payloads)

    }

    fun updateList(characterList: List<CharacterDataResponse>) {
        this.characterList = characterList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RickAndMortyViewHolder {
        val layaoutInflater = LayoutInflater.from(parent.context)
        return RickAndMortyViewHolder(
            layaoutInflater.inflate(
                R.layout.item_characters,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return characterList.size
    }

    override fun onBindViewHolder(holder: RickAndMortyViewHolder, position: Int) {
        holder.bind(characterList[position], onItemSelected)
    }

}