package com.deyvi.realogyassesment.presentation.characters_list.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.deyvi.realogyassesment.data.remote.dto.RelatedTopic
import com.deyvi.realogyassesment.domain.model.CharacterObject

@Composable
fun CharacterItem(
    characterObject: CharacterObject,
    onItemClick: () -> Unit
) {
    Row(
        modifier = Modifier.clickable {
            onItemClick()
        }
    ) {
        Column(modifier = Modifier.padding(start = 8.dp, top = 8.dp, end = 8.dp)) {
            Text(text = characterObject.name)

            Divider(
                thickness = 1.dp,
                modifier = Modifier
                    .background(Color.LightGray)
                    .fillMaxWidth()
            )
        }
    }
}