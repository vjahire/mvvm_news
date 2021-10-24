package com.vish.newsmvvm.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vish.newsmvvm.models.NewsResponse
import com.vish.newsmvvm.repository.NewsRepository
import com.vish.newsmvvm.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class NewsViewModel(
    val newRepository: NewsRepository
) : ViewModel() {
    val breakingNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    //we are using this to keep page number state in viewModel so the device config change won't affect our pagination
    var breakingNewsPage = 1

    init {
        getBreakingNews("in")
    }

    /**
     * get the data from repository, but using coroutines
     * This view model scope will make sure that this coroutine stays only alive as long as our view model is alive
     */
    private fun getBreakingNews(countryCode: String) = viewModelScope.launch {
        //before making network call we should emit loading state so our fragment can handle that
        breakingNews.postValue(Resource.Loading())

        //here we get the actual network response
        val response = newRepository.getBreakingNews(countryCode, breakingNewsPage)

        //process response and post in breakingNews
        breakingNews.postValue(handleBreakingNewsResponse(response))
    }

    /**
     * Here we decide weather we return success or error
     */
    private fun handleBreakingNewsResponse(response: Response<NewsResponse>): Resource<NewsResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }

        return Resource.Error(response.message())
    }

}