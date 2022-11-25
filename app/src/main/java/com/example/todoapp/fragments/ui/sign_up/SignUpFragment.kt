package com.example.todoapp.fragments.ui.sign_up

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
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

        handleEmailEditText()


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

    private fun handleEmailEditText() {
        val email = binding.emailEditText

        binding.emailEditText.onTextChanged { viewModel.onEmailChanged(it) }
            .also {
                email.setOnEditorActionListener(TextView.OnEditorActionListener { _, actionId, event ->
                    if (actionId == EditorInfo.IME_ACTION_NEXT || event.keyCode == KeyEvent.KEYCODE_ENTER) {
                        if (email.text.isNotBlank() && viewModel.emailf.value == true) {
                            binding.passwordEditText.requestFocus()
                        } else {
                            viewModel.showEmailError()
                        }
                        return@OnEditorActionListener true
                    }
                    false
                })
            }

    }

    private fun showBottomSheetDialog() {
        val dialog = BottomSheetDialog(requireContext())
        dialog.setContentView(R.layout.bottom_sheet_dialog_registration_success)
        dialog.show()
    }
}