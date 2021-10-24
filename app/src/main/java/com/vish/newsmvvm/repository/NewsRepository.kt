package com.vish.newsmvvm.repository

import com.vish.newsmvvm.api.RetrofitInstance
import com.vish.newsmvvm.db.ArticleDatabase

/**
 * This class is used to get data from database and remote server
 */
class NewsRepository(
    val db: ArticleDatabase
) {
    suspend fun getBreakingNews(countryCode: String, pageNumber: Int) =
        RetrofitInstance.api.getBreakingNews(countryCode, pageNumber)
}