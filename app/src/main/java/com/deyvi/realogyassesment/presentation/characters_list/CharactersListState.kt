package com.deyvi.realogyassesment.presentation.characters_list

import com.deyvi.realogyassesment.domain.model.CharacterObject

data class CharactersListState(
    val isLoading: Boolean = false,
    val characterObjects: List<CharacterObject> = emptyList(),
    val error: String = ""
)