package com.vish.newsmvvm.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.vish.newsmvvm.models.Article

@Dao
interface ArticleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(article: Article): Long

    //this func returning livedata hence no need to suspend
    @Query("SELECT * FROM articles")
    fun getAllArticles(): LiveData<List<Article>>

    @Delete
    suspend fun deleteArticle(article: Article)
}