package com.example.techmecook.ui.register

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.techmecook.R
import com.example.techmecook.databinding.FragmentRegisterBinding
import com.example.techmecook.model.result.Error
import com.example.techmecook.model.result.NetworkError
import com.example.techmecook.model.result.Success
import com.example.techmecook.util.invalidateError
import com.example.techmecook.util.showShortText

class RegisterFragment : Fragment() {
    private val viewModel by viewModels<RegisterViewModel>()
    private lateinit var binding: FragmentRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        invalidateInput()

        binding.buttonRegister.setOnClickListener {
            viewModel.tryRegister()
        }

        registerResponse()
    }

    private fun registerResponse() {
        viewModel.registerResponse.observe(viewLifecycleOwner) {
            when (it) {
                is Error -> requireContext().showShortText(getString(R.string.error_user_exist))
                NetworkError -> requireContext().showShortText(getString(R.string.error_network_connection))
                is Success -> {
                    requireContext().showShortText("Registration successful!r")
                    findNavController().navigate(
                        RegisterFragmentDirections.actionRegisterFragmentToFirstFragment()
                    )
                }
            }
        }
    }

    private fun invalidateInput() {
        binding.name.invalidateError(binding.textFieldUserName)
        binding.mail.invalidateError(binding.textFieldMail)
        binding.password.invalidateError(binding.textFieldPass)
        binding.passwordRepeat.invalidateError(binding.textFieldPassRepeat)
    }

}