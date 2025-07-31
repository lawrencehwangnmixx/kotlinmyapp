package com.example.myapplicationnew

import LoginScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myapplicationnew.data.api.RetrofitInstance
import com.example.myapplicationnew.data.repositor.ReviewRepository
import com.example.myapplicationnew.data.repositor.UserRepository
import com.example.myapplicationnew.ui.theme.MyApplicationnewTheme

import com.example.myapplicationnew.ui.theme.screen.CreateReviewScreen
import com.example.myapplicationnew.ui.theme.screen.CreateUserScreen
import com.example.myapplicationnew.ui.theme.screen.EditReviewScreen

import com.example.myapplicationnew.ui.theme.screen.ReviewScreen
import com.example.myapplicationnew.viewModel.CreateReviewModel
import com.example.myapplicationnew.viewModel.CreateUserModel
import com.example.myapplicationnew.viewModel.EditReviewModel
import com.example.myapplicationnew.viewModel.LoginViewModel
import com.example.myapplicationnew.viewModel.ReviewViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val db = AppDatabase.getDatabase(applicationContext)
        val userDao = db.userDao()
        val userRepository = UserRepository(userDao)

        setContent {
            MyApplicationnewTheme {
                MyApp(userRepository)
            }
        }
    }
}

@Composable
fun MyApp(userRepository: UserRepository) {
    val navController = rememberNavController()
    val reviewRepository = ReviewRepository(RetrofitInstance.api)
    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            val loginViewModel: LoginViewModel = viewModel(
                factory = LoginViewModelFactory(userRepository)
            )
            LoginScreen(loginViewModel, navController)
        }
        composable("createuser") {
            val createUserViewModel: CreateUserModel = viewModel(
                factory = CreateUserViewModelFactory(userRepository)
            )
            CreateUserScreen(createUserViewModel, navController)
        }
        composable(
            route = "review/{currentusername}",
            arguments = listOf(navArgument("currentusername") { type = NavType.StringType })
        ) { backStackEntry ->
            val currentusername = backStackEntry.arguments?.getString("currentusername") ?: ""
            val viewModel: ReviewViewModel = viewModel(
                factory = ReviewViewModelFactory(reviewRepository, currentusername)
            )
            ReviewScreen(currentusername = currentusername,viewModel = viewModel, navController = navController)
        }

        composable(
            route = "createreview/{currentusername}",
            arguments = listOf(navArgument("currentusername") { type = NavType.StringType })
        ) { backStackEntry ->
            val currentusername = backStackEntry.arguments?.getString("currentusername") ?: ""
            val createReviewModel: CreateReviewModel = viewModel(
                factory = CreateReviewViewModelFactory(reviewRepository, currentusername)
            )
            CreateReviewScreen(viewModel = createReviewModel, navController = navController)
        }
        composable(
            route = "editreview/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ){ backStackEntry->
                val reviewid = backStackEntry.arguments?.getInt("id") ?: -1
            val editReviewModel : EditReviewModel = viewModel(
                factory = EditReviewModelFactory(reviewRepository)
            )
            EditReviewScreen(id = reviewid, navController = navController, viewModel = editReviewModel )
        }


    }
}



