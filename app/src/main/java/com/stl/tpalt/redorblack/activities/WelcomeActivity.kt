package com.stl.tpalt.redorblack.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.os.Build
import android.widget.Button
import android.preference.PreferenceManager
import android.support.annotation.RequiresApi
import android.widget.ImageButton
import com.stl.tpalt.redorblack.R
import java.util.*

//can go to RulesActivity or PlayerSelectionActivity from here
class WelcomeActivity : AppCompatActivity() {

    private val firstLaunchPref = "firstLaunchPref"

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        val buttonRules= findViewById<Button>(R.id.button_regles)
        val buttonStart= findViewById<Button>(R.id.button_start)
        val prefs = PreferenceManager.getDefaultSharedPreferences(this)

        buttonStart.setOnClickListener(
                {
                    _ ->
                    val intent = Intent(this, PlayerSelectionActivity::class.java)
                    startActivity(intent)
                    finish()
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

        val flagFr= findViewById<ImageButton>(R.id.flagFr)
        flagFr.setOnClickListener{v ->
            val locale = Locale("fr")
            val dm = resources.displayMetrics
            val config = resources.configuration
            config.setLocale(locale)
            resources.updateConfiguration(config,dm)
            super.onConfigurationChanged(config)
            val intent = Intent(this, WelcomeActivity::class.java)
            startActivity(intent)
        }
        val flagEn= findViewById<ImageButton>(R.id.flagEn)
        flagEn.setOnClickListener{v ->
            val locale = Locale("en")
            val dm = resources.displayMetrics
            val config = resources.configuration
            config.setLocale(locale)
            resources.updateConfiguration(config,dm)
            super.onConfigurationChanged(config)
            val intent = Intent(this, WelcomeActivity::class.java)
            startActivity(intent)
        }
    }
}

