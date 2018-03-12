package com.stl.tpalt.redorblack

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Button
import android.preference.PreferenceManager

class WelcomeActivity : AppCompatActivity() {

    private val firstLaunchPref = "firstLaunchPref"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        val buttonRules= findViewById<Button>(R.id.button_regles)
        val buttonStart= findViewById<Button>(R.id.button_start)
        val prefs = PreferenceManager.getDefaultSharedPreferences(this)

        val isFirstLaunch = prefs.getBoolean(firstLaunchPref, true)
//        if (!isFirstLaunch) {
//            val intent = Intent(this, PlayerSelection::class.java)
//            startActivity(intent)
//            finish()
//        }

        buttonStart.setOnClickListener(
                {
                    _ ->
                    val editor = prefs.edit()
                    editor.putBoolean(firstLaunchPref, false)
                    editor.apply()
                    val intent = Intent(this, PlayerSelection::class.java)
                    startActivity(intent)
                    finish()
                })
    }
}

