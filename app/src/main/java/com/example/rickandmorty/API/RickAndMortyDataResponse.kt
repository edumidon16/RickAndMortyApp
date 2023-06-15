package com.example.rickandmorty.API

import com.google.gson.annotations.SerializedName

data class RickAndMortyDataResponse(@SerializedName("response") val response:String, @SerializedName("results") val charactersData: List<CharacterDataResponse>) {
}

data class CharacterDataResponse(
    @SerializedName("id") val characterId: Int,
    @SerializedName("name") val name: String,
    @SerializedName("status") val status: String,
    @SerializedName("species") val species:String,
    @SerializedName("image") val characterImage:String,
    @SerializedName("gender") val characterGender:String
)

