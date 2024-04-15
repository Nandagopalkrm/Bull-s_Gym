package com.gym.bullsgym.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.gym.bullsgym.R
import com.gym.bullsgym.databinding.ActivityLoginBinding
import com.gym.bullsgym.databinding.ActivityUserCreationBinding
import com.gym.bullsgym.global.DB
import com.gym.bullsgym.manager.SessionManager

class UserCreationActivity : AppCompatActivity() {

    var db: DB? = null
    var session: SessionManager? = null
    private lateinit var binding: ActivityUserCreationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserCreationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.statusBarColor = ContextCompat.getColor(this, R.color.black)

        binding.newUserBtn.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
         binding.ExistingUserBtn.setOnClickListener {
             val intent = Intent(this, ExistingUserActivity::class.java)
             startActivity(intent)
         }

    }
}