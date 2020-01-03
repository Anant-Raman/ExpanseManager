package com.example.expansemanager.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.expansemanager.R
import com.example.expansemanager.ui.fragments.AnalyticsFragment
import com.example.expansemanager.ui.fragments.HomeFragment
import com.example.expansemanager.ui.fragments.PlanningFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        if (supportActionBar!= null)
            supportActionBar?.hide()

        val bottomNavigation: BottomNavigationView = findViewById(R.id.home_bottom_navigation)
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        if (savedInstanceState == null) {
            val fragment = HomeFragment()
            supportFragmentManager.beginTransaction().replace(R.id.container_main, fragment, fragment.javaClass.simpleName)
                .commit()
        }
        initViews()

    }

    private fun initViews() {

    }

    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_home -> {
                    val fragment =
                        HomeFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container_main, fragment, fragment.javaClass.simpleName)
                        .commit()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.menu_planning -> {
                    val fragment =
                        PlanningFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container_main, fragment, fragment.javaClass.simpleName)
                        .commit()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.menu_analytics -> {
                    val fragment =
                        AnalyticsFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container_main, fragment, fragment.javaClass.simpleName)
                        .commit()
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }
}
