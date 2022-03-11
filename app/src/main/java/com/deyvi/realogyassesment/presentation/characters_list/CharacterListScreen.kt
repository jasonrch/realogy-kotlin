package com.deyvi.realogyassesment.presentation.characters_list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.deyvi.realogyassesment.common.Constants.CHARACTER_KEY
import com.deyvi.realogyassesment.data.remote.dto.RelatedTopic
import com.deyvi.realogyassesment.domain.model.CharacterObject
import com.deyvi.realogyassesment.presentation.Screens
import com.deyvi.realogyassesment.presentation.characters_list.component.CharacterItem
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.squareup.moshi.Moshi
import java.net.URLEncoder

@Composable
fun CharactersListScreen(
    navController: NavController,
    viewModel: CharactersListViewModel = hiltViewModel()
) {

    val state = viewModel.state.value
    val isRefreshing by viewModel.isRefreshing.collectAsState()

    Box() {
        SwipeRefresh(
            state = rememberSwipeRefreshState(isRefreshing),
            onRefresh = { viewModel.refreshCharacters() },
            modifier = Modifier.fillMaxSize()
        ) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.characterObjects) { character ->
                    CharacterItem(
                        characterObject = character,
                        onItemClick = { onCharacterClick(navController, character)}
                    )
                }
            }

            if (state.error.isNotBlank()) {
                ErrorComponent(state.error)
            }

            if (state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Center))
            }
        }
    }
}

private fun onCharacterClick(navController: NavController, item: CharacterObject) {
    val moshi = Moshi.Builder().build()
    val jsonAdapter = moshi.adapter(CharacterObject::class.java).lenient()
    val characterJson = jsonAdapter.toJson(item)
    val characterEncoded = URLEncoder.encode(characterJson, "utf-8")

    navController.navigate(
        Screens.CharacterDetailScreen.route.replace("{$CHARACTER_KEY}", characterEncoded)
    )
}

@Composable
fun ErrorComponent(error: String) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = error,
            color = MaterialTheme.colors.error,
            textAlign = TextAlign.Center
        )
    }
}