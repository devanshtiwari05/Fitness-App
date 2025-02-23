package com.example.fitnessapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fitnessapp.GetBodyTypeInfo
import com.example.fitnessapp.GetFinalDetails
import com.example.fitnessapp.GetGenderInfo
import com.example.fitnessapp.GetGoalInfo
import com.example.fitnessapp.Home_Screen
import com.example.fitnessapp.loginAndSignUp.LoginPage
import com.example.fitnessapp.loginAndSignUp.SignUpPage
import com.example.fitnessapp.Response.GetResponse
import com.example.fitnessapp.SplashScreen
import com.example.fitnessapp.viewModel.AuthViewModel
import com.example.fitnessapp.viewModel.HomeScreenViewModel
import com.example.fitnessapp.viewModel.UserInfoViewModel

@Composable
fun MyAppNavigation(modifier: Modifier){
    val userInfoViewModel:UserInfoViewModel= hiltViewModel()
    val homeScreenViewModel:HomeScreenViewModel= hiltViewModel()
    val authViewModel:AuthViewModel= hiltViewModel()
    val navController= rememberNavController()
    NavHost(navController = navController, startDestination = Routes.splash_screen, builder = {
        composable(Routes.splash_screen){
            SplashScreen(navController,authViewModel)
        }
        composable(Routes.login_page) {
            LoginPage(navController,authViewModel,userInfoViewModel)
        }
        composable(Routes.signUp_page) {
            SignUpPage(navController,authViewModel,userInfoViewModel)
        }
        composable(Routes.home_screen) {
            Home_Screen(navController,homeScreenViewModel,authViewModel)
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