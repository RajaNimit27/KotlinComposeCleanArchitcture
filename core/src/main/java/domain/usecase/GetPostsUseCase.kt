package domain.usecase

import common.UiState
import data.model.PostEntity
import domain.repository.PostRepository
import kotlinx.coroutines.flow.Flow

class GetPostsUseCase(private val postRepository: PostRepository) {
    suspend operator fun invoke(): Flow<UiState<List<PostEntity>>> {
        return postRepository.getPosts()
    }
}