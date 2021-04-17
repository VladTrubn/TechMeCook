package com.example.techmecook.ui

import android.os.Bundle
import android.util.Log
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.techmecook.BuildConfig
import com.example.techmecook.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.example.techmecook.repositories.*
import com.example.techmecook.model.*
import com.example.techmecook.model.result.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            GlobalScope.launch {
                val repo = RecipeRepository()
                when (val result = repo.getRandomRecipes(BuildConfig.SPOONACULAR_KEY, 1, "")) {
                    is Error -> Log.e("ERROR IN ACTIVITY", "${result.exceptionInfo}")
                    is NetworkError ->  Log.e("ERROR IN ACTIVITY", "INTERNET ERROR") // Snackbar.make(view, "Network error", Snackbar.LENGTH_LONG).setAction("Action", null).show()
                    is Success ->  Log.e("SUCCESS IN ACTIVITY", result.value.title) //Snackbar.make(view, result.value.title, Snackbar.LENGTH_LONG).setAction("Action", null).show()
                }
            }
        }
    }



    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}