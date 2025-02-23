package com.example.fitnessapp.viewModel

import android.app.Activity
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.IntentSenderRequest
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnessapp.R
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val auth:FirebaseAuth,
    private val db:FirebaseFirestore,
    private val oneTapClient: SignInClient
) : ViewModel() {
    private val _authState = MutableStateFlow<AuthState?>(null)
    val authState: StateFlow<AuthState?> = _authState.asStateFlow()

    private val _userSignInData = MutableStateFlow<Map<String, Any>?>(null)
    val userSignInData: StateFlow<Map<String, Any>?> = _userSignInData.asStateFlow()



    private lateinit var signInRequest: BeginSignInRequest

    init {
        checkAuthStatus()
    }

    fun checkAuthStatus() {
        _authState.value = AuthState.Checking
        if (auth.currentUser == null) {
            _authState.value = AuthState.UnAuthenticated
        } else {
            _authState.value = AuthState.Authenticated
        }
    }

    fun login(email: String, password: String) {
        if (email.isEmpty() || password.isEmpty()) {
            _authState.value = AuthState.Error("Email and Password cannot be empty.")
            return
        }
        _authState.value = AuthState.Loading
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                viewModelScope.launch {
                    if (task.isSuccessful) {
                        val userSignInData= hashMapOf(
                            "email" to email,
                            "password" to password
                        )
                        _userSignInData.value=userSignInData
                        saveUserSignInData(userSignInData,"userSignInData")
                        _authState.value = AuthState.Authenticated
                    } else {
                        _authState.value = AuthState.Error(task.exception?.message ?: "Something went wrong!")
                    }
                }
            }
    }

    fun signUp(email: String, password: String) {
        if (email.isEmpty() || password.isEmpty()) {
            _authState.value = AuthState.Error("Email and Password cannot be empty.")
            return
        }
        _authState.value = AuthState.Loading
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                viewModelScope.launch {
                    if (task.isSuccessful) {
                        _authState.value = AuthState.Authenticated
                    } else {
                        _authState.value = AuthState.Error(task.exception?.message ?: "Something went wrong!")
                    }
                }
            }
    }

    fun signOut() {
        auth.signOut()
        _authState.value = AuthState.UnAuthenticated
    }

    fun resetAuthState() {
        _authState.value = null
    }

    fun googleSignIn(activity: Activity, launcher: ActivityResultLauncher<IntentSenderRequest>) {
        _authState.value=AuthState.Loading
        signInRequest = BeginSignInRequest.builder()
            .setGoogleIdTokenRequestOptions(
                BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    .setServerClientId(activity.getString(R.string.web_client_id))
                    .setFilterByAuthorizedAccounts(false)
                    .build()
            )
            .build()

        oneTapClient.beginSignIn(signInRequest)
            .addOnSuccessListener(activity) { result ->
                try {
                    val intentSenderRequest = IntentSenderRequest.Builder(result.pendingIntent).build()
                    launcher.launch(intentSenderRequest)
                } catch (e: Exception) {
                    _authState.value = AuthState.Error("Google Sign-In failed: ${e.message}")
                }
            }
            .addOnFailureListener {
                _authState.value = AuthState.Error(it.message ?: "Google Sign-In failed!")
            }
    }

    fun handleGoogleSignInResult(idToken: String?) {
        if (idToken != null) {
            val credential = GoogleAuthProvider.getCredential(idToken, null)
            auth.signInWithCredential(credential)
                .addOnCompleteListener { task ->
                    viewModelScope.launch {
                        if (task.isSuccessful) {
                            val user=auth.currentUser
                             val userSignInData= hashMapOf(
                                 "email" to (user?.email ?: ""),
                                 "photoUrl" to (user?.photoUrl?.toString() ?: "")
                            )
                            _userSignInData.value=userSignInData
                            saveUserSignInData(userSignInData,"userSignInData")
                            _authState.value = AuthState.Authenticated
                            Log.d("AuthViewModel", "Google Sign-In successful: ${auth.currentUser?.email}")
                        } else {
                            _authState.value = AuthState.Error("Google Sign-In failed: ${task.exception?.message}")
                            Log.e("AuthViewModel", "Google Sign-In failed", task.exception)
                        }
                    }
                }
        } else {
            _authState.value = AuthState.Error("Google Sign-In failed: No token received.")
        }
    }

    fun saveUserSignInData(userSignInData: Map<String,Any>,collection:String){
        val userId = auth.currentUser?.uid ?: return
        db.collection(collection).document(userId)
            .set(userSignInData)
            .addOnSuccessListener {
                Log.d("Firestore", "User data saved successfully in $collection!")
            }
            .addOnFailureListener { e ->
                Log.e("Firestore", "Error saving data in $collection", e)
            }
    }
}

sealed class AuthState {
    object Authenticated : AuthState()
    object UnAuthenticated : AuthState()
    object Loading : AuthState()
    object Checking : AuthState()
    data class Error(val message: String) : AuthState()
}
