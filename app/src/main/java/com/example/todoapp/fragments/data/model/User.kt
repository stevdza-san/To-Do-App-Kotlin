package com.example.todoapp.fragments.data.model

import android.provider.ContactsContract

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
data class User(
    val userId: String,
    val username: String,
    val email: String
)