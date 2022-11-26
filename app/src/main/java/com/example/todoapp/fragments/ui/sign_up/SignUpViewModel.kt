package com.example.todoapp.fragments.ui.sign_up

import android.util.Patterns
import android.widget.EditText
import androidx.lifecycle.*
import com.example.todoapp.data.common.isValidEmail
import com.example.todoapp.data.common.isValidPassword
import com.example.todoapp.data.firebase.AccountServiceImpl

class SignUpViewModel(private val accountService: AccountServiceImpl) : ViewModel() {

    val email = MutableLiveData<String>()
    val username = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val repeatPassword = MutableLiveData<String>()
    val emailError = MutableLiveData<String>()
    val passwordError = MutableLiveData<String>()
    val repeatPasswordError = MutableLiveData<String>()
    val etEmail = MutableLiveData<EditText>()


    private val emailValidator = EmailValidator()
    private val passwordValidator = PasswordValidator()
    private val repeatPasswordValidator = RepeatPasswordValidator()

    private val state = MutableLiveData(SignUpState("", "", ""))

    data class SignUpState(
        var email: String = "",
        var password: String = "",
        var repeatPassword: String = ""
    )

    fun onSignupButtonClick() {
//        passwordError.value = passwordValidator.validate(password.value)
//        passwordError.value = passwordValidator.validate(password.value)
//        repeatPasswordError.value =
//            repeatPasswordValidator.validate(password.value, repeatPassword.value)

    }

    private val _showRegistrationSuccessDialog = MutableLiveData(false)
    val showRegistrationSuccessDialog: LiveData<Boolean>
        get() = _showRegistrationSuccessDialog

    data class UiStateFirstName(
        var name: String?
    )

    private val uiStateFirstName: MutableLiveData<UiStateFirstName> by lazy {
        MutableLiveData<UiStateFirstName>(UiStateFirstName(name = ""))
    }

    fun getStateFirstName(): LiveData<UiStateFirstName> {
        return uiStateFirstName
    }

    fun upDateFirstName(newValue: String) {
        uiStateFirstName.value?.let {
            uiStateFirstName.value = it.copy(name = newValue)
        }
    }

    //
//    val showUsername: LiveData<Boolean> = Transformations.map(email, ::isValidEmail)
//    val username: LiveData<String> = Transformations.map(email, ::generateUsername)
//
//
    fun onRegisterClicked() {
//    viewModelScope.launch {
//        accountService.register(
//            username.value.toString(),
//            email.value.toString(),
//            password.value.toString(),
//        ) { isSuccessful ->
//            isSuccessful
//            // navController.
//        }
//    }
    }
//        _showRegistrationSuccessDialog.value = true
//        Log.d("user", getUserInformation())
//    }
//
//    private fun getUserInformation(): String {
//        return "User information:\n" +
//                "Last name: ${lastName.value}\n" +
//                "Email: ${email.value}\n" +
//                "Username: ${username.value}\n " +
//                "Password: ${password.value}\n " +
//                "RepeatPassword: ${repeatPassword.value}"
//    }
//
//    private fun onRegisterResult(isSuccessful: Boolean, name: String, email: String) {
//        if (isSuccessful) {
//            TODO("insert user to database")
//          //  createUser(name, email)
//           // view.onRegisterSuccess()
//        } else {
//            TODO()
//            return
//           // view.showSignUpError()
//        }
//    }
//
////    private fun createUser(name: String, email: String) {
////        accountService.
////        val id = authentication.getUserId()
////         database.createUser
////    }
//
//    private fun generateUsername(email: String): String {
//        val prefix = getEmailPrefix(email)
//        val suffix = prefix.length
//        return "$prefix$suffix".lowercase()
//    }
//
//    val enableRegistration: LiveData<Boolean> = MediatorLiveData<Boolean>().apply {
//        addSources(email, password) {
//            value = true
//        }
//    }
//
//    private fun isUserInformationValid(): Boolean {
//        return email.value.toString().isValidEmail() && password.value.toString().isValidPassword() && repeatPassword.value.toString().passwordMatches(password.value.toString())
//    }
//
//
    /**
     * Convenience method similar to [MediatorLiveData.addSource], except that it bulk adds sources
     * to this [MediatorLiveData] to listen to.
     */
    private fun <T> MediatorLiveData<T>.addSources(
        vararg sources: LiveData<out Any>,
        onChanged: Observer<Any>
    ) {
        sources.forEach { source ->
            addSource(source, onChanged)
        }
    }

    private val _emailIsValid = MutableLiveData(true)
    var emailIsValid: LiveData<Boolean> = _emailIsValid

    fun showEmailError() {
        emailError.value = emailValidator.validate(email.value)
    }

    private val _passwordIsValid = MutableLiveData(true)
    var passwordIsValid: LiveData<Boolean> = _passwordIsValid

    private val _repeatPasswordIsValid = MutableLiveData(true)
    var repeatPasswordIsValid: LiveData<Boolean> = _repeatPasswordIsValid

    fun showRepeatPasswordError() {
        repeatPasswordError.value =
            repeatPasswordValidator.validate(password.value, repeatPassword.value)
    }

    fun showPasswordError() {
        passwordError.value = passwordValidator.validate(password.value)
    }

    fun onPasswordChanged(newValue: String) {
        state.value?.let {
            state.value = it.copy(password = newValue)
        }
        _passwordIsValid.value = newValue.isValidPassword()
    }

    fun onEmailChanged(newValue: String) {

        state.value?.let {
            state.value = it.copy(email = newValue)
        }

        _emailIsValid.value = newValue.isValidEmail()
    }

    fun onRepeatPasswordChanged(newValue: String) {

    }
}

class EmailValidator {
    fun validate(email: String?) = when {
        email == null || email.isBlank() -> "Email cannot be empty"
        email.length < 5 -> "Email cannot be this short"
        !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> "Invalid email"
        else -> null
    }
}

class RepeatPasswordValidator {
    fun validate(password: String?, repeatPassword: String?) = when {
        repeatPassword != password -> "Password doesn't match"
        else -> null
    }
}

class PasswordValidator {
    fun validate(password: String?) = when {
        password == null -> "Password cannot be empty"
        password.isBlank() -> "Password cannot be empty"
        password.length < 8 -> "Password cannot be this short"
        !password.containsRequiredCharacters() -> "Password should contain one lowercase, one uppercase, one digit, one spec char"
        else -> null
    }

    private fun String.containsRequiredCharacters() = containsDigit()
            && containsLowerCase()
            && containsUpperCase()
            && containsSpecialCharacter()

    private fun String.containsDigit() = any { it.isDigit() }

    private fun String.containsLowerCase() = any { it.isLowerCase() }

    private fun String.containsUpperCase() = any { it.isUpperCase() }

    private fun String.containsSpecialCharacter() = any { it.isSpecialCharacter() }

    private fun Char.isSpecialCharacter() = !isDigit() && !isLetter()
}
