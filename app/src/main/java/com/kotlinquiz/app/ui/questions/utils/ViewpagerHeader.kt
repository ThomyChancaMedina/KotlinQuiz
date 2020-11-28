package com.kotlinquiz.app.ui.questions.utils

import android.content.Context

import android.util.AttributeSet
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.viewpager.widget.ViewPager

class ViewpagerHeader @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : MotionLayout(context, attrs, defStyleAttr), ViewPager.OnPageChangeListener {

    override fun onPageScrollStateChanged(state: Int) {

    }

    override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
    ) {
        progress = (position + positionOffset)
    }

    override fun onPageSelected(position: Int) {

    }


}