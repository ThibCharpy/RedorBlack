package com.stl.tpalt.redorblack.activities

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.annotation.RequiresApi
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ViewAnimator
import com.stl.tpalt.redorblack.R
import org.jetbrains.anko.Orientation
import org.jetbrains.anko.configuration
import org.jetbrains.anko.displayMetrics
import java.lang.Boolean.getBoolean
import java.util.*

//can go to RulesActivity or PlayerSelectionActivity from here
class WelcomeActivity : AppCompatActivity() {

    private val firstLaunchPref = "firstLaunchPref"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val isTablet = resources.getBoolean(R.bool.isTablet)

        if (isTablet) {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE
        }

        setContentView(R.layout.activity_welcome)

        val buttonRules= findViewById<Button>(R.id.button_regles)
        val buttonStart= findViewById<Button>(R.id.button_start)
        val prefs = PreferenceManager.getDefaultSharedPreferences(this)

        buttonStart.setOnClickListener(
                {
                    _ ->
                    val intent = Intent(this, PlayerSelectionActivity::class.java)
                    startActivity(intent)
                })
        buttonRules.setOnClickListener(
                {
                    _ ->
                    val editor = prefs.edit()
                    editor.putBoolean(firstLaunchPref, true)
                    editor.apply()
                    val intent = Intent(this, RulesActivity::class.java)
                    startActivity(intent)
                })
    }

    fun goToSettings(v: View){
        val intent = Intent(this, SettingsActivity::class.java)
        startActivity(intent)
        finish()
    }

}

