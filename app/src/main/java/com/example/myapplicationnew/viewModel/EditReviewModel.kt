package com.example.myapplicationnew.viewModel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplicationnew.data.EditReview
import com.example.myapplicationnew.data.repositor.ReviewRepository
import com.example.myapplicationnew.data.review
import kotlinx.coroutines.launch


class EditReviewModel(private val repository : ReviewRepository) : ViewModel() {

    suspend fun getReviewbyId(
        id: Int
    ): review{
        Log.d("EditReviewModel", "Fetching review with id=$id")
        return repository.getReviewById(id)
    }
    fun editReview(
        id: Int,
        location: String,
        restaurant: String,
        comment: String,
        rating: Float
    ) {
        viewModelScope.launch {
            try {
                val review = EditReview(location, restaurant, comment, rating)
                repository.editReview(id, review)
                Log.d("EditReviewScreen", "Review updated!")
            } catch (e: Exception) {
                Log.e("EditReviewScreen", "Error updating review: ${e.message}")
            }
        }
    }
    fun deleteReview(id: Int){
        viewModelScope.launch {
            try {
                repository.deleteReview(id)
                Log.d("EditReviewScreen","Review delete")
            }catch (e : Exception){
                Log.e("EditReviewScreen","Error updating review: ${e.message}")
            }
        }
    }
}
