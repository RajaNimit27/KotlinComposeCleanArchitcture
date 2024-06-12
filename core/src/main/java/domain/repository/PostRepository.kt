package domain.repository

import common.UiState
import data.model.PostEntity
import kotlinx.coroutines.flow.Flow

interface PostRepository {
    fun fetchAndSavePosts(): Flow<UiState<List<PostEntity>>>
    fun getPostsFromDb(): Flow<List<PostEntity>>
}

