


/* This is a template */








package com.stl.tpalt.redorblack

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.view.View
//import kotlinx.android.synthetic.main.activity_phase/*N*/.*

/*N*/
/*N+1*/

class Phase/*N*/ : AppCompatActivity() {

    lateinit var layout: ConstraintLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_phase/*N*/)
    }

//    layout=layout_phase/*N*/

    fun whichcardcliked(v : View)
    {
        //TODO : show card and reduce other opacity
        //TODO : show text
        layout.isClickable=true
    }

    fun goToPhase/*N+1*/(v : View)
    {
        val intent = Intent(this, Phase/*N+1*/::class.java)
        startActivity(intent)
        finish()
    }
}
