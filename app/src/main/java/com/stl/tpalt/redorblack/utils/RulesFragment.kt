package com.stl.tpalt.redorblack.utils


import android.support.v4.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.stl.tpalt.redorblack.R
import kotlinx.android.synthetic.main.view_rules.view.*
import android.support.v4.view.ViewCompat.setAlpha
import android.widget.ImageView


class RulesFragment() : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Creates the view controlled by the fragment
        var view = inflater.inflate(R.layout.view_rules, container, false)
        println("ONCREATE !!")

        println("UPDATEINFO !!")
        val args = arguments
        val rules = args!!.getParcelable<RulesObject>("rule")
        println("rules : "+rules.toString())
        when(rules) {
            RulesObject.ONE ->
            {
                /*view.textRule!!.text = getString(R.string.rules1)
                view.setBackgroundColor(resources.getColor(android.R.color.holo_blue_dark))*/
                view = inflater.inflate(R.layout.view_rules1, container, false)
            }
            RulesObject.TWO ->
            {
                /*view.textRule!!.text = getString(R.string.rules2)
                view.setBackgroundColor(resources.getColor(android.R.color.holo_green_dark))*/
                view = inflater.inflate(R.layout.view_rules2, container, false)
            }
            RulesObject.THREE ->
            {
                /*view.textRule!!.text = getString(R.string.rules3)
                view.setBackgroundColor(resources.getColor(android.R.color.holo_red_dark))*/
                view = inflater.inflate(R.layout.view_rules3, container, false)
            }
            else -> {
                println("HERE")
                view = inflater.inflate(R.layout.view_rules, container, false)
            }
        }

        return view
    }

    companion object {
        fun newInstance(rulesObject: RulesObject): Fragment {
            println("NEW INSTANCE !!")
            val args = Bundle()
            println("object : "+rulesObject)
            args.putParcelable("rule",rulesObject)
            val fragment = RulesFragment()
            fragment.arguments = args
            return fragment
        }
    }
}