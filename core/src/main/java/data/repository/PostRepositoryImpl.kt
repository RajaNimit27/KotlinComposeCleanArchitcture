package data.repository

import common.UiState
import data.datasource.local.PostDao
import data.datasource.remote.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import common.toResultFlow
import data.model.PostEntity
import domain.repository.PostRepository


class PostRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val postDao: PostDao
) : PostRepository {
    override suspend fun getPosts(): Flow<UiState<List<PostEntity>>> {
        return toResultFlow {
          //  val postEntities = remoteDataSource.getPosts()
         //   postDao.insertAllPost(postEntities)
            remoteDataSource.getPosts()
        }
    }
}

