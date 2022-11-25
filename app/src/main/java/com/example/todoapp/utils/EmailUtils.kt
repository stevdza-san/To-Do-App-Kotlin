package com.example.todoapp.utils

import android.util.Patterns

/**
 * Returns the prefix (local-part) of an email address.
 * E.g: Calling this function with the argument `johndoe@domain.com` returns `johndoe`.
 */
fun getEmailPrefix(email: String): String {
    return email.substringBefore("@")
}

/**
 * Returns true if the email address is valid, false otherwise.
 * A valid email is composed of the local-part, the symbol `@` and a domain.
 */
fun isValidEmail(email: String?): Boolean {
    return !email.isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
}