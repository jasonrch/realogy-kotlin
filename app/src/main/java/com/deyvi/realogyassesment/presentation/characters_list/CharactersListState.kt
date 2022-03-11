package com.deyvi.realogyassesment.presentation.characters_list

import com.deyvi.realogyassesment.data.remote.dto.RelatedTopic

data class CharactersListState(
    val isLoading: Boolean = false,
    val characters: List<RelatedTopic> = emptyList(),
    val error: String = ""
)