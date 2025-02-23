package com.example.fitnessapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.fitnessapp.navigation.Routes
import com.example.fitnessapp.viewModel.AuthState
import com.example.fitnessapp.viewModel.AuthViewModel
import com.example.fitnessapp.viewModel.HomeScreenViewModel

@Composable

fun Home_Screen(navController: NavController, homeScreenViewModel: HomeScreenViewModel,authViewModel: AuthViewModel) {
    val selectedWorkout by homeScreenViewModel.selectedWorkout.collectAsState()
    val authState by authViewModel.authState.collectAsState()

    LaunchedEffect(authState) {
        when(authState){
            is AuthState.UnAuthenticated-> navController.navigate(Routes.login_page){
                popUpTo(Routes.home_screen){inclusive=true}
            }
            else -> Unit
        }
    }

    MaterialTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Which one would you prefer?",
                    color = MaterialTheme.colorScheme.primary,
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(10.dp),
                    lineHeight = 40.sp,
                    style = TextStyle(fontSize = 32.sp,
                    )
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .aspectRatio(1f)
                            .padding(8.dp)
                            .size(250.dp)
                            .clickable {
                                navController.navigate(Routes.get_gender_info)
                                homeScreenViewModel.selectWorkout("Gym Workout")
                            }
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Image(
                                painter = painterResource(id = R.drawable.gymworkout),
                                contentDescription = "Gym Workout",
                                contentScale = ContentScale.Fit
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                text = "Gym Workout",
                                color = MaterialTheme.colorScheme.onBackground,
                                fontWeight = FontWeight.SemiBold
                            )
                        }

                    }

                    Spacer(modifier = Modifier.width(16.dp))



                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .aspectRatio(1f)
                            .padding(8.dp)
                            .size(250.dp)
                            .clickable {
                                navController.navigate(Routes.get_gender_info)
                                homeScreenViewModel.selectWorkout("Home Workout")
                            }
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Image(
                                painter = painterResource(id = R.drawable.homeworkout),
                                contentDescription = "Home Workout",
                                contentScale = ContentScale.Fit
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                text = "Home Workout",
                                color = MaterialTheme.colorScheme.onBackground,
                                fontWeight = FontWeight.SemiBold
                            )
                        }

                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = {
                    authViewModel.signOut()
                }) {
                    Text("Sign Out")
                }
            }
        }
    }
}


