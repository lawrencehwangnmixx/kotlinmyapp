package com.example.myapplicationnew.data


data class review(
    val customer_name: String,
    val restaurant_name: String,
    val location: String,
    val comment: String,
    val rating: Float,
    val id: Int
)
data class Createreview(
    val location: String,
    val customer_name: String,
    val restaurant_name: String,
    val comment: String,
    val rating: Float
)
data class EditReview(
    val location: String,
    val restaurant_name: String,
    val comment: String,
    val rating: Float
)
data class GroupedReview(
    val restaurant_name: String,
    val location: String,
    val avg_rating: Float
)
