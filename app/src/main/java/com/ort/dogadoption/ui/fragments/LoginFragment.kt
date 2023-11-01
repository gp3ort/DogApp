package com.ort.dogadoption.ui.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.findNavController
import com.ort.dogadoption.R
import com.ort.dogadoption.ui.views.MainActivity


class LoginFragment : Fragment() {

    lateinit var v: View
    lateinit var loginButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_login, container, false)

        val usernameInput: TextView = v.findViewById(R.id.emailInputId)
        val passwordInput: TextView = v.findViewById(R.id.passwordInputId)

        setNavGraphButton(usernameInput, passwordInput)

        return v;
    }

    private fun setNavGraphButton(usernmameInput: TextView, passwordInput: TextView) {
        val loginButton = v.findViewById<Button>(R.id.loginButtonId)

        loginButton.setOnClickListener{

            val email: String = usernmameInput.text.toString()
            val password: String = passwordInput.text.toString()

            if(validateInputData(email, password)){
                val intent = Intent(activity, MainActivity::class.java)
                intent.putExtra("user", email)
                startActivity(intent)
                activity?.finish()
            }
        }
    }

    private fun validateInputData(username: String, password: String): Boolean{
        if (username.isEmpty()) {
            displayToast("Ingrese usuario, por favor!")
            return false
        }

        if (password.isEmpty()) {
            displayToast("Ingrese una contraseña, por favor!")
            return false
        }

        return true
    }

    private fun displayToast(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

}