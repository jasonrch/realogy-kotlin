package com.deyvi.realogyassesment.presentation.character_details

import androidx.compose.runtime.Composable
import com.deyvi.realogyassesment.domain.model.CharacterObject
import com.deyvi.realogyassesment.presentation.character_details.component.CharacterDetail

@Composable
fun CharacterDetailScreen(
    characterObject: CharacterObject?
) {
    if(characterObject != null) {
        CharacterDetail(characterObject = characterObject)
    }
}