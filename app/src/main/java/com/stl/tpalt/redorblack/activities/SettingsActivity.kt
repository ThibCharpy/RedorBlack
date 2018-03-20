package com.stl.tpalt.redorblack.activities

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.app.AppCompatActivity
import android.widget.ImageButton
import com.stl.tpalt.redorblack.R
import kotlinx.android.synthetic.main.activity_settings.*
import java.util.*

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
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                    setLanguage("fr")
                }
                val intent = Intent(this, WelcomeActivity::class.java)
                startActivity(intent)
            }

            val flagEn= findViewById<ImageButton>(R.id.flagEn)
            flagEn.setOnClickListener{
                _ ->
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                    setLanguage("en")
                }
                val intent = Intent(this, WelcomeActivity::class.java)
                startActivity(intent)
            }
        }
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
