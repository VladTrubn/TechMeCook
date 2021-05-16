package com.example.techmecook.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.techmecook.BuildConfig
import com.example.techmecook.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.example.techmecook.repositories.*
import com.example.techmecook.model.*
import com.example.techmecook.model.recipe.RandomRecipeCollection
import com.example.techmecook.model.result.*
import com.example.techmecook.util.SharedPref
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
    }



    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_logout -> {
                SharedPref.writeApiToken(applicationContext, "API_TOKEN")
                Log.d("App", "token cleared")
                val navController = findNavController(R.id.nav_host_fragment)
                navController.navigateUp() // to clear previous navigation history
                val navOption =
                    NavOptions.Builder().setPopUpTo(R.id.recipeListFragment, true).build()
                navController.navigate(R.id.loginFragment, null, navOption)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}