package com.deyvi.realogyassesment.presentation.character_details.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.deyvi.realogyassesment.BuildConfig
import com.deyvi.realogyassesment.R
import com.deyvi.realogyassesment.domain.model.CharacterObject

@Composable
fun CharacterDetail(
    characterObject: CharacterObject
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        AsyncImage(
            model = BuildConfig.BASE_URL + characterObject.imageUrl,
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            contentScale = ContentScale.Fit,
            placeholder = painterResource(id = R.drawable.ic_launcher_foreground),
            error = painterResource(id = R.drawable.ic_error_img)
        )

        Text(text = characterObject.description.replace('+', ' '), Modifier.padding(16.dp))

    }


}