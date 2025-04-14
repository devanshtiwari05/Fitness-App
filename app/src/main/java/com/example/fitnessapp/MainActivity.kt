package com.example.fitnessapp

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.fitnessapp.ui.theme.FitnessAppTheme
import com.example.fitnessapp.navigation.MyAppNavigation
import com.example.fitnessapp.utils.WorkoutUploader.uploadWorkoutPlans
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        uploadWorkoutPlans(this)
        val sharedPref = this.getSharedPreferences("WorkoutPrefs", Context.MODE_PRIVATE)
        val isUploaded = sharedPref.getBoolean("isUploaded", false)

        if (!isUploaded) {
            uploadWorkoutPlans(this)
            sharedPref.edit().putBoolean("isUploaded", true).apply()  // Mark as uploaded
        } else {
            Log.d("WorkoutUpload", "Workout plan already uploaded. Skipping upload.")
        }

        val db = com.google.firebase.firestore.FirebaseFirestore.getInstance()
        val collectionRef = db.collection("exercise_info")

        collectionRef.get().addOnSuccessListener { snapshot ->
            if (snapshot.isEmpty) {
                com.example.fitnessapp.utils.ExerciseUtils.generateExerciseCollection(this)
                Log.d("ExerciseUpload", "Exercise Upload successful.")
            } else {
                Log.d("ExerciseUpload", "Exercise info already exists. Skipping upload.")
            }
        }

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

