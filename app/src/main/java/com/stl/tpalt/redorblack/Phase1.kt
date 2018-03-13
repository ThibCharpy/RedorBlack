package com.stl.tpalt.redorblack

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.support.constraint.ConstraintLayout
import android.view.View
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_phase1.*
import org.jetbrains.anko.toast

class Phase1 : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phase1)
    }



    fun redClicked(v : View)
    {
        //TODO : show card and reduce other opacity
        toast("red clicked")
        makeBackGroundClickableAfterXsec(3)
    }

    fun blackClicked(v : View)
    {
        //TODO : show card and reduce other opacity
        toast("black clicked")
        makeBackGroundClickableAfterXsec(3)
    }

    fun goToPhase2(v : View)
    {
        val intent = Intent(this, Phase2::class.java)
        startActivity(intent)
        finish()
    }

    fun makeBackGroundClickableAfterXsec(sec : Long)
    {
        phase1_card_red.isClickable=false
        phase1_card_black.isClickable=false
        object : CountDownTimer(sec*1000, 1000) {
            override fun onTick(p0: Long) {}
            override fun onFinish() { layout_phase1.isClickable=true; toast("bg clickable")}
        }.start()
    }
}
