package com.example.todoapp.fragments.ui.sign_up

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.todoapp.R
import com.example.todoapp.data.common.onTextChanged
import com.example.todoapp.databinding.FragmentSignUpBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

class SignUpFragment : Fragment() {

    private lateinit var binding: FragmentSignUpBinding

    private val viewModel: SignUpViewModel by activityViewModels {
        SignUpViewModelFactory()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentSignUpBinding>(
            inflater,
            R.layout.fragment_sign_up,
            container,
            false
        )
        binding.lifecycleOwner = this
        binding.viewmodel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        handleValidationFields()


        //onTextChanged { viewModel.onEmailChanged(it) }
//        binding.passwordEditText.requestFocus()
//        binding.passwordEditText.isCursorVisible = true

//        viewModel.getStateFirstName()
//            .observe(viewLifecycleOwner) {
//                binding.registerTitleTextView.text = """Welcome, ${it.name.toString()}!"""
//            }
//        binding.firstNameEditText.onTextChanged { viewModel.upDateFirstName(it) }
//
//        viewModel.showRegistrationSuccessDialog.observe(viewLifecycleOwner) { showRegistrationSuccessDialog ->
//            if (showRegistrationSuccessDialog) {
//                showBottomSheetDialog()
//            }
//        }
    }

    private fun handleValidationFields() {
        val emailEditText = binding.emailEditText
        val passwordEditText = binding.passwordEditText
        val repeatPasswordEditText = binding.repeatPasswordEditText

        handleEmailField(emailEditText)

        handlePasswordField(emailEditText, passwordEditText, repeatPasswordEditText)

        handleRepeatPasswordEditText(emailEditText, passwordEditText, repeatPasswordEditText)


    }

    private fun handleRepeatPasswordEditText(
        emailEditText: EditText,
        passwordEditText: EditText,
        repeatPasswordEditText: EditText
    ) {
        repeatPasswordEditText.setOnFocusChangeListener { view, _ ->

            if (view.hasFocus()) {

                if (emailEditText.text.isBlank() || viewModel.emailIsValid.value == false) {

                    changeFocusToAnotherView(view, emailEditText)

                    sendEmailErrorMessage()

                } else if (passwordEditText.text.isBlank() || viewModel.passwordIsValid.value == false) {

                    changeFocusToAnotherView(view, passwordEditText)

                    viewModel.showPasswordError()
                } else if (viewModel.emailIsValid.value == false && viewModel.passwordIsValid.value == false) {

                    sendEmailErrorMessage()
                    changeFocusToAnotherView(view, emailEditText)

                } else {

                    passwordEditText.setOnEditorActionListener(TextView.OnEditorActionListener { _, actionId, event ->
                        if (actionId == EditorInfo.IME_ACTION_NEXT || event.keyCode == KeyEvent.KEYCODE_ENTER) {
                            if (passwordEditText.text.isNotBlank() && viewModel.passwordIsValid.value == true) {
                                repeatPasswordEditText.requestFocus()
                            } else {
                                viewModel.showPasswordError()
                            }
                            return@OnEditorActionListener true
                        }
                        false
                    })

                }

            }
        }
    }

    private fun handlePasswordField(
        emailEditText: EditText,
        passwordEditText: EditText,
        repeatPasswordEditText: EditText
    ) {
        passwordEditText.setOnFocusChangeListener { view, _ ->
            if (view.hasFocus()) {
                if (emailEditText.text.isBlank() || viewModel.emailIsValid.value == false) {
                    changeFocusToAnotherView(view, emailEditText)
                    sendEmailErrorMessage()
                } else {
                    passwordEditText.setOnEditorActionListener(TextView.OnEditorActionListener { _, actionId, event ->
                        if (actionId == EditorInfo.IME_ACTION_NEXT || event.keyCode == KeyEvent.KEYCODE_ENTER) {
                            if (passwordEditText.text.isNotBlank() && viewModel.passwordIsValid.value == true) {
                                repeatPasswordEditText.requestFocus()
                            } else {
                                viewModel.showPasswordError()
                            }
                            return@OnEditorActionListener true
                        }
                        false
                    })
                }
            }
        }
    }

    private fun changeFocusToAnotherView(view: View, anotherView: EditText) {
        view.clearFocus()
        anotherView.requestFocus()
    }

    private fun handleEmailField(emailEditText: EditText) {
        emailEditText.onTextChanged { viewModel.onEmailChanged(it) }
            .also {
                emailEditText.setOnEditorActionListener(TextView.OnEditorActionListener { _, actionId, event ->
                    if (actionId == EditorInfo.IME_ACTION_NEXT || event.keyCode == KeyEvent.KEYCODE_ENTER) {
                        if (emailEditText.text.isNotBlank() && viewModel.emailIsValid.value == true) {
                            binding.passwordEditText.requestFocus()
                        } else {
                            sendEmailErrorMessage()
                        }
                        return@OnEditorActionListener true
                    }
                    false
                })
            }
    }

    private fun sendEmailErrorMessage() {
        viewModel.showEmailError()
    }

    private fun showBottomSheetDialog() {
        val dialog = BottomSheetDialog(requireContext())
        dialog.setContentView(R.layout.bottom_sheet_dialog_registration_success)
        dialog.show()
    }
}