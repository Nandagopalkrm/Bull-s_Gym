package com.gym.bullsgym.activity

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.navigation.NavigationView
import com.gym.bullsgym.R
import com.gym.bullsgym.databinding.ActivityHomeBinding
import com.gym.bullsgym.fragments.AddMemberFragment
import com.gym.bullsgym.fragments.ChangePasswordFragment
import com.gym.bullsgym.fragments.Fee_Pending_Fragment
import com.gym.bullsgym.fragments.HomeFragment
import com.gym.bullsgym.fragments.UpdateFeeFragment
import com.gym.bullsgym.global.DB
import com.gym.bullsgym.manager.SessionManager

class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val TAG = "HomeActivity"
    var session: SessionManager? = null
    var db: DB? = null


    private lateinit var drawer: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle

    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.statusBarColor = ContextCompat.getColor(this, R.color.black)

        db = DB(this)
        session = SessionManager(this)

        drawer = binding.drawerLayout
//        val toolbar = findViewById<Toolbar>(R.id.toolbar)
                supportActionBar?.setDisplayHomeAsUpEnabled(true)


        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        toggle = ActionBarDrawerToggle(
            this, drawer, binding.toolbar,
            R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer.addDrawerListener(toggle)
        toggle.syncState()
//

//        binding.navView.setNavigationItemSelectedListener(this)

        if (savedInstanceState == null) {
            replaceFragment(HomeFragment())
            navigationView.setCheckedItem(R.id.nav_home)
        }
    }


    private fun replaceFragment(fragment: Fragment) {
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.commit()
    }

//
//    override fun onPostCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
//        super.onPostCreate(savedInstanceState, persistentState)
//        toggle.syncState()
//    }
//
//    override fun onConfigurationChanged(newConfig: Configuration) {
//        super.onConfigurationChanged(newConfig)
//        toggle.onConfigurationChanged(newConfig)
//    }
//
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        if(item.itemId == R.id.logOutMenu){
            logout()
        }
        return super.onOptionsItemSelected(item)
    }
//
//    override fun onNavigationItemSelected(item: MenuItem): Boolean {
//        when (item.itemId) {
//            R.id.nav_home -> {
//                Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show()
//                if (drawer.isDrawerOpen(GravityCompat.START)) {
//                    drawer.closeDrawer(GravityCompat.START)
//                }
//            }
//
//            R.id.nav_add -> {
//                Toast.makeText(this, "Add Member", Toast.LENGTH_SHORT).show()
//                if (drawer.isDrawerOpen(GravityCompat.START)) {
//                    drawer.closeDrawer(GravityCompat.START)
//                }
//            }
//
//            R.id.nav_fee_pending -> {
//                Toast.makeText(this, "Fee Pending", Toast.LENGTH_SHORT).show()
//                if (drawer.isDrawerOpen(GravityCompat.START)) {
//                    drawer.closeDrawer(GravityCompat.START)
//                }
//            }
//
//            R.id.nav_update_fee -> {
//                Toast.makeText(this, "Update Fee", Toast.LENGTH_SHORT).show()
//                if (drawer.isDrawerOpen(GravityCompat.START)) {
//                    drawer.closeDrawer(GravityCompat.START)
//                }
//            }
//
//            R.id.nav_change_password -> {
//                Toast.makeText(this, "Change Password", Toast.LENGTH_SHORT).show()
//                if (drawer.isDrawerOpen(GravityCompat.START)) {
//                    drawer.closeDrawer(GravityCompat.START)
//                }
//            }
//
//            R.id.nav_logout -> {
//                Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show()
//                if (drawer.isDrawerOpen(GravityCompat.START)) {
//                    drawer.closeDrawer(GravityCompat.START)
//                }
//            }
//
//        }
//        return true
//    }

    override fun onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_home -> replaceFragment(HomeFragment())
            R.id.nav_add -> replaceFragment(AddMemberFragment())
            R.id.nav_fee_pending -> replaceFragment(Fee_Pending_Fragment())
            R.id.nav_update_fee -> replaceFragment(UpdateFeeFragment())
            R.id.nav_change_password -> replaceFragment(ChangePasswordFragment())
            R.id.nav_logout ->{

                logout()
                Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show()
            }

        }
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    private fun logout() {
        session?.setLogin(false)
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        try{
            val inflater = menuInflater
            inflater.inflate(R.menu.menu_main,menu)
        }catch (e: Exception){

        }

        return super.onCreateOptionsMenu(menu)
    }

}