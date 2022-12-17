package com.example.challengechapter4_revisi.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.challengechapter4_revisi.R
import com.example.challengechapter4_revisi.data.user.DataUserManager
import com.example.challengechapter4_revisi.databinding.FragmentLoginBinding
import com.example.challengechapter4_revisi.ui.viewmodel.LoginViewModel

class LoginFragment : Fragment() {
    lateinit var binding : FragmentLoginBinding
    private lateinit var viewModel: LoginViewModel
    private lateinit var pref: DataUserManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        pref = DataUserManager(requireContext())
        viewModel = ViewModelProvider(this, ViewModelFactory(pref))[LoginViewModel::class.java]
        binding = FragmentLoginBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cekLogin()
        var email = ""
        var password = ""

        binding.btnLogin.setOnClickListener{
            viewModel.getEmail().observe(viewLifecycleOwner){
                email = it.toString()
            }
            viewModel.getPassword().observe(viewLifecycleOwner){
                password = it.toString()
            }
            val _email = binding.txtEmail.text.toString()
            val _password = binding.txtPassword.text.toString()

            if (email == _email && password == _password){
                viewModel.setIsLogin(true)
                findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
            }
            else {
                Toast.makeText(
                    requireContext(),
                    "The username or password is incorrect!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        binding.blmPunyaAkun.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }

    private fun cekLogin() {
        viewModel.getIsLogin().observe(viewLifecycleOwner){
            Handler(Looper.myLooper()!!).postDelayed({
                if(it == true)
                    findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
            },1000)
        }
    }

}