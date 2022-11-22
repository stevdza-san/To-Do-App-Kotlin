package com.example.todoapp.fragments.ui.sign_up

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todoapp.data.common.isUsernameValid
import com.example.todoapp.data.common.isValidEmail
import com.example.todoapp.data.common.isValidPassword
import com.example.todoapp.data.common.passwordMatches
import com.example.todoapp.data.firebase.AccountServiceImpl

class SignUpViewModel(private val accountService: AccountServiceImpl) : ViewModel() {

    private var uiState = MutableLiveData<SignUpUiState>()

    fun stateOnceAndStream(): LiveData<SignUpUiState> {
        return uiState
    }

    private val username get() = uiState.value?.email
    private val email get() = uiState.value?.email
    private val password get() = uiState.value?.password
    private val repeatPassword get() = uiState.value?.repeatPassword

    fun userNameChanged(newValue: String) {
        uiState.value?.let { currentUiState ->
            uiState.value = currentUiState.copy(username = newValue)
        }
    }

    fun onEmailChanged(newValue: String) {
        uiState.value?.let { currentUiState ->
            uiState.value = currentUiState.copy(email = newValue)
        }
    }

    fun onPasswordChanged(newValue: String) {
        uiState.value?.let { currentUiState ->
            uiState.value = currentUiState.copy(password = newValue)
        }
    }

    fun onRepeatPasswordChange(newValue: String) {
        uiState.value?.let { currentUiState ->
            uiState.value = currentUiState.copy(repeatPassword = newValue)
        }
    }

    fun onSignUpClick():String {
        var msg: String = ""

        if(username?.isUsernameValid() != true) {
            msg = "Please insert a valid username."
        }

        if (email?.isValidEmail() != true) {
            msg = "Please insert a valid email."
        }

        if (password?.isValidPassword() != true) {
            msg = "Your password should have at least six digits and include one digit, one lower case letter and one upper case letter."
        }

        if (password?.passwordMatches(repeatPassword.toString()) != true) {
            msg = "Passwords do not match."
        }
        return msg
    }

}