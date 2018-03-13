package com.stl.tpalt.redorblack

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView

/**
 * Created by I346992 on 12/03/2018.
 */
class CardImage : ImageView
{
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val height = measuredHeight
        setMeasuredDimension(5/8*height, height)
    }
}