package com.example.fitnessapp.ViewModel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeScreenViewModel : ViewModel(){
    private val _selectedWorkout = MutableStateFlow<String?>(null)

    val selectedWorkout:StateFlow<String?> = _selectedWorkout.asStateFlow()

    fun selectWorkout(workout:String){
        _selectedWorkout.value=workout
    }
}