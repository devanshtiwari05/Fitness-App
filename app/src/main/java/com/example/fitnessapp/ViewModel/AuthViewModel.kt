package com.example.fitnessapp.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AuthViewModel: ViewModel() {
    private val auth: FirebaseAuth=FirebaseAuth.getInstance()
    private val _authState= MutableStateFlow<AuthState?>(null)
    val authState: StateFlow<AuthState?> = _authState.asStateFlow()

    init {
        checkAuthStatus()
    }

    fun checkAuthStatus(){
        _authState.value=AuthState.Checking
        if(auth.currentUser==null){
            _authState.value=AuthState.UnAuthenticated
        }
        else{
            _authState.value=AuthState.Authenticated
        }
    }

    fun login(email:String,password:String){
        if(email.isEmpty()||password.isEmpty()){
            _authState.value=AuthState.Error("Email and Password cannot be empty.")
            return
        }
        _authState.value=AuthState.Loading
        auth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener { task->
                viewModelScope.launch {
                    if (task.isSuccessful) {
                        _authState.value = AuthState.Authenticated
                    } else {
                        _authState.value =
                            AuthState.Error(task.exception?.message ?: "Something went wrong!")
                    }
                }
        }
    }

    fun signUp(email:String,password:String){
        if(email.isEmpty()||password.isEmpty()){
            _authState.value=AuthState.Error("Email and Password cannot be empty.")
            return
        }
        _authState.value=AuthState.Loading
        auth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener { task->
                viewModelScope.launch {
                    if (task.isSuccessful) {
                        _authState.value = AuthState.Authenticated
                    } else {
                        _authState.value =
                            AuthState.Error(task.exception?.message ?: "Something went wrong!")
                    }
                }
            }
    }

    fun signOut(){
        auth.signOut()
        _authState.value=AuthState.UnAuthenticated
    }

    fun resetAuthState() {
        _authState.value = null // Reset state so errors can be triggered again
    }


}

sealed class AuthState{
    object Authenticated:AuthState()
    object UnAuthenticated:AuthState()
    object Loading:AuthState()
    object Checking:AuthState()
    data class Error(val message:String):AuthState()
}