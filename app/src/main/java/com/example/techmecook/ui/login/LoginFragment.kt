package com.example.techmecook.ui.login

import android.app.Application
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.techmecook.R
import com.example.techmecook.databinding.FragmentLoginBinding
import com.example.techmecook.model.result.Error
import com.example.techmecook.model.result.NetworkError
import com.example.techmecook.model.result.Success
import com.example.techmecook.util.*

class LoginFragment : Fragment() {
    private val viewModel by viewModels<LoginViewModel>()
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?,
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (requireActivity().getToken() != "API_TOKEN")
            navigateToMain();

        invalidateInput()

        binding.buttonLogin.setOnClickListener {
            requireActivity().hideSoftKeyboard(requireActivity())
            viewModel.tryLogin()
        }

        binding.buttonRegister.setOnClickListener {
            navigateToRegister()
        }

        loginResponse()
    }

    private fun navigateToRegister() {
        findNavController().navigate(
                LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
        )
    }

    private fun navigateToMain() {
        findNavController().navigate(
                LoginFragmentDirections.actionLoginFragmentToRecipeListFragment()
        )
    }

    private fun loginResponse() {
        viewModel.loginResponse.observe(viewLifecycleOwner) {
            when (it) {
                is Error -> requireContext().showShortText(getString(R.string.error_user_does_not_exist))
                NetworkError -> requireContext().showShortText(getString(R.string.error_network_connection))
                is Success -> {
                    requireContext().showShortText("Success login")
                    navigateToMain()
                }

            }
        }
    }

    private fun invalidateInput() {
        binding.mail.invalidateError(binding.textFieldMail)
        binding.password.invalidateError(binding.textFieldPass)
    }


}

