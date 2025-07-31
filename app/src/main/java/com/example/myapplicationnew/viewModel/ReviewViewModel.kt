package com.example.myapplicationnew.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplicationnew.data.GroupedReview
import com.example.myapplicationnew.data.repositor.ReviewRepository
import com.example.myapplicationnew.data.review
import kotlinx.coroutines.launch

class ReviewViewModel(private val repository: ReviewRepository, private val currentusername: String) : ViewModel() {

    var reviews = mutableStateOf<List<review>>(emptyList())
        private set

    var reviews2 = mutableStateOf<List<GroupedReview>>(emptyList())
        private set

    val errorMessage = mutableStateOf<String?>(null)

    fun getReviewByLocation(location: String) {
        viewModelScope.launch {
            try {
                val result = repository.getReviewByLocation(location)
                reviews.value = result
                reviews2.value = emptyList() // 清空另一個
                errorMessage.value = null
            } catch (e: Exception) {
                errorMessage.value = "讀取失敗: ${e.localizedMessage}"
            }
        }
    }

    fun getReviewByUsername(username: String) {
        viewModelScope.launch {
            try {
                val result = repository.getReviewsByName(username)
                reviews.value = result
                reviews2.value = emptyList()
                errorMessage.value = null
            } catch (e: Exception) {
                errorMessage.value = "讀取失敗: ${e.localizedMessage}"
            }
        }
    }

    fun getReviewByRestaurant(restaurant: String) {
        viewModelScope.launch {
            try {
                val result = repository.getReviewByRestaurant(restaurant)
                reviews.value = result
                reviews2.value = emptyList()
                errorMessage.value = null
            } catch (e: Exception) {
                errorMessage.value = "讀取失敗: ${e.localizedMessage}"
            }
        }
    }

    fun getReviewByCityAndUsername(city: String, username: String) {
        viewModelScope.launch {
            try {
                val result = repository.getReviewByCityAndUsername(city, username)
                reviews.value = result
                reviews2.value = emptyList()
                errorMessage.value = null
            } catch (e: Exception) {
                errorMessage.value = "讀取失敗: ${e.localizedMessage}"
            }
        }
    }

    fun getReviewByCityAndRating(city: String, rating: Float) {
        viewModelScope.launch {
            try {
                val result = repository.getReviewByCityAndRating(city, rating)
                reviews2.value = result
                reviews.value = emptyList() // 清空另一個
                errorMessage.value = null
            } catch (e: Exception) {
                errorMessage.value = "讀取失敗: ${e.localizedMessage}"
            }
        }
    }
}
