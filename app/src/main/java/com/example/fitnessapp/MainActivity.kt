package com.example.fitnessapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.fitnessapp.ui.theme.FitnessAppTheme
import com.example.fitnessapp.Navigation.MyAppNavigation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FitnessAppTheme {
                // A surface container using the 'background' color from the theme
//                val authViewModel:AuthViewModel by viewModels()
                Scaffold(modifier = Modifier.fillMaxSize(),
                        containerColor = MaterialTheme.colorScheme.background) {innerPadding->
                            MyAppNavigation(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

