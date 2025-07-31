package com.example.myapplicationnew.ui.theme.screen

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplicationnew.viewModel.EditReviewModel

@Composable
fun EditReviewScreen(id: Int, navController: NavController, viewModel: EditReviewModel) {
    val location = remember { mutableStateOf("") }
    val restaurantName = remember { mutableStateOf("") }
    val comment = remember { mutableStateOf("") }
    val rating = remember { mutableStateOf("") }

    // 當畫面建立時載入資料
    LaunchedEffect(Unit) {
        try {
            val review = viewModel.getReviewbyId(id)
            location.value = review.location
            restaurantName.value = review.restaurant_name
            comment.value = review.comment
            rating.value = review.rating.toString()
        } catch (e: Exception) {
            Log.e("EditReviewScreen", "Error loading review: ${e.message}")
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Edit Review", style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = location.value,
            onValueChange = { location.value = it },
            label = { Text("Location") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = restaurantName.value,
            onValueChange = { restaurantName.value = it },
            label = { Text("Restaurant") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = comment.value,
            onValueChange = { comment.value = it },
            label = { Text("Comment") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = rating.value,
            onValueChange = { rating.value = it },
            label = { Text("Rating") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                viewModel.editReview(
                    id = id,
                    location = location.value,
                    restaurant = restaurantName.value,
                    comment = comment.value,
                    rating = rating.value.toFloatOrNull() ?: 0f
                )
                navController.popBackStack()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Update")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                viewModel.deleteReview(id)
                navController.popBackStack()
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Delete")
        }
        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                navController.popBackStack()
            },
            colors = ButtonDefaults.buttonColors(contentColor = Color.Gray),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("回前一頁")
        }
    }
}
