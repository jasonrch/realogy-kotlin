package com.deyvi.realogyassesment.presentation.characters_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deyvi.realogyassesment.common.Resource
import com.deyvi.realogyassesment.data.use_case.GetCharacters
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class CharactersListViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharacters
) : ViewModel() {

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean>
        get() = _isRefreshing.asStateFlow()

    private val _state = mutableStateOf(CharactersListState())
    val state: State<CharactersListState> = _state

    init {
        getCharacters()
    }

    fun refreshCharacters() {
        getCharacters()
    }

    private fun getCharacters() {
        getCharactersUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    val characters = result.data

                    _state.value = CharactersListState(characters = characters ?: emptyList())
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