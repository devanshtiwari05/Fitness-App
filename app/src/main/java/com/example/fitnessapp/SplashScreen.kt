package com.example.fitnessapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.fitnessapp.navigation.Routes
import com.example.fitnessapp.viewModel.AuthState
import com.example.fitnessapp.viewModel.AuthViewModel
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController,authViewModel: AuthViewModel){
    val authState by authViewModel.authState.collectAsState()
    LaunchedEffect(Unit){
        delay(3000)
        when(authState){
            is AuthState.Authenticated -> navController.navigate(Routes.home_screen){
                popUpTo(Routes.splash_screen){inclusive=true}
            }
            is AuthState.UnAuthenticated -> navController.navigate(Routes.login_page){
                popUpTo(Routes.splash_screen) {inclusive=true}
            }
            else -> Unit
        }
    }
    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center) {
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            Image(painter= painterResource(id = R.drawable.samplelogo),
                contentDescription="App Logo",
                modifier= Modifier.size(500.dp))
            Spacer(modifier = Modifier.height(16.dp))
            Text(text="Your Fitness, Our Commitment",
                fontSize = 20.sp,
                fontWeight = FontWeight.Black,
                color= MaterialTheme.colorScheme.onBackground)
        }
    }
}