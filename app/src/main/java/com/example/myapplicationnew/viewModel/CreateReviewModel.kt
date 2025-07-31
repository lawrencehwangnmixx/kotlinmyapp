package com.example.myapplicationnew.viewModel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplicationnew.data.repositor.ReviewRepository
import com.example.myapplicationnew.data.review
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CreateReviewModel(private val repository: ReviewRepository, private val currentusername: String) : ViewModel() {
    val location = mutableStateOf("")
    val customer_name = mutableStateOf(currentusername)
    val restaurant_name = mutableStateOf("")
    val comment = mutableStateOf("")
    val rating = mutableStateOf("")
// 在 ReviewViewModel.kt 裡面

    private val _successMessage = MutableStateFlow<String?>(null)
    val successMessage: StateFlow<String?> = _successMessage

    val userReviews = mutableStateOf<List<review>>(emptyList())

    fun clearSuccessMessage() {
        _successMessage.value = null
    }

    fun submitReview() {
        val ratingFloat = rating.value.toFloatOrNull() ?: 0f
        val location = location.value
        val customer_name = customer_name.value
        val restaurant_name = restaurant_name.value
        val comment = comment.value
        val rating = ratingFloat

        viewModelScope.launch {
            try {
                val result = repository.createReview(location,customer_name,restaurant_name,comment,rating)
                _successMessage.value = "評論新增成功 ✅"
                clearForm()
            } catch (e: Exception) {
                Log.e("Review", "Failed to post review", e)
            }
        }
    }
    private fun clearForm() {
        location.value = ""
        restaurant_name.value = ""
        comment.value = ""
        rating.value = ""
    }
    fun loadUserReview(){
        viewModelScope.launch {
            try {
                val result = repository.getReviewsByName(currentusername)
                userReviews.value = result
            }catch (e :Exception){
                Log.e("Review", "Load failed: ${e.message}")
            }
        }
    }

}
