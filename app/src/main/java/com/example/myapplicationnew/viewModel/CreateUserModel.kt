package com.example.myapplicationnew.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplicationnew.data.User
import com.example.myapplicationnew.data.repositor.UserRepository
import kotlinx.coroutines.launch

class CreateUserModel(private val userDao: UserRepository) : ViewModel(){
    var account by mutableStateOf("")
    var password by mutableStateOf("")
    var username by mutableStateOf("")
    var errorMessage by mutableStateOf<String?>(null)
    var successMessage by mutableStateOf<String?>(null)
    fun createuser (){
        viewModelScope.launch {
            try {
                val existingUser = userDao.checkaccountexist(account)
                val existingusername = userDao.checkusernameexiset(username)
                if (existingUser != null){
                    errorMessage = "帳號已存在"
                    successMessage = null
                    return@launch
                }else if(existingusername != null){
                    errorMessage = "username 已存在"
                    successMessage = null
                    return@launch
                }

                val newuser = User(account = account , password = password , username = username)
                userDao.insertUser(newuser)
                successMessage = "註冊成功"
                errorMessage = null


            }catch (e : Exception){
                successMessage = null
                errorMessage = "註冊失敗:${e.localizedMessage}"
            }
        }
    }

}