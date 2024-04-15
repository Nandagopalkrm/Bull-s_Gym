package com.gym.bullsgym.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.gym.bullsgym.R
import com.gym.bullsgym.databinding.ActivityExistingUserBinding

class ExistingUserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityExistingUserBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityExistingUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.statusBarColor = ContextCompat.getColor(this, R.color.black)
    }
}