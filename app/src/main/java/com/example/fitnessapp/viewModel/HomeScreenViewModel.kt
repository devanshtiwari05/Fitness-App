package com.example.fitnessapp.viewModel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(

) : ViewModel(){
    private val _selectedWorkout = MutableStateFlow<String?>(null)

    val selectedWorkout:StateFlow<String?> = _selectedWorkout.asStateFlow()

    fun selectWorkout(workout:String){
        _selectedWorkout.value=workout
    }
}