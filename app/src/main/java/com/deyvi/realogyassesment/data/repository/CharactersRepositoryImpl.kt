package com.deyvi.realogyassesment.data.repository

import com.deyvi.realogyassesment.data.remote.CharactersApi
import com.deyvi.realogyassesment.data.remote.dto.RelatedTopic
import com.deyvi.realogyassesment.domain.repository.CharactersRepository
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val api: CharactersApi
) : CharactersRepository {

    override suspend fun getCharacters(): List<RelatedTopic> {
        return api.getCharacters().RelatedTopics
    }
}