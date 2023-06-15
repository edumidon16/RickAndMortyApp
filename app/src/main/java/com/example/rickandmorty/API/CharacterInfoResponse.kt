package com.example.rickandmorty.API

import com.google.gson.annotations.SerializedName

data class CharacterInfo(
    @SerializedName("id") val id:Int,
    @SerializedName("name") val name: String,
    @SerializedName("status") val characterStatus: String,
    @SerializedName("species") val characterSpecies: String,
    @SerializedName("gender") val characterGender: String,
    @SerializedName("origin") val characterOriginName: CharacterInfoOrigin,
    @SerializedName("location") val characterLocationName: CharacterInfoLocation,
    @SerializedName("image") val characterImage: String
)

data class CharacterInfoOrigin(
    @SerializedName("name") val name: String
)

data class CharacterInfoLocation(
    @SerializedName("name") val name: String
)