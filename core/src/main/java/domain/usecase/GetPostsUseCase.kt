package domain.usecase


import common.UiState
import data.model.PostEntity
import data.repository.PostRepositoryImpl
import domain.repository.PostRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetPostsUseCase(private val postRepository: PostRepository) {
    suspend operator fun invoke(): Flow<UiState<List<PostEntity>>> = flow {
        //get data from server
        postRepository.fetchAndSavePosts().collect { postsFromDb ->
            emit(postsFromDb)
        }

        //get data from db
        postRepository.getPostsFromDb().collect { postsFromDb ->
            emit(UiState.Success(postsFromDb))
        }
    }

}