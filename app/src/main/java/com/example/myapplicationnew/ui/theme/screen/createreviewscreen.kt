package com.example.myapplicationnew.ui.theme.screen

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplicationnew.viewModel.CreateReviewModel

@Composable
fun CreateReviewScreen(viewModel: CreateReviewModel, navController: NavController) {
    val location = viewModel.location.value
    val restaurantName = viewModel.restaurant_name.value
    val comment = viewModel.comment.value
    val rating = viewModel.rating.value
    val successMessage by viewModel.successMessage.collectAsState()
    val userReviews = viewModel.userReviews.value
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        viewModel.loadUserReview()
    }
    LaunchedEffect(successMessage) {
        successMessage?.let {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            viewModel.clearSuccessMessage() // 記得清空，避免重複顯示
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp, vertical = 24.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "新增評論",
            fontSize = 32.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp)
        )

        OutlinedTextField(
            value = location,
            onValueChange = { viewModel.location.value = it },
            label = { Text("地點") },
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
        )


        OutlinedTextField(
            value = restaurantName,
            onValueChange = { viewModel.restaurant_name.value = it },
            label = { Text("餐廳名稱") },
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
        )

        OutlinedTextField(
            value = comment,
            onValueChange = { viewModel.comment.value = it },
            label = { Text("評論內容") },
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
        )

        OutlinedTextField(
            value = rating,
            onValueChange = { viewModel.rating.value = it },
            label = { Text("評分 (0~5)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
        )

        Button(
            onClick = {
                viewModel.submitReview()
            },
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
        ) {
            Text("送出評論")
        }
        Button(
            onClick = {navController.popBackStack()
            },
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
        ) {
            Text("返回")
        }
        Text("你的評論", fontSize = 20.sp, modifier = Modifier.padding(vertical = 8.dp))

        LazyColumn {
            items(userReviews) { review ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .clickable {
                            navController.navigate("editreview/${review.id}")
                        }
                ) {
                    Column(modifier = Modifier.padding(8.dp)) {
                        Text("餐廳：${review.restaurant_name}")
                        Text("地點：${review.location}")
                        Text("評論：${review.comment}")
                        Text("評分：${review.rating}")
                    }
                }
            }
        }
    }
}