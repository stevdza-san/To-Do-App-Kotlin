package com.example.todoapp.data.firebase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import java.net.PasswordAuthentication

class AccountServiceImpl : AccountService {
    private var authentication = FirebaseAuth.getInstance()
    override fun register(
        userName: String,
        email: String,
        password: String,
        onResult: (Boolean) -> Unit
    ) {
        authentication.createUserWithEmailAndPassword(email, password).addOnCompleteListener {

            if (it.isComplete && it.isSuccessful) {
                authentication.currentUser?.updateProfile(
                    UserProfileChangeRequest
                        .Builder()
                        .setDisplayName(userName)
                        .build()
                )
                onResult(true)
            } else {
                onResult(false)
            }
        }
    }

    override fun login(email: String, password: String, onResult: (Boolean) -> Unit) {
        authentication.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            onResult(it.isComplete && it.isSuccessful)
        }
    }

    override fun getUserId(): String {
        return authentication.currentUser?.uid ?: ""
    }

    override fun getUserName(): String {
        return authentication.currentUser?.displayName ?: ""
    }

    override fun logOut(onResult: () -> Unit) {
        authentication.signOut()
        onResult()
    }
}