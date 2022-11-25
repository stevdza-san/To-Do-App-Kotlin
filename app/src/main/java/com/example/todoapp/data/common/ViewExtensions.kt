package com.example.todoapp.data.common

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

inline fun EditText.onTextChanged(crossinline onTextChangeHandler: (String) -> Unit) {
    addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) = Unit
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit

        override fun onTextChanged(input: CharSequence?, start: Int, before: Int, count: Int) {
            onTextChangeHandler(input?.toString() ?: "")
        }
    })
}