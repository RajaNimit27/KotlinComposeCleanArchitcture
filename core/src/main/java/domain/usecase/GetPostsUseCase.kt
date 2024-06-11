package domain.usecase


import common.UiState
import data.model.PostEntity
import data.repository.PostRepositoryImpl
import domain.repository.PostRepository
import kotlinx.coroutines.flow.Flow

class GetPostsUseCase(private val postRepository: PostRepositoryImpl) {
    suspend fun fetchAndSavePosts(): Flow<UiState<List<PostEntity>>> {
        return postRepository.fetchAndSavePosts()
    }

    fun getPostsFromDb(): Flow<List<PostEntity>> {
        return postRepository.getPostsFromDb()
    }
}