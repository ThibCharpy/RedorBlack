package com.stl.tpalt.redorblack.activities

import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import com.stl.tpalt.redorblack.R

//can go only to WelcomeActivity from here
class RulesActivity : AppCompatActivity() {

    private lateinit var prefs : SharedPreferences
    private val firstLaunchPref = "firstLaunchPref"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rules)

        prefs= PreferenceManager.getDefaultSharedPreferences(this)

        val isFirstLaunch = prefs.getBoolean(firstLaunchPref, true)
        if (!isFirstLaunch) {
            val intent = Intent(this, WelcomeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    fun goToWelcome(v : View){
        val editor = prefs.edit()
        editor.putBoolean(firstLaunchPref, false)
        editor.apply()
        val intent = Intent(this, WelcomeActivity::class.java)
        startActivity(intent)
    }
}
