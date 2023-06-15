package com.example.rickandmorty.API

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("/api/character/")
    suspend fun getRickAndMorty(@Query("name") characterName:String):Response<RickAndMortyDataResponse>

    @GET("api/character/{id}")
    suspend fun getCharacterInfo(@Path("id") characterId : Int):Response<CharacterInfo>

}