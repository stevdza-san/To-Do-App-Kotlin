package com.example.todoapp.fragments.ui.sign_up

data class SignUpUiState(
    val username: String = "",
    val email: String = "",
    val password: String = "",
    val repeatPassword: String = ""
)
