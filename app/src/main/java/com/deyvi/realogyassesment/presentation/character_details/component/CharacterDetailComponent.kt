package com.deyvi.realogyassesment.presentation.character_details.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.deyvi.realogyassesment.BuildConfig
import com.deyvi.realogyassesment.R
import com.deyvi.realogyassesment.domain.model.CharacterObject

@Composable
fun CharacterDetail(
    characterObject: CharacterObject,
    fontSize: Int = 18
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
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

        Text(
            text = characterObject.description.replace('+', ' '),
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            fontSize = fontSize.sp
        )

    }


}