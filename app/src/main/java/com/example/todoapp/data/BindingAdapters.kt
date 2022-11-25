package com.example.todoapp.data

import android.view.inputmethod.EditorInfo
import android.widget.TextView.OnEditorActionListener
import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout


@BindingAdapter("android:error")
fun setError(textInputLayout: TextInputLayout, error: String?) {

        textInputLayout.editText?.setOnEditorActionListener(OnEditorActionListener { v, actionId, event ->
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                        textInputLayout.error = error
                        textInputLayout.isErrorEnabled = error != null
                        return@OnEditorActionListener true
                }
                false
        })
}