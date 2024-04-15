package com.gym.bullsgym.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.gym.bullsgym.R
import com.gym.bullsgym.databinding.ActivityLoginBinding
import com.gym.bullsgym.fragments.HomeFragment
import com.gym.bullsgym.global.DB
import com.gym.bullsgym.manager.SessionManager

class LoginActivity : AppCompatActivity() {

    var db: DB? = null
    var session: SessionManager? = null
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.statusBarColor = ContextCompat.getColor(this, R.color.black)

        db = DB(this)
        session = SessionManager(this)

        binding.loginBtn.setOnClickListener {
            if (validateLogin()) {
                getLogin()
            }
        }

        binding.forgetPsdTv.setOnClickListener {

        }
    }

    private fun getLogin() {
        try {
            val sqlQuery = "SELECT * FROM ADMIN WHERE USER_NAME='" + binding.etName?.text.toString()
                .trim() + "' AND PASSWORD ='" + binding.etPassword?.text.toString()
                .trim() + "' AND ID='1'"
            db?.fireQuery(sqlQuery)?.use {
                if (it.count > 0) {
                    session?.setLogin(true)
                    Toast.makeText(this, "Successfully LoggedIn", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, UserCreationActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    session?.setLogin(false)
                    Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun validateLogin(): Boolean {
        if (binding.etName?.text.toString().trim().isEmpty()) {
            Toast.makeText(this, "Enter User Name", Toast.LENGTH_SHORT).show()
            return false
        } else if (binding.etPassword.text.toString().trim().isEmpty()) {
            Toast.makeText(this, "Enter Password", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }
}