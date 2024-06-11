package data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import data.model.PostEntity

@Database(entities = [(PostEntity::class)], version = 1)
abstract class PostDataBase : RoomDatabase() {
    abstract fun getPostDao() : PostDao
}