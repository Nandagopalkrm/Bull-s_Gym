package com.gym.bullsgym

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.core.content.ContextCompat
import com.gym.bullsgym.activity.HomeActivity
import com.gym.bullsgym.activity.LoginActivity
import com.gym.bullsgym.global.DB
import com.gym.bullsgym.manager.SessionManager

class SplashActivity : AppCompatActivity() {

    private var mDelayHandler: Handler? = null
    private val splash_delay: Long = 3000
    var db: DB? = null
    var session: SessionManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        window.statusBarColor = ContextCompat.getColor(this, R.color.black)

        db = DB(this)
        session = SessionManager(this)

        insertAdminData()
        mDelayHandler = Handler()
        mDelayHandler?.postDelayed(mRunnable, splash_delay)

    }

    private val mRunnable: Runnable = Runnable {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
        if (session?.isLoggedIn == true) {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun insertAdminData() {
        try {

            val sqlCheck = "SELECT * FROM ADMIN"
            db?.fireQuery(sqlCheck)?.use {
                if (it.count > 0) {
                    Log.d("SplashActivity", "data available")
                } else {
                    val sqlQuery =
                        "INSERT OR REPLACE INTO ADMIN(ID,USER_NAME,PASSWORD,MOBILE)VALUES('1','Admin','123456','9999999999')"
                    db?.executeQuery(sqlQuery)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mDelayHandler?.removeCallbacks(mRunnable)
    }
}