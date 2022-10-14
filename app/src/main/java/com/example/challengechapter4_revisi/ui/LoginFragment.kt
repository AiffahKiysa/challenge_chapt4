package com.example.challengechapter4_revisi.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.challengechapter4_revisi.R
import com.example.challengechapter4_revisi.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    lateinit var binding : FragmentLoginBinding
    lateinit var  sharedPref : SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cekLogin()

        sharedPref = requireActivity().getSharedPreferences("registerData", Context.MODE_PRIVATE)
        var email = sharedPref.getString("email", null)
        var password = sharedPref.getString("password", null)

        binding.btnLogin.setOnClickListener{
            var _email = binding.txtEmail.text.toString()
            var _password = binding.txtPassword.text.toString()
            if(email == _email && password == _password){
                var addData = sharedPref.edit()
                addData.putString("_username", _email)
                addData.putString("_password", _password)
                addData.apply()
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
        var data = requireActivity().getSharedPreferences("registerData", Context.MODE_PRIVATE)
        var username = data.getString("_username", null)

        Handler(Looper.myLooper()!!).postDelayed({
            if(username != null)
                findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
        },1000)
    }

}