package com.deyvi.realogyassesment.presentation.characters_list.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.deyvi.realogyassesment.data.remote.dto.RelatedTopic

@Composable
fun CharacterItem(
    relatedTopic: RelatedTopic
) {
    Row() {
        Column(modifier = Modifier.padding(start = 8.dp, top = 8.dp, end = 8.dp)) {
            Text(text = relatedTopic.Text)

            Divider(
                thickness = 1.dp,
                modifier = Modifier
                    .background(Color.LightGray)
                    .fillMaxWidth()
            )
        }
    }
}