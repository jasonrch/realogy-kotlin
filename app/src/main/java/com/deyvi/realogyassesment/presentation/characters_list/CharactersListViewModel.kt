package com.deyvi.realogyassesment.presentation.characters_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deyvi.realogyassesment.common.Resource
import com.deyvi.realogyassesment.data.use_case.GetCharacters
import com.deyvi.realogyassesment.domain.model.CharacterObject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import java.util.*
import javax.inject.Inject

@HiltViewModel
class CharactersListViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharacters
) : ViewModel() {

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean>
        get() = _isRefreshing.asStateFlow()

    private val _state = mutableStateOf(CharactersListState())
    val state: State<CharactersListState> = _state

    private var charactersList: List<CharacterObject> = emptyList()

    init {
        getCharacters()
    }

    fun refreshCharacters() {
        getCharacters()
    }

    fun onSearchQueryUpdate(query: String) {
        _state.value = CharactersListState(characterObjects = charactersList.filter {
            it.name.lowercase().contains(
                query.lowercase(Locale.getDefault())
            )
        })
    }

    fun clearSearchQuery() {
        _state.value = CharactersListState(characterObjects = charactersList)
    }

    private fun getCharacters() {
        getCharactersUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    charactersList = result.data ?: emptyList()

                    _state.value = CharactersListState(characterObjects = charactersList)
                    _isRefreshing.emit(false)
                }
                is Resource.Error -> {
                    _state.value = CharactersListState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                    _isRefreshing.emit(false)
                }
                is Resource.Loading -> {
                    _isRefreshing.emit(true)
                    _state.value = CharactersListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}