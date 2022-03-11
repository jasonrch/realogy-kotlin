package com.deyvi.realogyassesment.data.remote

import com.deyvi.realogyassesment.BuildConfig
import com.deyvi.realogyassesment.data.remote.dto.CharacterResponse
import retrofit2.http.GET

interface CharactersApi {

    @GET(BuildConfig.QUERY)
    suspend fun getCharacters(): CharacterResponse
}