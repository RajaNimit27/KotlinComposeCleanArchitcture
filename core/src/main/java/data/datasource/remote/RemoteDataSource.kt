package data.datasource.remote

class RemoteDataSource(private val apiService: ApiService) {
    suspend fun getPosts() = apiService.getPosts()
}