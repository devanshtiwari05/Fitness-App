package com.example.fitnessapp.ViewModel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.Calendar

class UserInfoViewModel : ViewModel(){
    private val _userGender=MutableStateFlow<String?>(null)
    val userGender:StateFlow<String?> = _userGender.asStateFlow()
    private val _userBodyType= MutableStateFlow<String?>(null)
    val userBodyType: StateFlow<String?> = _userBodyType.asStateFlow()
    private val _userGoal= MutableStateFlow<String?>(null)
    val userGoal: StateFlow<String?> = _userGoal.asStateFlow()
    private val _userName = MutableStateFlow<String?>(null)
    val userName: StateFlow<String?> = _userName.asStateFlow()
    private val _userDateOfBirth= MutableStateFlow<String?>(null)
    val userDateOfBirth: StateFlow<String?> = _userDateOfBirth.asStateFlow()
    private val _userActivityRate= MutableStateFlow<String?>(null)
    val userActivityRate: StateFlow<String?> = _userActivityRate.asStateFlow()
    private val _userBodyWeight= MutableStateFlow<String?>(null)
    val userBodyWeight: StateFlow<String?> = _userBodyWeight.asStateFlow()
    private val _userAge= MutableStateFlow<Int?>(null)
    val userAge: StateFlow<Int?> = _userAge.asStateFlow()
    fun selectGender(gender:String){
        _userGender.value=gender
    }
    fun selectBodyType(bodyType:String){
        _userBodyType.value=bodyType
    }
    fun selectGoal(goal:String){
        _userGoal.value=goal
    }
    fun enterUserName(name:String){
        _userName.value=name
    }
    fun enterDob(dob:String){
        _userDateOfBirth.value=dob
    }
    fun enterActivityRate(activityRate:String){
        _userActivityRate.value=activityRate
    }
    fun enterBodyWeight(bodyWeight:String){
        _userBodyWeight.value=bodyWeight
    }
    fun calculateAndSetAge(dobMillis: Long) {
        val dob = Calendar.getInstance().apply { timeInMillis = dobMillis }
        val today = Calendar.getInstance()

        var age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR)

        if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)) {
            age--
        }
        if (age>=0)
            _userAge.value = age
        else
            _userAge.value=0
    }



}