

/* This is a template








package com.stl.tpalt.redorblack

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.view.View
import android.R.attr.button
import android.os.CountDownTimer


import kotlinx.android.synthetic.main.activity_phase/*N*/.*

/*N*/
/*N+1*/

class Phase/*N*/ : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phase/*N*/)

        layout_phase/*N*/.isClickable=false
    }



    fun whichcardcliked(v : View)
    {
        //TODO : show card and reduce other opacity
        //TODO : show text
        makeBackGroundClickableAfterXsec(3)
    }

    fun goToPhase/*N+1*/(v : View)
    {
        val intent = Intent(this, Phase/*N+1*/::class.java)
        startActivity(intent)
        finish()
    }

    fun makeBackGroundClickableAfterXsec(sec : Long)
    {
        object : CountDownTimer(sec*1000, 1000) {
            override fun onTick(p0: Long) {}
            override fun onFinish() { layout_phase/*N*/.isClickable=true }
        }.start()
    }
}
*/














import android.support.v7.app.AppCompatActivity
class Phase/*N*/ : AppCompatActivity() {}