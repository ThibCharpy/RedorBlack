package com.stl.tpalt.redorblack.utils

import android.app.Activity
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.PagerAdapter
import android.view.View

class CustomPagerAdapter(var activity: Activity, fragmentManager: FragmentManager) :
        FragmentPagerAdapter(fragmentManager) {
    override fun getItem(position: Int): Fragment? {
        if (position == 0) return RulesFragment.newInstance(RulesObject.ONE)
        else if (position == 1) return RulesFragment.newInstance(RulesObject.TWO)
        else if (position == 2) return RulesFragment.newInstance(RulesObject.THREE)
        else return RulesFragment.newInstance(RulesObject.EMPTY)
    }

    override fun getCount(): Int {
        return 4
    }

}