package com.example.fitnessapp.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fitnessapp.GetBodyTypeInfo
import com.example.fitnessapp.GetFinalDetails
import com.example.fitnessapp.GetGenderInfo
import com.example.fitnessapp.GetGoalInfo
import com.example.fitnessapp.Home_Screen
import com.example.fitnessapp.Response.GetResponse
import com.example.fitnessapp.SplashScreen

@Composable
fun MyAppNavigation(){
    val navController= rememberNavController()
    NavHost(navController = navController, startDestination = Routes.splash_screen, builder = {
        composable(Routes.splash_screen){
            SplashScreen(navController)
        }
        composable(Routes.home_screen) {
            Home_Screen(navController)
        }
        composable(Routes.get_gender_info) {
            GetGenderInfo(navController)
        }
        composable(Routes.get_goal_info) {
            GetGoalInfo(navController)
        }
        composable(Routes.get_body_type_info) {
            GetBodyTypeInfo(navController)
        }
        composable(Routes.get_final_details) {
            GetFinalDetails(navController)
        }
        composable(Routes.get_response) {
            GetResponse(navController)
        }
    })
}