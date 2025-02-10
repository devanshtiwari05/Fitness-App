package com.example.fitnessapp.Navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fitnessapp.GetBodyTypeInfo
import com.example.fitnessapp.GetFinalDetails
import com.example.fitnessapp.GetGenderInfo
import com.example.fitnessapp.GetGoalInfo
import com.example.fitnessapp.Home_Screen
import com.example.fitnessapp.LoginAndSignUp.LoginPage
import com.example.fitnessapp.LoginAndSignUp.SignUpPage
import com.example.fitnessapp.Response.GetResponse
import com.example.fitnessapp.SplashScreen
import com.example.fitnessapp.ViewModel.AuthViewModel
import com.example.fitnessapp.ViewModel.HomeScreenViewModel
import com.example.fitnessapp.ViewModel.UserInfoViewModel

@Composable
fun MyAppNavigation(modifier: Modifier){
    val userInfoViewModel:UserInfoViewModel= viewModel()
    val homeScreenViewModel:HomeScreenViewModel= viewModel()
    val authViewModel:AuthViewModel= viewModel()
    val navController= rememberNavController()
    NavHost(navController = navController, startDestination = Routes.splash_screen, builder = {
        composable(Routes.splash_screen){
            SplashScreen(navController)
        }
        composable(Routes.login_page) {
            LoginPage(navController,authViewModel)
        }
        composable(Routes.signUp_page) {
            SignUpPage(navController,authViewModel)
        }
        composable(Routes.home_screen) {
            Home_Screen(navController,homeScreenViewModel)
        }
        composable(Routes.get_gender_info) {
            GetGenderInfo(navController,userInfoViewModel)
        }
        composable(Routes.get_goal_info) {
            GetGoalInfo(navController,userInfoViewModel)
        }
        composable(Routes.get_body_type_info) {
            GetBodyTypeInfo(navController,userInfoViewModel)
        }
        composable(Routes.get_final_details) {
            GetFinalDetails(navController,userInfoViewModel)
        }
        composable(Routes.get_response) {
            GetResponse(navController)
        }
    })
}