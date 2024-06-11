package com.app.kotlincomposecleanarchitcture.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import common.UiState
import data.model.PostEntity
import data.repository.PostRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val repository: PostRepositoryImpl): ViewModel() {

    val _uiStatePostList = MutableStateFlow<UiState<List<PostEntity>>>(UiState.Loading)
    val uiStatePostList: StateFlow<UiState<List<PostEntity>>> = _uiStatePostList

    fun getPostList() = viewModelScope.launch {
        repository.getPosts().collect {
            when (it) {
                is UiState.Success -> { _uiStatePostList.value = UiState.Success(it.data) }
                is UiState.Loading -> { _uiStatePostList.value = UiState.Loading }
                is UiState.Error -> { _uiStatePostList.value = UiState.Error(it.message) }
            }
        }
    }

}