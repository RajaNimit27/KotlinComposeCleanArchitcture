package com.app.kotlincomposecleanarchitcture.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import common.UiState
import data.model.PostEntity
import data.repository.PostRepositoryImpl
import domain.usecase.GetPostsUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val getPostsUseCase: GetPostsUseCase): ViewModel() {

    private val _uiStatePostList = MutableStateFlow<UiState<List<PostEntity>>>(UiState.Loading)
    val uiStatePostList: StateFlow<UiState<List<PostEntity>>> = _uiStatePostList

     fun fetchAndSavePosts() {
        viewModelScope.launch {
            getPostsUseCase.fetchAndSavePosts().collect { uiState ->
                _uiStatePostList.value = uiState
            }
        }
    }

    val postsFromDb: Flow<List<PostEntity>> = getPostsUseCase.getPostsFromDb()

}