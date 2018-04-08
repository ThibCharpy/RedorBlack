package com.stl.tpalt.redorblack.activities

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.ImageButton
import com.stl.tpalt.redorblack.R
import com.stl.tpalt.redorblack.model.RedOrBlackApp
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
                setLanguage("fr")
                reloadActivity()
            }

            val flagEn= findViewById<ImageButton>(R.id.flagEn)
            flagEn.setOnClickListener{
                setLanguage("en")
                reloadActivity()
            }
        }else{
            flagFr.visibility = View.GONE
            flagEn.visibility = View.GONE
        }


        closesettings.setOnClickListener{
            val intent = Intent(this, WelcomeActivity::class.java)
            startActivity(intent)
            finish()
        }

        settings_taken1.setText(RedOrBlackApp.rules.phase1sipsdrunk.toString())
        settings_taken2.setText(RedOrBlackApp.rules.phase2sipsdrunk.toString())
        settings_taken3.setText(RedOrBlackApp.rules.phase3sipsdrunk.toString())
        settings_taken4.setText(RedOrBlackApp.rules.phase4sipsdrunk.toString())
        settings_taken5.setText(RedOrBlackApp.rules.phase5sipsdrunk.toString())
        settings_given1.setText(RedOrBlackApp.rules.phase1sipsgiven.toString())
        settings_given2.setText(RedOrBlackApp.rules.phase2sipsgiven.toString())
        settings_given3.setText(RedOrBlackApp.rules.phase3sipsgiven.toString())
        settings_given4.setText(RedOrBlackApp.rules.phase4sipsgiven.toString())
        settings_given5.setText(RedOrBlackApp.rules.phase5sipsgiven.toString())

        level_soft.setOnClickListener {
            RedOrBlackApp.rules.phase1sipsdrunk=1
            RedOrBlackApp.rules.phase2sipsdrunk=1
            RedOrBlackApp.rules.phase3sipsdrunk=1
            RedOrBlackApp.rules.phase4sipsdrunk=1
            RedOrBlackApp.rules.phase5sipsdrunk=1
            RedOrBlackApp.rules.phase1sipsgiven=0
            RedOrBlackApp.rules.phase2sipsgiven=0
            RedOrBlackApp.rules.phase3sipsgiven=0
            RedOrBlackApp.rules.phase4sipsgiven=0
            RedOrBlackApp.rules.phase5sipsgiven=0
            settings_taken1.setText(RedOrBlackApp.rules.phase1sipsdrunk.toString())
            settings_taken2.setText(RedOrBlackApp.rules.phase2sipsdrunk.toString())
            settings_taken3.setText(RedOrBlackApp.rules.phase3sipsdrunk.toString())
            settings_taken4.setText(RedOrBlackApp.rules.phase4sipsdrunk.toString())
            settings_taken5.setText(RedOrBlackApp.rules.phase5sipsdrunk.toString())
            settings_given1.setText(RedOrBlackApp.rules.phase1sipsgiven.toString())
            settings_given2.setText(RedOrBlackApp.rules.phase2sipsgiven.toString())
            settings_given3.setText(RedOrBlackApp.rules.phase3sipsgiven.toString())
            settings_given4.setText(RedOrBlackApp.rules.phase4sipsgiven.toString())
            settings_given5.setText(RedOrBlackApp.rules.phase5sipsgiven.toString())
        }
        level_normal.setOnClickListener {
            RedOrBlackApp.rules.phase1sipsdrunk=1
            RedOrBlackApp.rules.phase2sipsdrunk=2
            RedOrBlackApp.rules.phase3sipsdrunk=3
            RedOrBlackApp.rules.phase4sipsdrunk=4
            RedOrBlackApp.rules.phase5sipsdrunk=5
            RedOrBlackApp.rules.phase1sipsgiven=1
            RedOrBlackApp.rules.phase2sipsgiven=1
            RedOrBlackApp.rules.phase3sipsgiven=1
            RedOrBlackApp.rules.phase4sipsgiven=1
            RedOrBlackApp.rules.phase5sipsgiven=1
            settings_taken1.setText(RedOrBlackApp.rules.phase1sipsdrunk.toString())
            settings_taken2.setText(RedOrBlackApp.rules.phase2sipsdrunk.toString())
            settings_taken3.setText(RedOrBlackApp.rules.phase3sipsdrunk.toString())
            settings_taken4.setText(RedOrBlackApp.rules.phase4sipsdrunk.toString())
            settings_taken5.setText(RedOrBlackApp.rules.phase5sipsdrunk.toString())
            settings_given1.setText(RedOrBlackApp.rules.phase1sipsgiven.toString())
            settings_given2.setText(RedOrBlackApp.rules.phase2sipsgiven.toString())
            settings_given3.setText(RedOrBlackApp.rules.phase3sipsgiven.toString())
            settings_given4.setText(RedOrBlackApp.rules.phase4sipsgiven.toString())
            settings_given5.setText(RedOrBlackApp.rules.phase5sipsgiven.toString())
        }
        level_hard.setOnClickListener {
            RedOrBlackApp.rules.phase1sipsdrunk=1
            RedOrBlackApp.rules.phase2sipsdrunk=2
            RedOrBlackApp.rules.phase3sipsdrunk=3
            RedOrBlackApp.rules.phase4sipsdrunk=4
            RedOrBlackApp.rules.phase5sipsdrunk=5
            RedOrBlackApp.rules.phase1sipsgiven=1
            RedOrBlackApp.rules.phase2sipsgiven=2
            RedOrBlackApp.rules.phase3sipsgiven=3
            RedOrBlackApp.rules.phase4sipsgiven=4
            RedOrBlackApp.rules.phase5sipsgiven=5
            settings_taken1.setText(RedOrBlackApp.rules.phase1sipsdrunk.toString())
            settings_taken2.setText(RedOrBlackApp.rules.phase2sipsdrunk.toString())
            settings_taken3.setText(RedOrBlackApp.rules.phase3sipsdrunk.toString())
            settings_taken4.setText(RedOrBlackApp.rules.phase4sipsdrunk.toString())
            settings_taken5.setText(RedOrBlackApp.rules.phase5sipsdrunk.toString())
            settings_given1.setText(RedOrBlackApp.rules.phase1sipsgiven.toString())
            settings_given2.setText(RedOrBlackApp.rules.phase2sipsgiven.toString())
            settings_given3.setText(RedOrBlackApp.rules.phase3sipsgiven.toString())
            settings_given4.setText(RedOrBlackApp.rules.phase4sipsgiven.toString())
            settings_given5.setText(RedOrBlackApp.rules.phase5sipsgiven.toString())
        }

        settings_taken1.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0.toString().toIntOrNull() != null)
                    RedOrBlackApp.rules.phase1sipsdrunk=p0.toString().toIntOrNull()!!
            }
        })
        settings_taken2.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0.toString().toIntOrNull() != null)
                    RedOrBlackApp.rules.phase2sipsdrunk=p0.toString().toIntOrNull()!!
            }
        })
        settings_taken3.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0.toString().toIntOrNull() != null)
                    RedOrBlackApp.rules.phase3sipsdrunk=p0.toString().toIntOrNull()!!
            }
        })
        settings_taken4.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0.toString().toIntOrNull() != null)
                    RedOrBlackApp.rules.phase4sipsdrunk=p0.toString().toIntOrNull()!!
            }
        })
        settings_taken5.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0.toString().toIntOrNull() != null)
                    RedOrBlackApp.rules.phase5sipsdrunk=p0.toString().toIntOrNull()!!
            }
        })
        settings_given1.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0.toString().toIntOrNull() != null)
                    RedOrBlackApp.rules.phase1sipsgiven=p0.toString().toIntOrNull()!!
            }
        })
        settings_given2.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0.toString().toIntOrNull() != null)
                    RedOrBlackApp.rules.phase2sipsgiven=p0.toString().toIntOrNull()!!
            }
        })
        settings_given3.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0.toString().toIntOrNull() != null)
                    RedOrBlackApp.rules.phase3sipsgiven=p0.toString().toIntOrNull()!!
            }
        })
        settings_given4.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0.toString().toIntOrNull() != null)
                    RedOrBlackApp.rules.phase4sipsgiven=p0.toString().toIntOrNull()!!
            }
        })
        settings_given5.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0.toString().toIntOrNull() != null)
                    RedOrBlackApp.rules.phase5sipsgiven=p0.toString().toIntOrNull()!!
            }
        })
    }


    private fun reloadActivity () {
        val intent = Intent(this, SettingsActivity::class.java)
        startActivity(intent)
        finish()
    }

    @Suppress("DEPRECATION")
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private fun setLanguage(language: String) {
        val locale = Locale(language)
        val dm = resources.displayMetrics
        val config = resources.configuration
        config.setLocale(locale)
        resources.updateConfiguration(config,dm)
        super.onConfigurationChanged(config)
    }


}
