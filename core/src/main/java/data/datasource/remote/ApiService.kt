package data.datasource.remote

import data.model.PostEntity
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("posts")
    suspend fun getPosts(): Response<List<PostEntity>>
}