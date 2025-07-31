package com.example.myapplicationnew.data.api



import com.example.myapplicationnew.data.Createreview
import com.example.myapplicationnew.data.EditReview
import com.example.myapplicationnew.data.GroupedReview
import com.example.myapplicationnew.data.review
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ReviewApi {
    @Headers("Content-Type: application/json; charset=UTF-8")
    @GET("reviews/by_location")
    suspend fun getReviewByLocation(@Query("location") location: String): List<review>

    @GET("reviews/by_customer")
    suspend fun getReviewsByName(@Query("customer_name") customer_name: String): List<review>

    @GET("reviews/by_restaurant")
    suspend fun getReviewByRestaurant(@Query("restaurant_name") restaurant_name: String): List<review>

    @GET("reviews/bycityanduser")
    suspend fun getReviewByCityAndUsername(
        @Query("location") location: String,
        @Query("customer_name") customer_name: String
    ): List<review>

    @GET("reviews/bycityandrating")
    suspend fun getReviewByCityAndRating(
        @Query("location") location: String,
        @Query("rating") rating: Float
    ): List<GroupedReview>

    @GET("reviews/byid")
    suspend fun getReviewById(
        @Query("id") id: Int
    ):review
    @POST("reviews")
    suspend fun createreview(
        @Body request : Createreview) : review

    @PUT("reviews/{review_id}")
    suspend fun editreview(@Path("review_id") id: Int, @Body request: EditReview) :  review

    @DELETE("reviews/{review_id}")
    suspend fun deleteReview(@Path("review_id") id: Int): Response<Unit>
}