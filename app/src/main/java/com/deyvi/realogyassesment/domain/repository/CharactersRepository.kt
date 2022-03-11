package com.deyvi.realogyassesment.domain.repository

import com.deyvi.realogyassesment.data.remote.dto.RelatedTopic

interface CharactersRepository {

    //TODO: look into renaming related topic
    suspend fun getCharacters(): List<RelatedTopic>
}