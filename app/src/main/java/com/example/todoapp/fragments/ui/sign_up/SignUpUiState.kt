package com.example.todoapp.fragments.ui.sign_up

import android.text.Editable

data class SignUpUiState(
    var username: String,
    val email: String,
    val password: String,
    val repeatPassword: String
)
