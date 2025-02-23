package com.example.fitnessapp.loginAndSignUp

import android.app.Activity
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.fitnessapp.navigation.Routes
import com.example.fitnessapp.R
import com.example.fitnessapp.viewModel.AuthState
import com.example.fitnessapp.viewModel.AuthViewModel
import com.example.fitnessapp.viewModel.UserInfoViewModel

@Composable
fun LoginPage(
    navController: NavController,
    authViewModel: AuthViewModel,
    userInfoViewModel: UserInfoViewModel
) {
    val userEmail by userInfoViewModel.userEmail.collectAsState()
    val userPassword by userInfoViewModel.userPassword.collectAsState()
    val authState by authViewModel.authState.collectAsState()
    val context = LocalContext.current
    val activity = context as Activity

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartIntentSenderForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data = result.data
            val credential = com.google.android.gms.auth.api.identity.Identity.getSignInClient(context)
                .getSignInCredentialFromIntent(data)
            val idToken = credential.googleIdToken
            authViewModel.handleGoogleSignInResult(idToken)
        } else {
            Toast.makeText(context, "Google Sign-In failed", Toast.LENGTH_SHORT).show()
        }
    }

    LaunchedEffect(authState) {
        when (authState) {
            is AuthState.Authenticated -> {
                navController.navigate(Routes.home_screen) {
                    popUpTo(Routes.login_page) { inclusive = true }
                }
                authViewModel.resetAuthState()
            }
            is AuthState.Error -> {
                Toast.makeText(
                    context,
                    (authState as AuthState.Error).message,
                    Toast.LENGTH_SHORT
                ).show()
                authViewModel.resetAuthState()
            }
            else -> Unit
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Login",
                color = MaterialTheme.colorScheme.primary,
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(10.dp),
                lineHeight = 40.sp,
                style = TextStyle(fontSize = 32.sp))
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = userEmail ?: "",
                onValueChange = { userInfoViewModel.enterEmail(it) },
                label = { Text("Email Address") },
                keyboardOptions = KeyboardOptions.Default
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = userPassword ?: "",
                onValueChange = { userInfoViewModel.enterPassword(it) },
                label = { Text("Password") },
                keyboardOptions = KeyboardOptions.Default
            )
            Spacer(modifier = Modifier.height(16.dp))
            if (authState is AuthState.Loading) {
                CircularProgressIndicator()
                Spacer(modifier = Modifier.height(8.dp))
            }
            Button(
                onClick = { authViewModel.login(userEmail ?: "", userPassword ?: "") },
                enabled = authState !is AuthState.Loading
            ) {
                Text(text = "Login")
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = { authViewModel.googleSignIn(activity, launcher) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.google_logo),
                    contentDescription = "Google Sign-In",
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Sign in with Google")
            }
            Spacer(modifier = Modifier.height(8.dp))
            TextButton(onClick = { navController.navigate(Routes.signUp_page) }) {
                Text("Don't have an account? Sign Up")
            }
        }
    }
}
