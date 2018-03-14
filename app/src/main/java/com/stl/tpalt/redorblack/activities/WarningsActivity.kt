package com.stl.tpalt.redorblack.activities

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.stl.tpalt.redorblack.R

/**
 * Created by thibault on 14/03/18.
 */
class WarningsActivity: AppCompatActivity() {
    private lateinit var prefs : SharedPreferences
    private val firstLaunchPref = "firstLaunchPref"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_warnings)

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