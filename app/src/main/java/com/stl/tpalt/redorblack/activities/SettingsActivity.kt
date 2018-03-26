package com.stl.tpalt.redorblack.activities

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import com.stl.tpalt.redorblack.R
import kotlinx.android.synthetic.main.activity_settings.*
import kotlinx.android.synthetic.main.settings_button.*
import java.util.*
import kotlin.system.exitProcess

class SettingsActivity : AppCompatActivity() {



    @Suppress("DEPRECATION")
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR1) {
            val flagFr= findViewById<ImageButton>(R.id.flagFr)
            flagFr.setOnClickListener{
                _ ->
                setLanguage("fr")
                reloadActivity()
            }

            val flagEn= findViewById<ImageButton>(R.id.flagEn)
            flagEn.setOnClickListener{
                _ ->
                setLanguage("en")
                reloadActivity()
            }
        }else{
            flagFr.visibility = View.GONE
            flagEn.visibility = View.GONE
        }

        closesettings.setOnClickListener { _ ->
            val intent = Intent(this, WelcomeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }


    fun reloadActivity () {
        val intent = Intent(this, SettingsActivity::class.java)
        startActivity(intent)
        finish()
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    fun setLanguage(language: String) {
        val locale = Locale(language)
        val dm = resources.displayMetrics
        val config = resources.configuration
        config.setLocale(locale)
        resources.updateConfiguration(config,dm)
        super.onConfigurationChanged(config)
    }
}
