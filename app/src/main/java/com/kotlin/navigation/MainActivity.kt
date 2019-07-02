package com.kotlin.navigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.kotlin.navigation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        drawerLayout = binding.drawerLayout
        navController = findNavController(R.id.myNavHostFragment)

        // SOS: this along w onSupportNavigateUp makes the "Up Button" behave exactly how the phone's
        // Back button does, in our case that's according to the navigation.xml
        // To add the 'burger' icon for the drawer, I added a 3rd arg here, the drawerLayout
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)

        // SOS: now clicking on items in the drawer menu leads to fragments, just like w regular menu
        NavigationUI.setupWithNavController(binding.navView, navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        // SOS: this also changed after adding the DrawerLayout. See codelab for more details
        return NavigationUI.navigateUp(navController, drawerLayout)
    }
}
