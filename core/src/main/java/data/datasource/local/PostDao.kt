package data.datasource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import data.model.PostEntity

@Dao
interface PostDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllPost(post: List<PostEntity>)

    @Query("SELECT * FROM table_post")
    fun getPosts(): Flow<List<PostEntity>>
}