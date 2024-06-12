package com.app.kotlincomposecleanarchitcture.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import common.UiState
import data.model.PostEntity
import domain.usecase.GetPostsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val useCase: GetPostsUseCase): ViewModel() {

    val _uiStatePostList = MutableStateFlow<UiState<List<PostEntity>>>(UiState.Loading)
    val uiStatePostList: StateFlow<UiState<List<PostEntity>>> = _uiStatePostList

    fun getPostList() = viewModelScope.launch {
        useCase().collect {
            _uiStatePostList.value = it
        }
    }

}