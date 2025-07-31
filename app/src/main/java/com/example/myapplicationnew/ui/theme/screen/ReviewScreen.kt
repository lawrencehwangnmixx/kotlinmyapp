package com.example.myapplicationnew.ui.theme.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplicationnew.data.GroupedReview
import com.example.myapplicationnew.data.review
import com.example.myapplicationnew.viewModel.ReviewViewModel

@Composable
fun ReviewScreen(currentusername: String, viewModel: ReviewViewModel, navController: NavController) {
    var input1 by remember { mutableStateOf("") }
    var input2 by remember { mutableStateOf("") }

    // 新增一個狀態表示目前顯示的是哪種 review list
    var showGrouped by remember { mutableStateOf(false) }



    Column(modifier = Modifier.padding(16.dp)) {
        Text("Welcome $currentusername", fontSize = 24.sp)

        OutlinedTextField(
            value = input1,
            onValueChange = { input1 = it },
            label = { Text("Input1") }
        )
        OutlinedTextField(
            value = input2,
            onValueChange = { input2 = it },
            label = { Text("Input2") }
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = {
                viewModel.getReviewByLocation(input1)
                showGrouped = false
            }) { Text("By Location") }

            Button(onClick = {
                viewModel.getReviewByUsername(input1)
                showGrouped = false
            }) { Text("By Name") }

            Button(onClick = {
                viewModel.getReviewByRestaurant(input1)
                showGrouped = false
            }) { Text("By Restaurant") }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = {
                viewModel.getReviewByCityAndUsername(input1, input2)
                showGrouped = false
            }) { Text("City + Name") }

            Button(onClick = {
                val rating = input2.toFloatOrNull()
                if (rating != null) {
                    viewModel.getReviewByCityAndRating(input1, rating)

                }
            }) { Text("City + Rating") }

            Button(onClick = {
                navController.navigate("createreview/$currentusername")
            }) { Text("Create review") }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 根據狀態來顯示正確的 Review List
        if (viewModel.reviews2.value.isNotEmpty()) {
            GroupedReviewList(reviews = viewModel.reviews2.value)
        } else {
            ReviewList(reviews = viewModel.reviews.value)
        }
    }
}


@Composable
fun ReviewItem(review: review) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(text = "username : ${review.customer_name}")
        Text(text = "location: ${review.location}")
        Text(text = "Restaurant: ${review.restaurant_name}")
        Text(text = "Comment: ${review.comment}")
        Text(text = "Rating: ${review.rating}")

    }
}

@Composable
fun ReviewList(reviews: List<review>) {
    LazyColumn {
        items(reviews) { review ->
            ReviewItem(review)
        }
    }
}
@Composable
fun GroupedReviewItem(review: GroupedReview) {
    Column(modifier = Modifier.padding(8.dp)) {
        Text(text = "Location: ${review.location}")
        Text(text = "Restaurant: ${review.restaurant_name}")
        Text(text = "Avg Rating: ${review.avg_rating}")
    }
}

@Composable
fun GroupedReviewList(reviews: List<GroupedReview>) {
    LazyColumn {
        items(reviews) { review ->
            GroupedReviewItem(review)
        }
    }
}
