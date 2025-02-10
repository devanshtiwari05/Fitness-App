package com.example.fitnessapp.LoginAndSignUp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.example.fitnessapp.ViewModel.AuthViewModel

@Composable
fun SignUpPage(navController: NavController, viewModel: AuthViewModel){
    var useremail by remember{ mutableStateOf("") }
    var userpassword by remember{ mutableStateOf("") }

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
            OutlinedTextField(value = useremail, onValueChange = {useremail=it},
                label = { Text(text = "Email Address") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Next),)
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(value = userpassword, onValueChange = {userpassword=it},
                label = { Text(text = "Password") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Next),
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = {  }) {
                Text(text = "Create Account")
            }
            TextButton(onClick = { navController.navigate(Routes.login_page) }) {
                Text(text = "Already have an account? Login.")
            }
        }
    }
}