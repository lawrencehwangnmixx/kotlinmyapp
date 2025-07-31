package com.example.myapplicationnew.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplicationnew.data.repositor.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(private val userDao: UserRepository) : ViewModel() {
    private val _username = MutableStateFlow<String?>(null)
    val username = _username.asStateFlow()
    val loginSuccess = mutableStateOf(false)
    private val _errormessage = MutableStateFlow<String?>(null)
    val errormessage = _errormessage.asStateFlow()

    fun login(account : String, password : String){
        viewModelScope.launch {
            try {
                val result = userDao.CheckUser(account,password)
                if (result !=null){
                    loginSuccess.value = true
                    _username.value = result.toString()
                    _errormessage.value = null
                }else{
                    _username.value = null
                    _errormessage.value = "帳號或密碼錯誤"
                }
            }catch (e : Exception){
                _username.value = null
                _errormessage.value = "發生錯誤:${e.localizedMessage}"
            }
        }
    }
}