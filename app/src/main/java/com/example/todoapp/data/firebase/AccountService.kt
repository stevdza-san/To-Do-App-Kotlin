package com.example.todoapp.data.firebase

interface AccountService {
    fun register(userName: String, email: String, password: String, onResult: (Boolean) -> Unit)
    fun login(email: String, password: String, onResult: (Boolean) -> Unit)
    fun getUserId(): String
    fun getUserName(): String
    fun logOut(onResult: () -> Unit)
}