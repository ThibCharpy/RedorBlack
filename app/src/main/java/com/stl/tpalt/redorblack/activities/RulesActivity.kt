package com.stl.tpalt.redorblack.activities

import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import com.stl.tpalt.redorblack.R
import android.support.v4.view.ViewPager
import com.stl.tpalt.redorblack.utils.CustomPagerAdapter


//can go only to WelcomeActivity from here
class RulesActivity : AppCompatActivity() {

    private lateinit var prefs : SharedPreferences
    private val firstLaunchPref = "firstLaunchPref"

    private lateinit var viewPager: ViewPager
    private lateinit var pagerAdapter: CustomPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rules)

        viewPager = findViewById<View>(R.id.viewpager) as ViewPager
        pagerAdapter = CustomPagerAdapter(this,supportFragmentManager)

        viewPager.adapter = pagerAdapter
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageScrollStateChanged(state: Int) {
                println("HEre : "+state)
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                println("pos 1 : "+position)
                if (position == 3)
                    goToWelcome(null)
            }

            override fun onPageSelected(position: Int) {

            }

        })

        prefs = PreferenceManager.getDefaultSharedPreferences(this)
        val isFirstLaunch = prefs.getBoolean(firstLaunchPref, true)
        if (!isFirstLaunch) {
            val intent = Intent(this, WelcomeActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    @Suppress("UNUSED_PARAMETER")
    fun goToWelcome(v: View?){
        val intent = Intent(this, WelcomeActivity::class.java)
        startActivity(intent)
    }
}
