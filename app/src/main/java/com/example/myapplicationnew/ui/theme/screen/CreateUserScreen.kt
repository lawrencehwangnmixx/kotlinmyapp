package com.example.myapplicationnew.ui.theme.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplicationnew.viewModel.CreateUserModel

@Composable
fun CreateUserScreen(
    viewModel: CreateUserModel,
    navController: NavController
) {
    val account = viewModel.account
    val password = viewModel.password
    val username = viewModel.username
    val errorMessage = viewModel.errorMessage
    val successMessage = viewModel.successMessage

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp, vertical = 24.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "註冊帳號",
            fontSize = 32.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp)
        )

        // 帳號輸入
        OutlinedTextField(
            value = account,
            onValueChange = { viewModel.account = it },
            label = { Text("帳號") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 密碼輸入
        OutlinedTextField(
            value = password,
            onValueChange = { viewModel.password = it },
            label = { Text("密碼") },
            visualTransformation = PasswordVisualTransformation(),
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 使用者名稱輸入
        OutlinedTextField(
            value = username,
            onValueChange = { viewModel.username = it },
            label = { Text("名稱") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        // 註冊按鈕
        Button(
            onClick = { viewModel.createuser() },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text("註冊")
        }

        Spacer(modifier = Modifier.height(12.dp))

        // 顯示錯誤訊息（紅色）
        errorMessage?.let {
            Text(
                text = it,
                color = Color.Red,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        // 顯示成功訊息（綠色）
        successMessage?.let {
            Text(
                text = it,
                color = Color.Green,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 回到登入頁面
        TextButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("返回登入頁")
        }
    }
}
