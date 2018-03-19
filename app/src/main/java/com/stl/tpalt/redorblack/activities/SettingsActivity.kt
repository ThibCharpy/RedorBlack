package com.stl.tpalt.redorblack.activities

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
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

            val flagFr = flagFr
            flagFr.setOnClickListener {
                val locale = Locale("fr")
                val dm = resources.displayMetrics
                val config = resources.configuration
                config.setLocale(locale)
                resources.updateConfiguration(config, dm)
                super.onConfigurationChanged(config)
                val intent = Intent(this, WelcomeActivity::class.java)
                startActivity(intent)
            }
            val flagEn = flagEn
            flagEn.setOnClickListener {
                val locale = Locale("en")
                val dm = resources.displayMetrics
                val config = resources.configuration
                config.setLocale(locale)
                resources.updateConfiguration(config, dm)
                super.onConfigurationChanged(config)
                val intent = Intent(this, WelcomeActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
