package com.example.myapplicationnew.data.repositor

import com.example.myapplicationnew.data.Createreview
import com.example.myapplicationnew.data.EditReview
import com.example.myapplicationnew.data.GroupedReview
import com.example.myapplicationnew.data.api.RetrofitInstance
import com.example.myapplicationnew.data.api.ReviewApi
import com.example.myapplicationnew.data.review
import retrofit2.Response

class ReviewRepository(private val api: ReviewApi) {
    suspend fun getReviewByLocation(location: String) = api.getReviewByLocation(location)
    suspend fun getReviewsByName(name: String) = api.getReviewsByName(name)
    suspend fun getReviewByRestaurant(restaurant: String) = api.getReviewByRestaurant(restaurant)
    suspend fun getReviewByCityAndUsername(city: String, username: String) = api.getReviewByCityAndUsername(city, username)
    suspend fun getReviewByCityAndRating(city: String, rating: Float): List<GroupedReview> {
        return api.getReviewByCityAndRating(city, rating)
    }

    suspend fun getReviewById(id: Int) = api.getReviewById(id)
    suspend fun createReview(
        location: String,
        restaurant: String,
        username: String,
        comment: String,
        rating: Float
    ): review {
        val request = Createreview(location, restaurant, username, comment, rating)
        return api.createreview(request)
    }
    suspend fun editReview(id: Int, request: EditReview): review {
        return RetrofitInstance.api.editreview(id, request)
    }
    suspend fun deleteReview(id: Int): Response<Unit> {
        return RetrofitInstance.api.deleteReview(id)
    }
}
