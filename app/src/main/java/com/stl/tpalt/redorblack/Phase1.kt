package com.stl.tpalt.redorblack

import android.content.Intent
import android.net.wifi.WifiEnterpriseConfig
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.view.View
import kotlinx.android.synthetic.main.activity_phase1.*

class Phase1 : AppCompatActivity() {
    lateinit var layout: ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phase1)

        layout=layout_phase1
    }



    fun redClicked(v : View)
    {
        //TODO : show card and reduce other opacity
        layout.isClickable=true
    }

    fun blackClicked(v : View)
    {
        //TODO : show card and reduce other opacity
        layout.isClickable=true
    }

    fun goToPhase2(v : View)
    {
        val intent = Intent(this, WifiEnterpriseConfig.Phase2::class.java)
        startActivity(intent)
        finish()
    }
}
