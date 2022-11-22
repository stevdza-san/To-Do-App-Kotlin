package com.example.todoapp.fragments.data

import com.example.todoapp.data.firebase.AccountService
import com.example.todoapp.fragments.data.model.User
import java.io.IOException

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
//class LoginDataSource {
//
//    private lateinit var accountService: AccountService
//
//    fun signUp(username: String, email: String, password: String, onResult:(Boolean)): Result<User> {
//        try {
//            // TODO: handle loggedInUser authentication
//            accountService.register(username,email,password)
//            val fakeUser = User(java.util.UUID.randomUUID().toString(), "Jane Doe")
//            return Result.Success(fakeUser)
//        } catch (e: Throwable) {
//            return Result.Error(IOException("Error logging in", e))
//        }
//    }
//
//    fun logout() {
//        // TODO: revoke authentication
//    }
//}