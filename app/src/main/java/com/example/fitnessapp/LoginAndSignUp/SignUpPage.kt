package com.example.fitnessapp.LoginAndSignUp

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.fitnessapp.Navigation.Routes
import com.example.fitnessapp.ViewModel.AuthState
import com.example.fitnessapp.ViewModel.AuthViewModel
import com.example.fitnessapp.ViewModel.UserInfoViewModel


@Composable
fun SignUpPage(navController: NavController, authviewModel: AuthViewModel,userInfoViewModel: UserInfoViewModel){
    val userEmail by userInfoViewModel.userEmail.collectAsState()
    val userPassword by userInfoViewModel.userPassword.collectAsState()

    val authState by authviewModel.authState.collectAsState()
    val context= LocalContext.current

    LaunchedEffect(Unit) {
        userInfoViewModel.clearFields()
    }

    LaunchedEffect(authState) {
        when(authState){
            is AuthState.Authenticated -> navController.navigate(Routes.home_screen){
                popUpTo(Routes.signUp_page) {inclusive=true }
            }
            is AuthState.Error ->
                Toast.makeText(context, (authState as AuthState.Error).message,Toast.LENGTH_SHORT).show()
            else -> Unit
        }
    }

    Surface(modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background) {
        Column(verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "SignUp",
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
            OutlinedTextField(value = userEmail?:"", onValueChange = {
                newValue -> userInfoViewModel.enterEmail(newValue)},
                label = { Text(text = "Email Address") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next),)
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(value = userPassword?:"", onValueChange = {
                newValue -> userInfoViewModel.enterPassword(newValue)},
                label = { Text(text = "Password") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            )
            Spacer(modifier = Modifier.height(8.dp))


            if (authState is AuthState.Loading) {
                CircularProgressIndicator()
                Spacer(modifier = Modifier.height(8.dp))
            }
            Button(onClick = {
                authviewModel.signUp(email = userEmail?:"", password = userPassword?:"")
            },
                enabled = authState!=AuthState.Loading) {
                Text(text = "Create Account")
            }
            TextButton(onClick = { navController.navigate(Routes.login_page) }) {
                Text(text = "Already have an account? Login.")
            }
        }
    }
}