package com.stl.tpalt.redorblack.activities

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import com.stl.tpalt.redorblack.R

//can go to RulesActivity or PlayerSelectionActivity from here
class WelcomeActivity : AppCompatActivity() {

    private val firstLaunchPref = "firstLaunchPref"

    private lateinit var buttonSettings : ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        val isTablet = resources.getBoolean(R.bool.isTablet)

        if (isTablet) {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE
        }


        val buttonRules= findViewById<Button>(R.id.button_regles)
        val buttonStart= findViewById<Button>(R.id.button_start)
        val prefs = PreferenceManager.getDefaultSharedPreferences(this)

        buttonStart.setOnClickListener(
                {
                    val intent = Intent(this, PlayerSelectionActivity::class.java)
                    startActivity(intent)
                })
        buttonRules.setOnClickListener(
                {
                    val editor = prefs.edit()
                    editor.putBoolean(firstLaunchPref, true)
                    editor.apply()
                    val intent = Intent(this, RulesActivity::class.java)
                    startActivity(intent)
                })

        buttonSettings = findViewById(R.id.settings)
        buttonSettings.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }

    }

    @Suppress("UNUSED_PARAMETER")
    fun goToSettings(v : View){
        val intent = Intent(this, SettingsActivity::class.java)
        startActivity(intent)
        finish()
    }

}

