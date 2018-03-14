package com.stl.tpalt.redorblack.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import com.stl.tpalt.redorblack.R
import kotlinx.android.synthetic.main.activity_phase2.*
import org.jetbrains.anko.toast

class Phase2Activy : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phase2)
    }



    fun lessClicked(v : View)
    {
        //TODO : show card and reduce other opacity
        //TODO : show text
        toast("less clicked")
        makeBackGroundClickableAfterXsec(3)
    }
    fun moreClicked(v : View)
    {
        //TODO : show card and reduce other opacity
        //TODO : show text
        toast("more clicked")
        makeBackGroundClickableAfterXsec(3)
    }
    fun equalsClicked(v : View)
    {
        //TODO : show card and reduce other opacity
        //TODO : show text
        toast("equals clicked")
        makeBackGroundClickableAfterXsec(3)
    }

    fun goToPhase3(v : View)
    {
        val intent = Intent(this, WelcomeActivity/*TODO Phase3*/::class.java)
        startActivity(intent)
        finish()
    }

    fun makeBackGroundClickableAfterXsec(sec : Long)
    {
        phase2_card_less.isEnabled=false
        phase2_card_equals.isEnabled=false
        phase2_card_more.isEnabled=false
        phase2_card_mycard.isEnabled=false
        object : CountDownTimer(sec*1000, 1000) {
            override fun onTick(p0: Long) {}
            override fun onFinish() { layout_phase2.isClickable=true }
        }.start()
    }
}

