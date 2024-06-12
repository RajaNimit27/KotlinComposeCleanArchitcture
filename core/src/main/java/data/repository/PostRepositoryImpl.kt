package data.repository

import common.UiState
import data.datasource.local.PostDao
import data.datasource.remote.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import data.model.PostEntity
import domain.repository.PostRepository
import kotlinx.coroutines.flow.flow


class PostRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val postDao: PostDao
) : PostRepository {

    override fun fetchAndSavePosts(): Flow<UiState<List<PostEntity>>> = flow {
        emit(UiState.Loading)
        try {
            val remotePosts = remoteDataSource.getPosts()
            remotePosts.body()?.let { postDao.insertAllPost(it) }
            emit(UiState.Success(remotePosts.body()))
        } catch (e: Exception) {
            emit(UiState.Error(e.message?:""))
        }
    }

    override fun getPostsFromDb(): Flow<List<PostEntity>> {
        return postDao.getPosts()
    }
}


