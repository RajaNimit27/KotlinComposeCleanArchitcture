package domain.repository

import common.UiState
import data.model.PostEntity
import kotlinx.coroutines.flow.Flow


interface PostRepository {
    suspend fun getPosts(): Flow<UiState<List<PostEntity>>>
}
