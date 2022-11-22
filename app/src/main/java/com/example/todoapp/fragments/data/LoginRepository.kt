package com.example.todoapp.fragments.data

import com.example.todoapp.fragments.data.model.User

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

//class LoginRepository(val dataSource: LoginDataSource) {
//
//    // in-memory cache of the loggedInUser object
//    var user: User? = null
//        private set
//
//    val isLoggedIn: Boolean
//        get() = user != null
//
//    init {
//        // If user credentials will be cached in local storage, it is recommended it be encrypted
//        // @see https://developer.android.com/training/articles/keystore
//        user = null
//    }
//
//    fun logout() {
//        user = null
//        dataSource.logout()
//    }
//
//    fun login(username: String, password: String): Result<User> {
//        // handle login
//        val result = dataSource.signUp(username, password)
//
//        if (result is Result.Success) {
//            setLoggedInUser(result.data)
//        }
//
//        return result
//    }
//
//    private fun setLoggedInUser(loggedInUser: User) {
//        this.user = loggedInUser
//        // If user credentials will be cached in local storage, it is recommended it be encrypted
//        // @see https://developer.android.com/training/articles/keystore
//    }
//}