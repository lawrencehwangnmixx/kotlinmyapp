package com.example.myapplicationnew

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplicationnew.data.repositor.ReviewRepository
import com.example.myapplicationnew.data.repositor.UserRepository
import com.example.myapplicationnew.viewModel.CreateReviewModel
import com.example.myapplicationnew.viewModel.CreateUserModel
import com.example.myapplicationnew.viewModel.EditReviewModel
import com.example.myapplicationnew.viewModel.LoginViewModel
import com.example.myapplicationnew.viewModel.ReviewViewModel

class LoginViewModelFactory(private val userRepository: UserRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LoginViewModel(userRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

class CreateUserViewModelFactory(private val userRepository: UserRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CreateUserModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CreateUserModel(userRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
class ReviewViewModelFactory(private val repository: ReviewRepository, private val currentusername: String) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ReviewViewModel::class.java)) {
            return ReviewViewModel(repository,currentusername) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
class CreateReviewViewModelFactory(private val repository: ReviewRepository,private val username : String): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CreateReviewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CreateReviewModel(repository,username) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}


class EditReviewModelFactory(private val repository: ReviewRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EditReviewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return EditReviewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
